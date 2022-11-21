package com.soumosir.bookmydream.api;

import com.soumosir.bookmydream.model.Hall;

import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.helper.HallRest;
import com.soumosir.bookmydream.service.HallService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @GetMapping("/hall")
    public ResponseEntity<List<HallRest>> getHalls(){

        return ResponseEntity.ok().body(
                hallService.getHalls().stream().map(
                        hall ->
                                new HallRest(hall)).collect(Collectors.toList()));
    }

    @PostMapping("/hall")
    public ResponseEntity<HallRest> saveHall(@RequestBody Hall hall){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/hall").toUriString());

        return ResponseEntity.created(uri).body(new HallRest(hallService.saveHall(hall)));
    }

    @PutMapping("/hall/{id}")
    public ResponseEntity<HallRest> updateHall(@PathVariable long id, @RequestBody Hall hall){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/hall/"+id).toUriString());
        return ResponseEntity.created(uri).body(new HallRest(hallService.updateHall(id,hall)));
    }

    @DeleteMapping("/hall/{id}")
    public ResponseEntity<?> deleteHall(@PathVariable long id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/hall").toUriString());
        hallService.deleteHall(id);
        return ResponseEntity.ok().build();
    }

}


