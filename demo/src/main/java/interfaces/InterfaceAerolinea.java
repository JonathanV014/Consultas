package interfaces;

import models.Aereolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfaceAerolinea extends JpaRepository<Aereolinea, Long> {

    Aereolinea findByIdAerolinea(Long idAerolinea);

    List<Aereolinea> findByNombre(String nombre);

    List<Aereolinea> findByNameContaining(String keyword);

    List<Aereolinea> findByVuelosSize(int size);

    List<Aereolinea> findByVuelos_Id(Long vueloId);


    @Query("select u from Aereolinea u where u.id = ?1")
    Aereolinea findByidQuery(long id);

    @Query("SELECT a FROM Aereolinea a WHERE a.name LIKE %:name%")
    List<Aereolinea> buscarPorNombre(@Param("name") String name);

    @Query("SELECT a FROM Aereolinea a JOIN a.vuelos v WHERE v.id = :vueloId")
    List<Aereolinea> buscarPorVuelo(@Param("vueloId") Long vueloId);

    @Query("SELECT DISTINCT a FROM Aereolinea a WHERE SIZE(a.vuelos) > 0")
    List<Aereolinea> encontrarConVuelos();

    @Query("SELECT a FROM Aereolinea a WHERE SIZE(a.vuelos) > :cantidad")
    List<Aereolinea> encontrarPorCantidadDeVuelos(@Param("cantidad") int cantidad);






}
