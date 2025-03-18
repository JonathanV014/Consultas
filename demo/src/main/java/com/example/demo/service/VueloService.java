package com.example.demo.service;

import com.example.demo.entities.Vuelo;
import com.example.demo.repositories.RepositoryVuelo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Getter
@Service
public class VueloService {

    private final RepositoryVuelo repositoryVuelo;

    @Autowired
    public VueloService(RepositoryVuelo repositoryVuelo) {
        this.repositoryVuelo = repositoryVuelo;
    }

    public Vuelo createVuelo(Vuelo vuelo) {
        return this.repositoryVuelo.save(vuelo);
    }

    public Optional<Vuelo> getVueloById(Long id) {
        return this.repositoryVuelo.findById(id);
    }
}
