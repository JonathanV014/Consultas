package repositories;

import models.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryPasaporte extends JpaRepository<Pasaporte, Long> {
    Pasaporte findByNumber(String number);

    Pasaporte findByNumberIgnoreCase(String number);

    List<Pasaporte> findByNumberContaining(String fragmento);

    List<Pasaporte> findByPasajeroIsNotNull();

    List<Pasaporte> findByPasajeroIsNull();

    @Query("SELECT p FROM Pasaporte p WHERE p.number = :number")
    Pasaporte buscarPorNumero(@Param("number") String number);

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NOT NULL")
    List<Pasaporte> buscarPasaportesConPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.pasajero IS NULL")
    List<Pasaporte> buscarPasaportesSinPasajero();

    @Query("SELECT p FROM Pasaporte p WHERE p.number IN :numeros")
    List<Pasaporte> buscarPorListaDeNumeros(@Param("numeros") List<String> numeros);

    @Query("SELECT p FROM Pasaporte p WHERE p.number LIKE :prefijo%")
    List<Pasaporte> buscarPorPrefijo(@Param("prefijo") String prefijo);

}
