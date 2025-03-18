package com.example.demo.controlador;

import com.example.demo.entities.Vuelo;
import com.example.demo.service.VueloService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorVuelosTest {
    @Mock
    private VueloService vueloService;

    @InjectMocks
    private ControladorVuelos controladorVuelos;

    public ControladorVuelosTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVuelo() {
        Vuelo vuelo = new Vuelo(null, UUID.randomUUID(), "Islas", "Bogota", null);
        when(vueloService.createVuelo(vuelo)).thenReturn(new Vuelo(1L,UUID.randomUUID(), "Islas", "Bogota", null));

        ResponseEntity<Vuelo> response = controladorVuelos.createVuelo(vuelo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("Islas",response.getBody().getOrigen());
        assertEquals("Bogota",response.getBody().getDestino());
        verify(vueloService,times(1)).createVuelo(vuelo);
    }

    @Test
    void getVueloById(){// Falta completar, esta incompleto en la documentacion
        Vuelo vuelo = new Vuelo(1L, UUID.randomUUID(), "Islas", "Bogota", null);
        when(vueloService.getVueloById(1L)).thenReturn(Optional.of(vuelo));

        ResponseEntity<Vuelo> response = controladorVuelos.getVueloById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L,response.getBody().getId());
        assertEquals("Islas",response.getBody().getOrigen());
        assertEquals("Bogota",response.getBody().getDestino());
        verify(vueloService,times(1)).getVueloById(1L);

    }

    @Test
    void getVueloById_NotFound(){
        when(vueloService.getVueloById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Vuelo> response = controladorVuelos.getVueloById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vueloService,times(1)).getVueloById(2L);
    }
  
}