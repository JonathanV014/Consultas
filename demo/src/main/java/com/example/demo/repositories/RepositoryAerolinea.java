package com.example.demo.repositories;

import com.example.demo.entities.Aereolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryAerolinea extends JpaRepository<Aereolinea, Long> {

    Optional<Aereolinea> findById(Long id);

    List<Aereolinea> findByNombre(String nombre);

    List<Aereolinea> findByNombreContaining(String keyword);

    //List<Aereolinea> findByVuelosSize(int size);

    List<Aereolinea> findByVuelos_Id(Long vueloId);


    @Query("select u from Aereolinea u where u.id = ?1")
    Aereolinea findByIdQuery(long id);

    @Query("SELECT a FROM Aereolinea a WHERE a.nombre LIKE %:nombre%")
    List<Aereolinea> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Aereolinea a JOIN a.vuelos v WHERE v.id = :vueloId")
    List<Aereolinea> buscarPorVuelo(@Param("vueloId") Long vueloId);

    @Query("SELECT DISTINCT a FROM Aereolinea a JOIN a.vuelos v")
    List<Aereolinea> encontrarConVuelos();

    @Query("SELECT a FROM Aereolinea a WHERE (SELECT COUNT(v) FROM a.vuelos v) = :size")
    List<Aereolinea> encontrarPorCantidadDeVuelos(@Param("size") int size);

}
