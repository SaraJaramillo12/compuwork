package compuwork.model;

import java.util.ArrayList;

public class Departamento {

    // Atributos
    private String id;
    private String nombre;
    private String descripcion;
    private ArrayList<Empleado> empleados;
    private int capacidadMaxima;

    // Constructor
    public Departamento(String id, String nombre, String descripcion, int capacidadMaxima) {
        this.id             = id;
        this.nombre         = nombre;
        this.descripcion    = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.empleados      = new ArrayList<>();
    }

    // Agrega un empleado al departamento
    public void agregarEmpleado(Empleado empleado) {
        if (empleados.size() >= capacidadMaxima) {
            System.out.println("Error: el departamento " + nombre + " esta lleno.");
            return;
        }
        if (empleados.contains(empleado)) {
            System.out.println("Error: el empleado ya esta asignado a este departamento.");
            return;
        }
        empleados.add(empleado);
        empleado.setDepartamento(nombre);
        System.out.println("Empleado " + empleado.getNombreCompleto() + " agregado a " + nombre);
    }

    // Elimina un empleado del departamento
    public void eliminarEmpleado(Empleado empleado) {
        if (empleados.remove(empleado)) {
            empleado.setDepartamento("Sin asignar");
            System.out.println("Empleado " + empleado.getNombreCompleto() + " removido de " + nombre);
        } else {
            System.out.println("Error: el empleado no pertenece a este departamento.");
        }
    }

    // Muestra todos los empleados del departamento
    public void listarEmpleados() {
        System.out.println("Empleados del departamento " + nombre + ":");
        if (empleados.isEmpty()) {
            System.out.println("  No hay empleados asignados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println("  - " + e.getNombreCompleto() + " | " + e.getTipoContrato());
        }
    }

    // Calcula la masa salarial total del departamento
    public double calcularMasaSalarial() {
        double total = 0;
        for (Empleado e : empleados) {
            total += e.calcularSalario();
        }
        return total;
    }

    // Retorna el numero de empleados actuales
    public int getTotalEmpleados() {
        return empleados.size();
    }

    // Getters
    public String getId()                      { return id; }
    public String getNombre()                  { return nombre; }
    public String getDescripcion()             { return descripcion; }
    public ArrayList<Empleado> getEmpleados()  { return empleados; }
    public int getCapacidadMaxima()            { return capacidadMaxima; }

    // Setters
    public void setNombre(String nombre)             { this.nombre = nombre; }
    public void setDescripcion(String descripcion)   { this.descripcion = descripcion; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    // Muestra la informacion del departamento
    @Override
    public String toString() {
        return "ID: " + id +
               " | Departamento: " + nombre +
               " | Empleados: " + empleados.size() + "/" + capacidadMaxima +
               " | Masa salarial: $" + calcularMasaSalarial();
    }
}