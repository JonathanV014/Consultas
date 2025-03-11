package com.example.demo.repositories;

import com.example.demo.interfaces.InterfaceAerolinea;
import com.example.demo.models.Aereolinea;
import com.example.demo.models.Vuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InterfaceAerolineaTest {

    @Autowired
    private InterfaceAerolinea interfaceAerolinea;

    private Aereolinea aerolinea1;
    private Aereolinea aerolinea2;

    @BeforeEach
    void setUp() {
        aerolinea1 = new Aereolinea();
        aerolinea1.setNombre("AeroTest");

        aerolinea2 = new Aereolinea();
        aerolinea2.setNombre("SkyFly");

        interfaceAerolinea.save(aerolinea1);
        interfaceAerolinea.save(aerolinea2);
    }

    @Test
    void testFindById() {
        Optional<Aereolinea> found = interfaceAerolinea.findById(aerolinea1.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("AeroTest");
    }

    @Test
    void testFindByNombre() {
        List<Aereolinea> found = interfaceAerolinea.findByNombre("AeroTest");
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getNombre()).isEqualTo("AeroTest");
    }

    @Test
    void testFindByNombreContaining() {
        List<Aereolinea> found = interfaceAerolinea.findByNombreContaining("Test");
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getNombre()).isEqualTo("AeroTest");
    }
}