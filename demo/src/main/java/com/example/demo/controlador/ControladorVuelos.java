package com.example.demo.controlador;

import com.example.demo.entities.Vuelo;
import com.example.demo.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
public class ControladorVuelos {
    @Autowired
    private final VueloService vueloService;

    public ControladorVuelos(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping
    public ResponseEntity<Vuelo> createVuelo(@RequestBody Vuelo vuelo) {
        Vuelo vueloCreado = vueloService.createVuelo(vuelo);
        return new ResponseEntity<>(vueloCreado, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable Long id) {
        Optional <Vuelo> vuelo = vueloService.getVueloById(id);
        return vuelo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
