package com.example.demo.repositories;

import com.example.demo.config.ContainerConfiguracion;
import com.example.demo.entities.Reserva;
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
class RepositoryReservaTest {

    @Autowired
    private RepositoryReserva repositoryReserva;

    @Test
    void findByPasajero_Id() {
        List<Reserva> reservas = repositoryReserva.findByPasajero_Id(1L);
        assertFalse(reservas.isEmpty());
        assertEquals(1, reservas.get(0).getPasajero().getId());
    }

    @Test
    void findByVuelo_Id() {
        List<Reserva> reservas = repositoryReserva.findByVuelo_Id(2L);
        assertFalse(reservas.isEmpty());
        assertEquals(2, reservas.get(0).getVuelo().getId());
    }

    @Test
    void findByCodigoDeReserva() {
        UUID codigo = UUID.fromString("880e8700-e29b-41d4-a716-446655440003");
        Reserva reserva = repositoryReserva.findByCodigoDeReserva(codigo);
        assertNotNull(reserva);
        assertEquals(codigo, reserva.getCodigoDeReserva());
    }

    @Test
    void findByPasajeroIsNull() {
        List<Reserva> reservas = repositoryReserva.findByPasajeroIsNull();
        assertTrue(reservas.isEmpty());
    }

    @Test
    void findByVueloIsNotNull() {
        List<Reserva> reservas = repositoryReserva.findByVueloIsNotNull();
        assertEquals(3, reservas.size());
    }

    @Test
    void buscarReservasPorPasajero() {
        List<Reserva> reservas = repositoryReserva.buscarReservasPorPasajero(3L);
        assertFalse(reservas.isEmpty());
        assertEquals(3, reservas.get(0).getPasajero().getId());
    }

    @Test
    void buscarReservasPorVuelo() {
        List<Reserva> reservas = repositoryReserva.buscarReservasPorVuelo(1L);
        assertFalse(reservas.isEmpty());
        assertEquals(1, reservas.get(0).getVuelo().getId());
    }

    @Test
    void buscarPorCodigoReserva() {
        UUID codigo = UUID.fromString("990e8800-e29b-41d4-a716-446655440004");
        Reserva reserva = repositoryReserva.buscarPorCodigoReserva(codigo);
        assertNotNull(reserva);
        assertEquals(codigo, reserva.getCodigoDeReserva());
    }

    @Test
    void buscarReservasSinPasajero() {
        List<Reserva> reservas = repositoryReserva.buscarReservasSinPasajero();
        assertTrue(reservas.isEmpty());
    }

    @Test
    void buscarReservasConVuelo() {
        List<Reserva> reservas = repositoryReserva.buscarReservasConVuelo();
        assertEquals(3, reservas.size());
    }
}