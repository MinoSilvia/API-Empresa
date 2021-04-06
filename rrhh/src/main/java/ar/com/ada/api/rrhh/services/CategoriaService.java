package ar.com.ada.api.rrhh.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Categoria;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repositoryCategoria;

    public void crearcategoria(Categoria categoria) {
        repositoryCategoria.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return repositoryCategoria.findAll();
    }

    public List<Empleado> traerEmpleadosPorCategoria(int categoriaId) {

        Optional<Categoria> cOptional = repositoryCategoria.findById(categoriaId);
        List<Empleado> listaVacia = new ArrayList<>();

        if (cOptional.isPresent()) {
            return (cOptional.get()).getEmpleados();
        }
        return listaVacia;
    }

    public Categoria buscarCategoriaPorId(int categoriaId) {

        Optional<Categoria> cOptional = repositoryCategoria.findById(categoriaId);

        if (cOptional.isPresent()) {

            return cOptional.get();
        }
        return null;

    }

}
