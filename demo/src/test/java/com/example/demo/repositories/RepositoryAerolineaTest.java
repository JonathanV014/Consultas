package com.example.demo.repositories;

import com.example.demo.config.ContainerConfiguracion;
import com.example.demo.entities.Aereolinea;
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
class RepositoryAerolineaTest {


    @Autowired
    private RepositoryAerolinea repositoryAerolinea;

    @Test
    void findById() {
        var aerolinea = repositoryAerolinea.findById(1L);
        assertTrue(aerolinea.isPresent());
        assertEquals("AeroTest", aerolinea.get().getNombre());
    }

    @Test
    void findByNombre() {
        List<Aereolinea> aerolineas = repositoryAerolinea.findByNombre("SkyFlights");
        assertFalse(aerolineas.isEmpty());
        assertEquals("SkyFlights", aerolineas.get(0).getNombre());
    }

    @Test
    void findByNombreContaining() {
        List<Aereolinea> aerolineas = repositoryAerolinea.findByNombreContaining("Test");
        assertFalse(aerolineas.isEmpty());
        assertTrue(aerolineas.get(0).getNombre().contains("Test"));
    }

    @Test
    void findByVuelos_Id() {
        List<Aereolinea> aerolineas = repositoryAerolinea.findByVuelos_Id(1L);
        assertFalse(aerolineas.isEmpty());
        assertEquals("AeroTest", aerolineas.get(0).getNombre());
    }

    @Test
    void findByIdQuery() {
        Aereolinea aerolinea = repositoryAerolinea.findByIdQuery(2L);
        assertNotNull(aerolinea);
        assertEquals("SkyFlights", aerolinea.getNombre());
    }

    @Test
    void buscarPorNombre() {
        List<Aereolinea> aerolineas = repositoryAerolinea.buscarPorNombre("High");
        assertFalse(aerolineas.isEmpty());
        assertTrue(aerolineas.get(0).getNombre().contains("High"));
    }

    @Test
    void buscarPorVuelo() {
        List<Aereolinea> aerolineas = repositoryAerolinea.buscarPorVuelo(3L);
        assertFalse(aerolineas.isEmpty());
        assertEquals("FlyHigh", aerolineas.get(0).getNombre());
    }

    @Test
    void encontrarConVuelos() {
        List<Aereolinea> aerolineas = repositoryAerolinea.encontrarConVuelos();
        assertFalse(aerolineas.isEmpty());
    }

    @Test
    void encontrarPorCantidadDeVuelos() {
        List<Aereolinea> aerolineas = repositoryAerolinea.encontrarPorCantidadDeVuelos(1);
        assertFalse(aerolineas.isEmpty());
        assertTrue(aerolineas.stream().allMatch(a -> a.getVuelos().size() == 1));
    }
}