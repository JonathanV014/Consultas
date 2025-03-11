package com.example.demo.repositories;

import com.example.demo.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryVuelo extends JpaRepository<Vuelo, Long> {

    Vuelo findByNumeroVuelo(UUID numeroVuelo);

    List<Vuelo> findByOrigen(String origen);

    List<Vuelo> findByDestino(String destino);

    List<Vuelo> findByOrigenAndDestino(String origen, String destino);

    List<Vuelo> findByReservasIsNotEmpty();

    @Query("SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numeroVuelo")
    Vuelo buscarPorUuid(@Param("numeroVuelo") UUID numeroVuelo);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen")
    List<Vuelo> buscarPorOrigen(@Param("origen") String origen);

    @Query("SELECT v FROM Vuelo v WHERE v.destino = :destino")
    List<Vuelo> buscarPorDestino(@Param("destino") String destino);

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino")
    List<Vuelo> buscarPorOrigenYDestino(@Param("origen") String origen, @Param("destino") String destino);

    @Query("SELECT v FROM Vuelo v WHERE SIZE(v.reservas) > 0")
    List<Vuelo> buscarVuelosConReservas();

}
