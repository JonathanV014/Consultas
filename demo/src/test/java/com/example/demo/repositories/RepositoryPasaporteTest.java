package com.example.demo.repositories;

import com.example.demo.config.ContainerConfiguracion;
import com.example.demo.entities.Pasaporte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(ContainerConfiguracion.class)
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:data.sql")
class RepositoryPasaporteTest {

    @Autowired
    private RepositoryPasaporte repositoryPasaporte;

    @Test
    void findByNumero() {
        Pasaporte pasaporte = repositoryPasaporte.findByNumero("ABC123456");
        assertNotNull(pasaporte);
        assertEquals("ABC123456", pasaporte.getNumero());
    }

    @Test
    void findByNumeroIgnoreCase() {
        Pasaporte pasaporte = repositoryPasaporte.findByNumeroIgnoreCase("abc123456");
        assertNotNull(pasaporte);
        assertEquals("ABC123456", pasaporte.getNumero());
    }

    @Test
    void findByNumeroContaining() {
        List<Pasaporte> pasaportes = repositoryPasaporte.findByNumeroContaining("XYZ");
        assertFalse(pasaportes.isEmpty());
        assertEquals("XYZ987654", pasaportes.get(0).getNumero());
    }

    @Test
    void findByPasajeroIsNotNull() {
        List<Pasaporte> pasaportes = repositoryPasaporte.findByPasajeroIsNotNull();
        assertEquals(3, pasaportes.size());
    }

    @Test
    void findByPasajeroIsNull() {
        List<Pasaporte> pasaportes = repositoryPasaporte.findByPasajeroIsNull();
        assertTrue(pasaportes.isEmpty());
    }

    @Test
    void buscarPorNumero() {
        Pasaporte pasaporte = repositoryPasaporte.buscarPorNumero("LMN567890");
        assertNotNull(pasaporte);
        assertEquals("LMN567890", pasaporte.getNumero());
    }

    @Test
    void buscarPasaportesConPasajero() {
        List<Pasaporte> pasaportes = repositoryPasaporte.buscarPasaportesConPasajero();
        assertEquals(3, pasaportes.size());
    }

    @Test
    void buscarPasaportesSinPasajero() {
        List<Pasaporte> pasaportes = repositoryPasaporte.buscarPasaportesSinPasajero();
        assertTrue(pasaportes.isEmpty());
    }

    @Test
    void buscarPorListaDeNumeros() {
        List<Pasaporte> pasaportes = repositoryPasaporte.buscarPorListaDeNumeros(List.of("ABC123456", "XYZ987654"));
        assertEquals(2, pasaportes.size());
    }

    @Test
    void buscarPorPrefijo() {
        List<Pasaporte> pasaportes = repositoryPasaporte.buscarPorPrefijo("LMN");
        assertFalse(pasaportes.isEmpty());
        assertEquals("LMN567890", pasaportes.get(0).getNumero());
    }
}