package com.example.demo.repositories;

import com.example.demo.config.ContainerConfiguracion;
import com.example.demo.entities.Pasajero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(ContainerConfiguracion.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:data.sql")
class RepositoryPasajeroTest {

    @Autowired
    private RepositoryPasajero repositoryPasajero;

    @Test
    void findById() {
        Optional<Pasajero> pasajero = repositoryPasajero.findById(1L);
        assertTrue(pasajero.isPresent());
        assertEquals("Juan Pérez", pasajero.get().getNombre());
    }

    @Test
    void findByNombre() {
        List<Pasajero> pasajeros = repositoryPasajero.findByNombre("Juan Pérez");
        assertFalse(pasajeros.isEmpty());
        assertEquals("Juan Pérez", pasajeros.get(0).getNombre());
    }

    @Test
    void buscarPorReserva() {
        List<Pasajero> pasajeros = repositoryPasajero.buscarPorReserva(1L);
        assertFalse(pasajeros.isEmpty());
        assertEquals("Juan Pérez", pasajeros.get(0).getNombre());
    }

    @Test
    void buscarPorPasaporte() {
        Pasajero pasajero = repositoryPasajero.buscarPorPasaporte(1L);
        assertNotNull(pasajero);
        assertEquals("Juan Pérez", pasajero.getNombre());
    }

    @Test
    void buscarPasajerosConReservas() {
        List<Pasajero> pasajeros = repositoryPasajero.buscarPasajerosConReservas();
        assertFalse(pasajeros.isEmpty());
    }

    @Test
    void buscarPorListaDePasaportes() {
        List<Pasajero> pasajeros = repositoryPasajero.buscarPorListaDePasaportes(List.of(1L, 2L));
        assertFalse(pasajeros.isEmpty());
        assertEquals(2, pasajeros.size());
    }

    @Test
    void buscarPasajerosSinReservas() {
        List<Pasajero> pasajeros = repositoryPasajero.buscarPasajerosSinReservas();
        assertTrue(pasajeros.isEmpty());
    }
}