package com.example.demo.interfaces;

import com.example.demo.models.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfacePasaporte extends JpaRepository<Pasaporte, Long> {
    Pasaporte findByNumero(String numero); // Buscar por número de pasaporte

    Pasaporte findByNumeroIgnoreCase(String numero); // Buscar por número de pasaporte (ignorando mayúsculas/minúsculas)

    List<Pasaporte> findByNumeroContaining(String fragmento); // Buscar por fragmento del número de pasaporte

    List<Pasaporte> findByPasajeroIsNotNull(); // Buscar pasaportes con pasajero asociado

    List<Pasaporte> findByPasajeroIsNull(); // Buscar pasaportes sin pasajero asociado

    @Query("SELECT p FROM Pasaporte p WHERE p.numero = :numero")
    Pasaporte buscarPorNumero(@Param("numero") String numero); // Consulta personalizada para buscar por número de pasaporte

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    List<Pasaporte> buscarPasaportesConPasajero(); // Consulta personalizada para buscar pasaportes con pasajero

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> buscarPasaportesSinPasajero(); // Consulta personalizada para buscar pasaportes sin pasajero

    @Query("SELECT p FROM Pasaporte p WHERE p.numero IN :numeros")
    List<Pasaporte> buscarPorListaDeNumeros(@Param("numeros") List<String> numeros); // Consulta personalizada para buscar por lista de números

    @Query("SELECT p FROM Pasaporte p WHERE p.numero LIKE :prefijo%")
    List<Pasaporte> buscarPorPrefijo(@Param("prefijo") String prefijo); // Consulta personalizada para buscar por prefijo
}