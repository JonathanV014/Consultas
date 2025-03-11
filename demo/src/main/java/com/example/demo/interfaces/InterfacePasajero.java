package com.example.demo.interfaces;

import com.example.demo.models.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterfacePasajero extends JpaRepository<Pasajero, Long> {
    Optional<Pasajero> findById(Long id);

    List<Pasajero> findByNombre(String nombre);

    List<Pasajero> findByReservas_Id(Long reservaId); // Corregido: usar "reservas" en lugar de "reserva"

    Pasajero findByPasaporte_Id(Long pasaporteId);

    List<Pasajero> findByReservasIsNotNull(); // Corregido: usar "reservas" en lugar de "reserva"

    @Query("SELECT p FROM Pasajero p JOIN p.reservas r WHERE r.id = :reservaId")
    List<Pasajero> buscarPorReserva(@Param("reservaId") Long reservaId); // Corregido: usar "reservas" en lugar de "reserva"

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id = :pasaporteId")
    Pasajero buscarPorPasaporte(@Param("pasaporteId") Long pasaporteId);

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reservas) > 0")
    List<Pasajero> buscarPasajerosConReservas(); // Corregido: usar "reservas" en lugar de "reserva"

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id IN :pasaporteIds")
    List<Pasajero> buscarPorListaDePasaportes(@Param("pasaporteIds") List<Long> pasaporteIds);

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reservas) = 0")
    List<Pasajero> buscarPasajerosSinReservas(); // Corregido: usar "reservas" en lugar de "reserva"
}