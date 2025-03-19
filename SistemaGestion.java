import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Proyecto nuevoProyecto = new Proyecto(nombre, prioridad, EstadoProyecto.NUEVO);
        proyectos.add(nuevoProyecto);
        System.out.println("Proyecto agregado satisfactoriamente: " + nuevoProyecto);

    }




    @OperacionesPermitidas(descripcion = "Planificamos el proyecto")
    public void planificarProyecto(){
        //mostrarProyectos();
        int nP=0;
        boolean continuar = true;
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (empleados.size()-1) );

            if( opcion >= 0 && opcion<empleados.size()){
                nP = opcion;
                continuar = false;
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }


        try {
            String fechaIniStr = solicitarInput("Introduce la fecha de inicio del proyecto (dd-MM-yyyy)");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            proyectos.get(nP).setFechaInicio(LocalDate.parse(fechaIniStr, formatter));
            
        } catch (Exception e) {
            System.out.println("Fecha invalida.");
        }
    }

    @OperacionesPermitidas(descripcion = "Comenzar proyecto")
    public void comenzarProyecto(){
        private List<Empleado> empleadosProyecto = ArrayList<>();
        Map<Proyecto, List<Empleado>> proyecto = new HashMap<>();

        //mostrarProyectos();
        int nP=0;
        boolean continuar = true;
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );

            if( opcion >= 0 && opcion<proyectos.size()){
                nP = opcion;
                continuar = false;
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }
        
        boolean añadirEmpleados = true;
        while (añadirEmpleados){
            String nombreEmpleado = solicitarInput("Introduzca el nombre del empleado que va a participar en el proyecto");
            for(Empleado e : empleados){
                if(nombreEmpleado.equals(e.getNombre())){
                    if(empleadosProyecto.contains(e)){
                        JOptionPane.showMessageDialog(null, "El empleado " + nombreEmpleado + "ya está participando en el proyecto.");
                    }else{
                        empleadosProyecto.add(e);
                    } 
                }
            }

            String añadirEmpleadosString;
            do {
                añadirEmpleadosString = solicitarInput("¿Desea añadir más empleados al proyecto? (y/n)").toLowerCase();
            } while (!añadirEmpleadosString.equals("y") && !añadirEmpleadosString.equals("n"));
            
            añadirEmpleados = añadirEmpleadosString.equals("y");
        }
        proyecto.mostrarDetalles();
        
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
