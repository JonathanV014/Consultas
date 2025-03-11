package repositories;

import models.Pasaporte;
import models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RepositoryReserva extends JpaRepository<Pasaporte, Long> {
    List<Reserva> findByPasajero_Id(Long pasajeroId);

    List<Reserva> findByVuelo_Id(Long vueloId);

    Reserva findByCodigoReserva(UUID codigoReserva);

    List<Reserva> findByPasajeroIsNull();

    List<Reserva> findByVueloIsNotNull();

    @Query("SELECT r FROM Reserva r WHERE r.pasajero.id = :pasajeroId")
    List<Reserva> buscarReservasPorPasajero(@Param("pasajeroId") Long pasajeroId);

    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> buscarReservasPorVuelo(@Param("vueloId") Long vueloId);

    @Query("SELECT r FROM Reserva r WHERE r.codigoReserva = :codigoReserva")
    Reserva buscarPorCodigoReserva(@Param("codigoReserva") UUID codigoReserva);

    @Query("SELECT r FROM Reserva r WHERE r.pasajero IS NULL")
    List<Reserva> buscarReservasSinPasajero();

    @Query("SELECT r FROM Reserva r WHERE r.vuelo IS NOT NULL")
    List<Reserva> buscarReservasConVuelo();

}
