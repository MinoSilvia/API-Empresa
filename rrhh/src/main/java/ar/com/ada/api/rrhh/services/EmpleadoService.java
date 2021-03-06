package ar.com.ada.api.rrhh.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public void crearEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public List<Empleado> ListarEmpleados() {

        return empleadoRepository.findAll();
    }

    public Empleado traerEmpleadoPorId(int idEmpleado) {

        Optional<Empleado> eo = empleadoRepository.findById(idEmpleado);

        if (eo.isPresent()) {
            return eo.get();
        }
        return null;
    }

    public void actualizarSueldoEmpleado(Empleado empleadoOriginal, BigDecimal sueldo) {

        empleadoOriginal.setSueldo(sueldo);
        empleadoRepository.save(empleadoOriginal);

    }

    public void actualizarEstado(Empleado empleado, int estadoId) {
        empleado.setEstadoId(estadoId);
        empleadoRepository.save(empleado);
    }

    public void borrarEmpleado(Empleado empleado) {
        this.actualizarEstado(empleado, 0);
    }

}
