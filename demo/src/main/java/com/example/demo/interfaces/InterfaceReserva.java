package com.example.demo.interfaces;

import com.example.demo.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InterfaceReserva extends JpaRepository<Reserva, Long> {
    List<Reserva> findByPasajero_Id(Long pasajeroId); // Buscar reservas por ID de pasajero

    List<Reserva> findByVuelo_Id(Long vueloId); // Buscar reservas por ID de vuelo

    Reserva findByCodigoDeReserva(UUID codigoDeReserva); // Buscar reserva por código de reserva

    List<Reserva> findByPasajeroIsNull(); // Buscar reservas sin pasajero

    List<Reserva> findByVueloIsNotNull(); // Buscar reservas con vuelo

    @Query("SELECT r FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    List<Reserva> buscarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId); // Consulta personalizada para buscar reservas por ID de pasajero

    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> buscarReservasPorVuelo(@Param("vueloId") Long vueloId); // Consulta personalizada para buscar reservas por ID de vuelo

    @Query("SELECT r FROM Reserva r WHERE r.codigoDeReserva = :codigoDeReserva")
    Reserva buscarPorCodigoReserva(@Param("codigoDeReserva") UUID codigoDeReserva); // Consulta personalizada para buscar reserva por código de reserva

    @Query("SELECT r FROM Reserva r WHERE r.pasajero IS NULL")
    List<Reserva> buscarReservasSinPasajero(); // Consulta personalizada para buscar reservas sin pasajero

    @Query("SELECT r FROM Reserva r WHERE r.vuelo IS NOT NULL")
    List<Reserva> buscarReservasConVuelo(); // Consulta personalizada para buscar reservas con vuelo
}