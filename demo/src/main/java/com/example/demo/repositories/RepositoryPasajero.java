package com.example.demo.repositories;

import com.example.demo.entities.Pasajero;
import com.example.demo.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryPasajero extends JpaRepository<Pasajero, Long> {

    Optional<Pasajero> findById(Long id);

    List<Pasajero> findByNombre(String nombre);

    //List<Pasajero> findByReserva_Id(Long reservaId);

    //Pasajero findByPasaporte_Id(Long pasaporteId);

    //List<Pasajero> findByReservaIsNotNull();

    @Query("SELECT p FROM Pasajero p JOIN p.reservas r WHERE r.id = :reservaId")
    List<Pasajero> buscarPorReserva(@Param("reservaId") Long reservaId);

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id = :pasaporteId")
    Pasajero buscarPorPasaporte(@Param("pasaporteId") Long pasaporteId);

    @Query("SELECT DISTINCT p FROM Pasajero p JOIN p.reservas r")
    List<Pasajero> buscarPasajerosConReservas();

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id IN :pasaporteIds")
    List<Pasajero> buscarPorListaDePasaportes(@Param("pasaporteIds") List<Long> pasaporteIds);

    @Query("SELECT p FROM Pasajero p WHERE p NOT IN (SELECT DISTINCT p FROM Pasajero p JOIN p.reservas r)")
    List<Pasajero> buscarPasajerosSinReservas();

}