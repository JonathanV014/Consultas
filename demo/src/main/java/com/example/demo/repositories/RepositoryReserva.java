package com.example.demo.repositories;

import com.example.demo.entities.Pasaporte;
import com.example.demo.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryReserva extends JpaRepository<Reserva, Long> {
    List<Reserva> findByPasajero_Id(Long pasajeroId);

    List<Reserva> findByVuelo_Id(Long vueloId);

    Reserva findByCodigoDeReserva(UUID codigoDeReserva);

    List<Reserva> findByPasajeroIsNull();

    List<Reserva> findByVueloIsNotNull();

    @Query("SELECT r FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    List<Reserva> buscarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);

    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> buscarReservasPorVuelo(@Param("vueloId") Long vueloId);

    @Query("SELECT r FROM Reserva r WHERE r.codigoDeReserva = :codigoDeReserva")
    Reserva buscarPorCodigoReserva(@Param("codigoDeReserva") UUID codigoDeReserva);

    @Query("SELECT r FROM Reserva r WHERE r.pasajero IS NULL")
    List<Reserva> buscarReservasSinPasajero();

    @Query("SELECT r FROM Reserva r WHERE r.vuelo IS NOT NULL")
    List<Reserva> buscarReservasConVuelo();

}
