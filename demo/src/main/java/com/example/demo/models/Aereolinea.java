package com.example.demo.models;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(nullable = false)
    private String nombre;

    @ManyToMany(targetEntity = Vuelo.class)
    @JoinTable(name = "aerolineas_vuelos",
            inverseJoinColumns = @JoinColumn(nullable = true))

    private List<Vuelo> vuelos;
}
