package ar.com.ada.api.rrhh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import ar.com.ada.api.rrhh.entities.Categoria;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.models.requests.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.rrhh.models.requests.SueldoInfoRequest;
import ar.com.ada.api.rrhh.models.responses.GenericResponse;
import ar.com.ada.api.rrhh.services.CategoriaService;
import ar.com.ada.api.rrhh.services.EmpleadoService;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoBasicaEmpleadoRequest info) {

        Empleado empleado = new Empleado();
        empleado.setNombre(info.nombre);
        empleado.setEdad(info.edad);
        empleado.setSueldo(info.sueldo);
        empleado.setCategoria(categoriaService.buscarCategoriaPorId(info.categoriaId));
        empleado.setFechaAlta(new Date());
        empleado.setEstadoId(1);
        empleadoService.crearEmpleado(empleado);

        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = empleado.getEmpleadoId();
        resp.message = "Empleado generado con exito";

        return ResponseEntity.ok(resp);

    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados() {

        return ResponseEntity.ok(empleadoService.ListarEmpleados());

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable int id) {

        Empleado empleado = empleadoService.traerEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        // return ResponseEntity.notFound().build();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/empleados/categorias/{catId}")
    public ResponseEntity<List<Empleado>> listarEmpleadosPorCategoriaId(@PathVariable int catId) {

        List<Empleado> listaEmpleados = categoriaService.buscarCategoriaPorId(catId).getEmpleados();

        return ResponseEntity.ok(listaEmpleados);
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> actualizarSueldoEmpleado(@PathVariable int id, @RequestBody SueldoInfoRequest infoNueva) {

        Empleado empleadoOriginal = empleadoService.traerEmpleadoPorId(id);

        if (empleadoOriginal != null) {

            empleadoService.actualizarSueldoEmpleado(empleadoOriginal, infoNueva.sueldoNuevo);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = empleadoOriginal.getEmpleadoId();
            resp.message = "Empleado actualizado exitosamente";

            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable int id) {
        Empleado empleado = empleadoService.traerEmpleadoPorId(id);

        if (empleado != null) {
            empleadoService.borrarEmpleado(empleado);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = empleado.getEmpleadoId();
            resp.message = "Empleado eliminado con exito";

            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
