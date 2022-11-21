package com.soumosir.bookmydream.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.model.helper.ScreeningRest;
import com.soumosir.bookmydream.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScreeningController {
    private final ScreeningService screeningService;

    @GetMapping("/screening")
    public ResponseEntity<List<ScreeningRest>> getScreenings(){
        return ResponseEntity.ok().body(screeningService.getScreenings().stream().map(screening ->
                new ScreeningRest(screening)).collect(Collectors.toList()));
    }

    @PostMapping("/screening")
    public ResponseEntity<ScreeningRest> saveScreening(@RequestBody Screening screening, HttpServletResponse response) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/hall").toUriString());
        try {
            Screening scr = screeningService.saveScreening(screening);
            return ResponseEntity.created(uri).body(new ScreeningRest(scr));
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

    @PutMapping("/screening/{id}")
    public ResponseEntity<ScreeningRest> updateScreening(@PathVariable long id, @RequestBody Screening screening){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/screening/"+id).toUriString());
        return ResponseEntity.created(uri).body(new ScreeningRest(screeningService.updateScreening(id,screening)));
    }

    @DeleteMapping("/screening/{id}")
    public ResponseEntity<Screening> deleteScreening(@PathVariable long id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/screening").toUriString());
        screeningService.deleteScreening(id);
        return ResponseEntity.ok().build();
    }

}


