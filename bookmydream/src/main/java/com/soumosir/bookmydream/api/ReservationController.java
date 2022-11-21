package com.soumosir.bookmydream.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.model.helper.ReservationRest;
import com.soumosir.bookmydream.repo.AppUserRepo;
import com.soumosir.bookmydream.repo.RoleRepo;
import com.soumosir.bookmydream.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final RoleRepo roleRepo;
    private final AppUserRepo appUserRepo;


    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationRest>> getReservations(Authentication authentication){

        List<Reservation> reservations;
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            reservations = reservationService.getReservations();
        }
        else {
            reservations = reservationService.getReservationsByUsername(authentication.getPrincipal().toString());
        }

        return ResponseEntity.ok().body(reservations.stream().map(reservation ->
                new ReservationRest(reservation)).collect(Collectors.toList()));

    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationRest> saveScreening(@RequestBody Reservation reservation, HttpServletResponse response,Principal principal) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reservation").toUriString());
        try {
            AppUser appUser = appUserRepo.findByUsername(principal.getName()).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + principal.getName()));
            reservation.setAppUser(appUser);
            Reservation reserv = reservationService.saveReservation(reservation);
            return ResponseEntity.created(uri).body(new ReservationRest(reserv));
        }
        catch(Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationRest> updateReservation(@PathVariable long id, @RequestBody Reservation reservation,HttpServletResponse response,Principal principal) throws IOException{

        try {
            AppUser appUser = appUserRepo.findByUsername(principal.getName()).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + principal.getName()));
            reservation.setAppUser(appUser);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reservation/" + id).toUriString());
            return ResponseEntity.created(uri).body(new ReservationRest(reservationService.updateReservation(id, reservation)));
        }
        catch(Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable long id,Principal principal){
        AppUser appUser = appUserRepo.findByUsername(principal.getName()).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + principal.getName()));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reservation").toUriString());
        reservationService.deleteReservation(id,appUser.getUsername());
        return ResponseEntity.ok().build();
    }

}




