package interfaces;

import models.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InterfaceVuelo extends JpaRepository<Vuelo, Long> {

    Vuelo findByUuid(UUID uuid);

    List<Vuelo> findByOrigin(String origin);

    List<Vuelo> findByDestiny(String destiny);

    List<Vuelo> findByOriginAndDestiny(String origin, String destiny);

    List<Vuelo> findByReservasIsNotEmpty();

    @Query("SELECT v FROM Vuelo v WHERE v.uuid = :uuid")
    Vuelo buscarPorUuid(@Param("uuid") UUID uuid);

    @Query("SELECT v FROM Vuelo v WHERE v.origin = :origin")
    List<Vuelo> buscarPorOrigen(@Param("origin") String origin);

    @Query("SELECT v FROM Vuelo v WHERE v.destiny = :destiny")
    List<Vuelo> buscarPorDestino(@Param("destiny") String destiny);

    @Query("SELECT v FROM Vuelo v WHERE v.origin = :origin AND v.destiny = :destiny")
    List<Vuelo> buscarPorOrigenYDestino(@Param("origin") String origin, @Param("destiny") String destiny);

    @Query("SELECT v FROM Vuelo v WHERE SIZE(v.reservas) > 0")
    List<Vuelo> buscarVuelosConReservas();

}
