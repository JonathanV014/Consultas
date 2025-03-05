package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pasajeros")

public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String NID;

    @OneToOne(targetEntity = Pasaporte.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false, name = "pasaporte_id")
    private Pasaporte pasaporte;

    @OneToMany(targetEntity = Reserva.class, mappedBy = "pasajero")
    private List<Reserva> reservas =  new ArrayList<>();

}
