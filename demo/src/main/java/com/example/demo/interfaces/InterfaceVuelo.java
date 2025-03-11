package com.example.demo.interfaces;

import com.example.demo.models.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InterfaceVuelo extends JpaRepository<Vuelo, Long> {

    Vuelo findByNumeroVuelo(UUID numeroVuelo); // Buscar por número de vuelo

    List<Vuelo> findByOrigen(String origen); // Buscar por origen

    List<Vuelo> findByDestino(String destino); // Buscar por destino

    List<Vuelo> findByOrigenAndDestino(String origen, String destino); // Buscar por origen y destino

    List<Vuelo> findByReservasIsNotEmpty(); // Buscar vuelos con reservas

    @Query("SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numeroVuelo")
    Vuelo buscarPorNumeroVuelo(@Param("numeroVuelo") UUID numeroVuelo); // Consulta personalizada para buscar por número de vuelo

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen")
    List<Vuelo> buscarPorOrigen(@Param("origen") String origen); // Consulta personalizada para buscar por origen

    @Query("SELECT v FROM Vuelo v WHERE v.destino = :destino")
    List<Vuelo> buscarPorDestino(@Param("destino") String destino); // Consulta personalizada para buscar por destino

    @Query("SELECT v FROM Vuelo v WHERE v.origen = :origen AND v.destino = :destino")
    List<Vuelo> buscarPorOrigenYDestino(@Param("origen") String origen, @Param("destino") String destino); // Consulta personalizada para buscar por origen y destino

    @Query("SELECT v FROM Vuelo v WHERE SIZE(v.reservas) > 0")
    List<Vuelo> buscarVuelosConReservas(); // Consulta personalizada para buscar vuelos con reservas
}