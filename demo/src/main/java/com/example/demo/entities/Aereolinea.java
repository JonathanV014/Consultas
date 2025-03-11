package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aerolineas")

public class Aereolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "aerolineas_vuelos",
            joinColumns = @JoinColumn(name = "aerolinea_id"),
            inverseJoinColumns = @JoinColumn(name = "vuelo_id")
    )
    private List<Vuelo> vuelos;
}
