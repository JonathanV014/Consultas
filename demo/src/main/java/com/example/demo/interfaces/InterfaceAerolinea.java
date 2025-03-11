package com.example.demo.interfaces;

import com.example.demo.models.Aereolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterfaceAerolinea extends JpaRepository<Aereolinea, Long> {

    Optional<Aereolinea> findById(Long id); // Buscar por ID

    List<Aereolinea> findByNombre(String nombre); // Buscar por nombre exacto

    List<Aereolinea> findByNombreContaining(String keyword); // Buscar por nombre que contenga una palabra clave

    List<Aereolinea> findByVuelos_Id(Long vueloId); // Buscar por ID de vuelo

    @Query("SELECT a FROM Aereolinea a WHERE a.nombre LIKE %:nombre%")
    List<Aereolinea> buscarPorNombre(@Param("nombre") String nombre); // Consulta personalizada para buscar por nombre

    @Query("SELECT a FROM Aereolinea a JOIN a.vuelos v WHERE v.id = :vueloId")
    List<Aereolinea> buscarPorVuelo(@Param("vueloId") Long vueloId); // Consulta personalizada para buscar por ID de vuelo

    @Query("SELECT DISTINCT a FROM Aereolinea a WHERE SIZE(a.vuelos) > 0")
    List<Aereolinea> encontrarConVuelos(); // Consulta personalizada para encontrar aerolíneas con vuelos

    @Query("SELECT a FROM Aereolinea a WHERE SIZE(a.vuelos) = :size")
    List<Aereolinea> encontrarPorCantidadDeVuelos(@Param("size") int size); // Consulta personalizada para encontrar aerolíneas con un número específico de vuelos
}