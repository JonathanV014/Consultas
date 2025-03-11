package com.example.demo.repositories;

import com.example.demo.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPasaporte extends JpaRepository<Pasaporte, Long> {
    Pasaporte findByNumero(String numero);

    Pasaporte findByNumeroIgnoreCase(String numero);

    List<Pasaporte> findByNumeroContaining(String numero);

    List<Pasaporte> findByPasajeroIsNotNull();

    List<Pasaporte> findByPasajeroIsNull();

    @Query("SELECT p FROM Pasaporte p WHERE p.numero = :numero")
    Pasaporte buscarPorNumero(@Param("numero") String numero);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    List<Pasaporte> buscarPasaportesConPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> buscarPasaportesSinPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.numero IN :numeros")
    List<Pasaporte> buscarPorListaDeNumeros(@Param("numeros") List<String> numeros);

    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE CONCAT(:prefijo, '%')")
    List<Pasaporte> buscarPorPrefijo(@Param("prefijo") String prefijo);

}
