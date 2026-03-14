package compuwork;

import compuwork.exception.DepartamentoException;
import compuwork.exception.EmpleadoNoEncontradoException;
import compuwork.model.*;
import compuwork.report.GestorRRHH;

public class Main {

    public static void main(String[] args) {

        GestorRRHH gestor = new GestorRRHH();

        // ── Crear departamentos ──────────────────────────────────────────────
        System.out.println("===== REGISTRANDO DEPARTAMENTOS =====");
        Departamento dTI     = new Departamento("D001", "Tecnologia",       "Desarrollo de software", 10);
        Departamento dVentas = new Departamento("D002", "Ventas",           "Gestion comercial",       5);
        Departamento dRRHH   = new Departamento("D003", "Recursos Humanos", "Gestion del talento",     4);

        gestor.registrarDepartamento(dTI);
        gestor.registrarDepartamento(dVentas);
        gestor.registrarDepartamento(dRRHH);

        // ── Crear empleados ──────────────────────────────────────────────────
        System.out.println("\n===== REGISTRANDO EMPLEADOS =====");
        EmpleadoPermanente ep1 = new EmpleadoPermanente(
                "E001", "Laura", "Martinez", "laura@compuwork.com",
                4500000, 1200000, true, 20);

        EmpleadoPermanente ep2 = new EmpleadoPermanente(
                "E002", "Carlos", "Gomez", "carlos@compuwork.com",
                3800000, 900000, true, 15);

        EmpleadoTemporal et1 = new EmpleadoTemporal(
                "E003", "Ana", "Torres", "ana@compuwork.com", 0,
                "2025-12-31", 25000, 160, "TalentoPro");

        EmpleadoTemporal et2 = new EmpleadoTemporal(
                "E004", "Pedro", "Ruiz", "pedro@compuwork.com", 0,
                "2025-06-30", 22000, 140, "StaffPlus");

        gestor.registrarEmpleado(ep1);
        gestor.registrarEmpleado(ep2);
        gestor.registrarEmpleado(et1);
        gestor.registrarEmpleado(et2);

        // ── Asignar empleados a departamentos ────────────────────────────────
        System.out.println("\n===== ASIGNANDO EMPLEADOS =====");
        try {
            gestor.asignarEmpleadoADepartamento("E001", "D001");
            gestor.asignarEmpleadoADepartamento("E002", "D001");
            gestor.asignarEmpleadoADepartamento("E003", "D002");
            gestor.asignarEmpleadoADepartamento("E004", "D003");
        } catch (EmpleadoNoEncontradoException | DepartamentoException e) {
            System.out.println("Error al asignar: " + e.getMessage());
        }

        // ── Listar departamentos y empleados ─────────────────────────────────
        gestor.listarDepartamentos();

        // ── Generar reporte de desempeno ─────────────────────────────────────
        System.out.println("===== REPORTE DE DESEMPENO =====");
        ReporteDesempeno reporte = new ReporteDesempeno(
                "RPT-001", ep1, "2025-01-01", "Enero - Diciembre 2025");

        reporte.agregarMetrica(new MetricaDesempeno("Cumplimiento de objetivos", 9.0, 0.30, "Supero metas"));
        reporte.agregarMetrica(new MetricaDesempeno("Calidad del trabajo",       8.5, 0.25, "Minimos errores"));
        reporte.agregarMetrica(new MetricaDesempeno("Trabajo en equipo",         9.5, 0.20, "Excelente colaboracion"));
        reporte.agregarMetrica(new MetricaDesempeno("Puntualidad",              10.0, 0.15, "Sin ausencias"));
        reporte.agregarMetrica(new MetricaDesempeno("Iniciativa",                8.0, 0.10, "Propuso mejoras"));
        reporte.setComentarioGeneral("Laura es una empleada destacada.");

        System.out.println(reporte.generarReporte());

        // ── Prueba de excepciones ────────────────────────────────────────────
        System.out.println("\n===== PRUEBA DE EXCEPCIONES =====\n");

        // Prueba 1 - Buscar empleado inexistente
        System.out.println(">> Prueba 1: Buscar empleado con ID inexistente (E999)");
        try {
            gestor.buscarEmpleadoPorId("E999");
        } catch (EmpleadoNoEncontradoException e) {
            System.out.println("   [EmpleadoNoEncontradoException] " + e.getMessage());
        }

        // Prueba 2 - Eliminar departamento con empleados asignados
        System.out.println("\n>> Prueba 2: Eliminar departamento con empleados asignados (D001)");
        try {
            gestor.eliminarDepartamento("D001");
        } catch (DepartamentoException e) {
            System.out.println("   [DepartamentoException] " + e.getMessage());
        }

        // Prueba 3 - Asignar empleado a departamento inexistente
        System.out.println("\n>> Prueba 3: Asignar empleado a departamento inexistente (D999)");
        try {
            gestor.asignarEmpleadoADepartamento("E001", "D999");
        } catch (EmpleadoNoEncontradoException | DepartamentoException e) {
            System.out.println("   [DepartamentoException] " + e.getMessage());
        }

        // Prueba 4 - Transferir empleado a departamento inexistente
        System.out.println("\n>> Prueba 4: Transferir empleado a departamento inexistente");
        try {
            gestor.transferirEmpleado("E002", "D001", "D999");
        } catch (EmpleadoNoEncontradoException | DepartamentoException e) {
            System.out.println("   [DepartamentoException] " + e.getMessage());
        }

        System.out.println("\n===== FIN PRUEBA DE EXCEPCIONES =====");

        // ── Transferir empleado entre departamentos ───────────────────────────
        System.out.println("\n===== TRANSFERENCIA DE EMPLEADO =====");
        try {
            gestor.transferirEmpleado("E001", "D001", "D002");
        } catch (EmpleadoNoEncontradoException | DepartamentoException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        // ── Listar empleados al final ─────────────────────────────────────────
        gestor.listarEmpleados();

        System.out.println("\n===== SISTEMA COMPUWORK FINALIZADO =====");
    }
}