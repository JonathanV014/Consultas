package com.example.demo.service;

import com.example.demo.entities.Vuelo;
import com.example.demo.repositories.RepositoryVuelo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VueloServiceTest {
    @Mock
    private RepositoryVuelo repositoryVuelo;

    @InjectMocks
    private VueloService vueloService;

    public VueloServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVuelo() {
        Vuelo vuelo = new Vuelo(null, UUID.randomUUID(), "Islas", "Bogota", null);
        when(repositoryVuelo.save(any(Vuelo.class))).thenReturn(new Vuelo());

        Vuelo createdVuelo = vueloService.createVuelo(vuelo);

        assertNotNull(createdVuelo.getId());
        assertEquals("Islas",createdVuelo.getOrigen());
        assertEquals("Bogota",createdVuelo.getDestino());
        verify(repositoryVuelo, times(1)).save(any(Vuelo.class));


    }


    @Test
    void getVueloById() {
        Vuelo vuelo = new Vuelo(1L, UUID.randomUUID(), "Islas", "Bogota", null);
        when(repositoryVuelo.findById(1L)).thenReturn(Optional.of(vuelo));

        Optional<Vuelo> foundVuelo = vueloService.getVueloById(1L);

        assertTrue(foundVuelo.isPresent());
        assertEquals("Islas", foundVuelo.get().getOrigen());
        verify(repositoryVuelo,times(1)).findById(1L);

    }

    @Test
    void getVueloByIdNotFound() {
        when(repositoryVuelo.findById(2L)).thenReturn(Optional.empty());

        Optional<Vuelo> foundVuelo = vueloService.getVueloById(2L);
        assertFalse(foundVuelo.isPresent());
        verify(repositoryVuelo,times(1)).findById(2L);
    }
}