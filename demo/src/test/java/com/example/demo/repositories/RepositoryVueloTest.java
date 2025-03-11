package com.example.demo.repositories;

import com.example.demo.config.ContainerConfiguracion;
import com.example.demo.entities.Vuelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(ContainerConfiguracion.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:data.sql")

class RepositoryVueloTest {

    @Autowired
    private RepositoryVuelo repositoryVuelo;

    @Test
    void findByNumeroVuelo() {
        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        Vuelo vuelo = repositoryVuelo.findByNumeroVuelo(uuid);
        assertNotNull(vuelo);
        assertEquals("Londres", vuelo.getOrigen());
        assertEquals("Madrid", vuelo.getDestino());
    }

    @Test
    void findByOrigen() {
        List<Vuelo> vuelos = repositoryVuelo.findByOrigen("Roma");
        assertFalse(vuelos.isEmpty());
        assertEquals("París", vuelos.get(0).getDestino());
    }

    @Test
    void findByDestino() {
        List<Vuelo> vuelos = repositoryVuelo.findByDestino("Ámsterdam");
        assertFalse(vuelos.isEmpty());
        assertEquals("Berlín", vuelos.get(0).getOrigen());
    }

    @Test
    void findByOrigenAndDestino() {
        List<Vuelo> vuelos = repositoryVuelo.findByOrigenAndDestino("Londres", "Madrid");
        assertFalse(vuelos.isEmpty());
    }

    @Test
    void findByReservasIsNotEmpty() {
        List<Vuelo> vuelos = repositoryVuelo.findByReservasIsNotEmpty();
        assertFalse(vuelos.isEmpty());
    }

    @Test
    void buscarPorUuid() {
        UUID uuid = UUID.fromString("660e8500-e29b-41d4-a716-446655440001");
        Vuelo vuelo = repositoryVuelo.buscarPorUuid(uuid);
        assertNotNull(vuelo);
        assertEquals("Roma", vuelo.getOrigen());
    }

    @Test
    void buscarPorOrigen() {
        List<Vuelo> vuelos = repositoryVuelo.buscarPorOrigen("Berlín");
        assertFalse(vuelos.isEmpty());
        assertEquals("Ámsterdam", vuelos.get(0).getDestino());
    }

    @Test
    void buscarPorDestino() {
        List<Vuelo> vuelos = repositoryVuelo.buscarPorDestino("Madrid");
        assertFalse(vuelos.isEmpty());
        assertEquals("Londres", vuelos.get(0).getOrigen());
    }

    @Test
    void buscarPorOrigenYDestino() {
        List<Vuelo> vuelos = repositoryVuelo.buscarPorOrigenYDestino("Roma", "París");
        assertFalse(vuelos.isEmpty());
    }

    @Test
    void buscarVuelosConReservas() {
        List<Vuelo> vuelos = repositoryVuelo.buscarVuelosConReservas();
        assertFalse(vuelos.isEmpty());
    }
}