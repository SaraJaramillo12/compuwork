# CompuWork


## Integrantes
- Sebastian Castaño
- Sara Jaramillo

## Descripcion del proyecto
CompuWork es un sistema de gestion de recursos humanos desarrollado en **Java**
aplicando los principios de la **Programacion Orientada a Objetos (POO)**.
El sistema permite administrar empleados, organizar departamentos y generar
reportes de desempeno de manera eficiente.

El sistema maneja dos tipos de empleados:
- **Empleado Permanente:** tiene salario base, bonificacion anual, seguro medico y dias de vacaciones.
- **Empleado Temporal:** se paga por horas trabajadas y tiene fecha de fin de contrato.

## Estructura de carpetas
```
src/compuwork/
├── Main.java                          → Punto de entrada con menu interactivo
├── model/
│   ├── Empleado.java                  → Clase base abstracta
│   ├── EmpleadoPermanente.java        → Herencia de Empleado
│   ├── EmpleadoTemporal.java          → Herencia de Empleado
│   ├── Departamento.java              → Gestion de empleados por area
│   ├── MetricaDesempeno.java          → Metricas de evaluacion
│   └── ReporteDesempeno.java          → Generacion de reportes
├── exception/
│   ├── EmpleadoNoEncontradoException.java
│   ├── EmpleadoDuplicadoException.java
│   ├── DepartamentoException.java
│   ├── DepartamentoLlenoException.java
│   ├── SalarioInvalidoException.java
│   └── ReporteException.java
└── report/
    └── GestorRRHH.java           
```

## Principios POO aplicados

| Principio | Donde se aplica |
|---|---|
| **Encapsulamiento** | Atributos privados con getters y setters en todas las clases |
| **Herencia** | EmpleadoPermanente y EmpleadoTemporal extienden de Empleado |
| **Polimorfismo** | Metodos abstractos calcularSalario() y getTipoContrato() |
| **Abstraccion** | Clase Empleado como contrato abstracto para los tipos de empleado |

## Excepciones del sistema

| Excepcion | Cuando se lanza |
|---|---|
| `EmpleadoNoEncontradoException` | Cuando se busca un empleado con un ID que no existe |
| `EmpleadoDuplicadoException` | Cuando se registra un empleado con un ID ya existente |
| `DepartamentoException` | Cuando se elimina un departamento que tiene empleados asignados |
| `DepartamentoLlenoException` | Cuando se intenta agregar un empleado a un departamento lleno |
| `SalarioInvalidoException` | Cuando se ingresa un salario negativo |
| `ReporteException` | Cuando se genera un reporte sin metricas |

