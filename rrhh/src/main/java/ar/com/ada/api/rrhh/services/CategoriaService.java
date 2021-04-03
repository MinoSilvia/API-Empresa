package ar.com.ada.api.rrhh.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Categoria;
import ar.com.ada.api.rrhh.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public void crearcategoria(Categoria categoria) {
        repository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return repository.findAll();
    }

}