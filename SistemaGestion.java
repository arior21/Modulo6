import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class SistemaGestion {
    private List<Empleado> empleados;
    private List<Proyecto> proyectos;

    public SistemaGestion(){
        empleados = new ArrayList<>();
        proyectos = new ArrayList<>();
    }

    private String solicitarInput(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }

    private double solicitarDouble(String mensaje){
        while (true) {
            try{
                return Double.parseDouble(solicitarInput(mensaje));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
            }
        }
    }
    private int solicitarEntero(String mensaje){
        while (true) {
            try{
                return Integer.parseInt(solicitarInput(mensaje));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese un número entero válido");
            }
        }
    }

    @OperacionesPermitidas(descripcion = "Agregar un empleado a la lista")
    public void agregarEmpleado(){
        String nombre = solicitarInput("Ingrese el nombre del empleado");

        String[] puestos = {"GERENTE", "DESARROLLADOR", "TESTER"};

        String puestoSeleccionado = (String) JOptionPane.showInputDialog(null,
                "Seleccione el puesto del empleado: ",
                "Puestos", JOptionPane.QUESTION_MESSAGE, null, puestos, puestos[0]);
                System.out.println("Empleado a agregar: " + nombre + "\nPuesto: " + puestoSeleccionado);

        PuestoEmpleado puesto = PuestoEmpleado.valueOf(puestoSeleccionado);

        int antiguedad = solicitarEntero("Ingrese la antigüedad del empleado: ");

        double sueldo = solicitarDouble("Ingrese el sueldo del empleado: ");


        Empleado nuevoEmpleado;

        switch (puesto) {
            case GERENTE:
                String area = solicitarInput("Ingrese el área del gerente: ");
                nuevoEmpleado = new Gerente(nombre, antiguedad, sueldo, area);
                break;
            case DESARROLLADOR:
                CategoriaEmpleado categoria = (antiguedad >= 2 ? CategoriaEmpleado.JUNIOR : CategoriaEmpleado.SENIOR);
                nuevoEmpleado = new Desarrollador(nombre, antiguedad, sueldo, categoria);
                break;
            case TESTER:
                nuevoEmpleado = new Tester(nombre, antiguedad, sueldo);
            break;
            default:
                throw new IllegalStateException("Puesto no válido: " + puesto);
        }
        empleados.add(nuevoEmpleado);
        System.out.println("Empleado agregado satisfactoriamente: " + nuevoEmpleado);
    }

    @OperacionesPermitidas(descripcion = "Mostrar empleados")
    public void mostrarEmpleados(){
        if(empleados.isEmpty()){
            System.out.println("No hay empleados registrados");
        }else{
            StringBuilder sb = new StringBuilder();
            for(Empleado p : empleados){
                sb.append(p.mostrarDetalles()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    @OperacionesPermitidas(descripcion = "Agregar proyecto")
    public void agregarProyecto(){
        String nombre = solicitarInput("Ingrese el nombre del proyecto: ");
        int prioridad = solicitarEntero("Ingrese la prioridad del proyecto: ");

        Proyecto nuevoProyecto = Proyecto(nombre, prioridad, EstadoProyecto.NUEVO);
        proyectos.add(nuevoProyecto);
        System.out.println("Proyecto agregado satisfactoriamente: " + nuevoProyecto);
    }

    @OperacionesPermitidas(descripcion = "Mostrar proyectos")
    public void mostrarProyectos(){
        if(proyectos.isEmpty()){
            System.out.println("No hay proyectos registrados");
        }else{
            StringBuilder sb = new StringBuilder();
            for(Proyecto p : proyectos){
                sb.append(p.mostrarDetalles()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}
