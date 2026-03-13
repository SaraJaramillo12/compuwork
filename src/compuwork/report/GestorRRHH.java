package compuwork.report;

import compuwork.exception.DepartamentoException;
import compuwork.exception.EmpleadoNoEncontradoException;
import compuwork.model.Departamento;
import compuwork.model.Empleado;

import java.util.ArrayList;

public class GestorRRHH {

    // Lista de empleados y departamentos registrados
    private ArrayList<Empleado> empleados;
    private ArrayList<Departamento> departamentos;

    // Constructor
    public GestorRRHH() {
        this.empleados     = new ArrayList<>();
        this.departamentos = new ArrayList<>();
    }

    // ───────────────────────────────────────────────── Gestion de empleados ─────────────────────────────────────────────────

    // Registra un nuevo empleado en el sistema
    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado registrado: " + empleado.getNombreCompleto());
    }

    // Elimina un empleado del sistema por su ID
    public void eliminarEmpleado(String id) throws EmpleadoNoEncontradoException {
        Empleado empleado = buscarEmpleadoPorId(id);
        empleados.remove(empleado);
        System.out.println("Empleado eliminado: " + empleado.getNombreCompleto());
    }

    // Busca un empleado por su ID
    public Empleado buscarEmpleadoPorId(String id) throws EmpleadoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        throw new EmpleadoNoEncontradoException("No se encontro empleado con ID: " + id);
    }

    // Muestra todos los empleados registrados
    public void listarEmpleados() {
        System.out.println("\n===== LISTA DE EMPLEADOS =====");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println(e);
        }
        System.out.println("==============================\n");
    }

    // ───────────────────────────────────────────── Gestion de departamentos ─────────────────────────────────────────────

    // Registra un nuevo departamento en el sistema
    public void registrarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
        System.out.println("Departamento registrado: " + departamento.getNombre());
    }

    // Elimina un departamento del sistema por su ID
    public void eliminarDepartamento(String id) throws DepartamentoException {
        Departamento departamento = buscarDepartamentoPorId(id);
        if (departamento.getTotalEmpleados() > 0) {
            throw new DepartamentoException(
                "No se puede eliminar el departamento " + departamento.getNombre() +
                " porque tiene " + departamento.getTotalEmpleados() + " empleado(s) asignado(s)."
            );
        }
        departamentos.remove(departamento);
        System.out.println("Departamento eliminado: " + departamento.getNombre());
    }

    // Busca un departamento por su ID
    public Departamento buscarDepartamentoPorId(String id) throws DepartamentoException {
        for (Departamento d : departamentos) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        throw new DepartamentoException("No se encontro departamento con ID: " + id);
    }

    // Muestra todos los departamentos registrados
    public void listarDepartamentos() {
        System.out.println("\n===== LISTA DE DEPARTAMENTOS =====");
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
            return;
        }
        for (Departamento d : departamentos) {
            System.out.println(d);
            d.listarEmpleados();
        }
        System.out.println("==================================\n");
    }

    // ──────────────────────────────────────────────Asignacion de empleados ──────────────────────────────────────────────

    // Asigna un empleado a un departamento
    public void asignarEmpleadoADepartamento(String idEmpleado, String idDepartamento)
            throws EmpleadoNoEncontradoException, DepartamentoException {
        Empleado empleado       = buscarEmpleadoPorId(idEmpleado);
        Departamento departamento = buscarDepartamentoPorId(idDepartamento);
        departamento.agregarEmpleado(empleado);
    }

    // Transfiere un empleado de un departamento a otro
    public void transferirEmpleado(String idEmpleado,
                                    String idDepartamentoOrigen,
                                    String idDepartamentoDestino)
            throws EmpleadoNoEncontradoException, DepartamentoException {
        Empleado empleado            = buscarEmpleadoPorId(idEmpleado);
        Departamento origen          = buscarDepartamentoPorId(idDepartamentoOrigen);
        Departamento destino         = buscarDepartamentoPorId(idDepartamentoDestino);
        origen.eliminarEmpleado(empleado);
        destino.agregarEmpleado(empleado);
        System.out.println("Empleado " + empleado.getNombreCompleto() +
                           " transferido a " + destino.getNombre());
    }
}