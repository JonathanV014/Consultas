package interfaces;

import models.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfacePasajero extends JpaRepository<Pasajero, Long> {
    Pasajero findByIdPasajero(Long idPasajero);

    List<Pasajero> findByNombre(String nombre);

    List<Pasajero> findByReserva_Id(Long reservaId);

    Pasajero findByPasaporte_Id(Long pasaporteId);

    List<Pasajero> findByReservaIsNotNull();

    @Query("SELECT p FROM Pasajero p JOIN p.reserva r WHERE r.id = :reservaId")
    List<Pasajero> buscarPorReserva(@Param("reservaId") Long reservaId);

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id = :pasaporteId")
    Pasajero buscarPorPasaporte(@Param("pasaporteId") Long pasaporteId);

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reserva) > 0")
    List<Pasajero> buscarPasajerosConReservas();

    @Query("SELECT p FROM Pasajero p WHERE p.pasaporte.id IN :pasaporteIds")
    List<Pasajero> buscarPorListaDePasaportes(@Param("pasaporteIds") List<Long> pasaporteIds);

    @Query("SELECT p FROM Pasajero p WHERE SIZE(p.reserva) = 0")
    List<Pasajero> buscarPasajerosSinReservas();

}