package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservas")

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID codigoDeReserva;

    @ManyToOne(targetEntity = Pasajero.class, optional = true)
    @JoinColumn(nullable = true, name = "pasajero_id")
    private Pasajero pasajero;

    @ManyToOne(targetEntity = Vuelo.class, optional = false)
    @JoinColumn(nullable = false, name = "vuelo_id")
    private Vuelo vuelo;

}
