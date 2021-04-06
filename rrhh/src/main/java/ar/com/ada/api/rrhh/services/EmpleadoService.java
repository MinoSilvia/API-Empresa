package ar.com.ada.api.rrhh.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository repository;

    public void crearEmpleado(Empleado empleado) {
        repository.save(empleado);
    }

    public List<Empleado> ListarEmpleados() {

        return repository.findAll();
    }

    public Empleado traerEmpleadoPorId(int idEmpleado) {

        Optional<Empleado> eo = repository.findById(idEmpleado);

        if (eo.isPresent()) {
            return eo.get();
        }
        return null;
    }

}
