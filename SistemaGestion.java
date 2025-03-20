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
    Map<Proyecto, List<Empleado>> proyectosHashMap;

    public SistemaGestion(){
        empleados = new ArrayList<>();
        proyectos = new ArrayList<>();
        proyectosHashMap = new HashMap<>();
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

        String id = solicitarInput("Introduce el id: ");
        


        Empleado nuevoEmpleado;

        switch (puesto) {
            case GERENTE:
                String area = solicitarInput("Ingrese el área del gerente: ");
                nuevoEmpleado = new Gerente(id, nombre, antiguedad, sueldo, area);
                break;
            case DESARROLLADOR:
                CategoriaEmpleado categoria = (antiguedad >= 2 ? CategoriaEmpleado.JUNIOR : CategoriaEmpleado.SENIOR);
                nuevoEmpleado = new Desarrollador(id, nombre, antiguedad, sueldo, categoria);
                break;
            case TESTER:
                nuevoEmpleado = new Tester(id, nombre, antiguedad, sueldo);
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
        // Mostramos todos lo proyectos
        mostrarProyectos();

        // Posición del proyecto seleccionadop
        int nP=0;
        boolean continuar = true;
        // Seleccionamos el proyecto correspondiente
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );
            if( opcion >= 0 && opcion<empleados.size()){
                if(proyectos.get(opcion).getEstado().equals(EstadoProyecto.NUEVO)&& !proyectos.get(opcion).getEstado().equals(EstadoProyecto.CANCELADO)){
                    nP = opcion;
                    continuar = false;
                }else{
                    JOptionPane.showMessageDialog(null, "El proyecto seleccionado debe estar en estado "+EstadoProyecto.NUEVO);
                }       
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }

        // Solicitamos las fechas correspondientes
        continuar = true;
        while (continuar) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaIniStr = solicitarInput("Introduce la fecha de inicio del proyecto (dd-MM-yyyy)");
                proyectos.get(nP).setFechaInicio(LocalDate.parse(fechaIniStr, formatter));
    
                String fechaEntregaStr = solicitarInput("Introduce la fecha de entrega máxima del proyecto (dd-MM-yyyy)");
                proyectos.get(nP).setFechaEntregaMaxima(LocalDate.parse(fechaEntregaStr, formatter));
    
                proyectos.get(nP).setEstado(EstadoProyecto.PLANIFICADO);
                continuar = false;
    
            } catch (Exception e) {
                System.out.println("Fecha invalida.");
            }
        }
        JOptionPane.showMessageDialog(null, "Se ha planificado el proyecto "+proyectos.get(nP).getNombre());
        
    }
    

    @OperacionesPermitidas(descripcion = "Mostrar proyectos")
    public void mostrarProyectos(){
        // Comprobamos que este vacio
        if(proyectos.isEmpty()){
            System.out.println("No hay proyectos registrados");
        }else{
            // Si no lo está, lo recorremos y usamos mostrar detalles
            StringBuilder sb = new StringBuilder();
            for(Proyecto p : proyectos){
                sb.append(p.mostrarDetalles()).append("\n");
                List<Empleado> empleados = proyectosHashMap.getOrDefault(p, new ArrayList<>());

                for (Empleado empleado : empleados) {
                    sb.append(empleado.mostrarDetalles()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    @OperacionesPermitidas(descripcion = "Comenzar proyecto")
    public void comenzarProyecto(){
        private List<Empleado> empleadosProyecto = ArrayList<>();
        Proyecto proyecto;

        //mostrarProyectos();
        int nP=0;
        boolean continuar = true;
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );

            if( opcion >= 0 && opcion<proyectos.size()){
                nP = opcion;
                proyecto = proyectos.get(opcion);
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

        proyectosHashMap.put(proyecto, empleadosProyecto);
        mostrarProyectos();
    }
    

























































































































































































    public void finalizarProyecto(){
        // Mostramos todos lo proyectos
        mostrarProyectos();
        // Posición del proyecto seleccionadop
        int nP=0;
        boolean continuar = true;
        // Seleccionamos el proyecto correspondiente. Debe ser 
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );
            if( opcion >= 0 && opcion<empleados.size()){
                if(proyectos.get(opcion).getEstado().equals(EstadoProyecto.DESARROLLO)){
                    nP = opcion;
                    continuar = false;
                }else{
                    JOptionPane.showMessageDialog(null, "El proyecto seleccionado debe estar en estado "+EstadoProyecto.DESARROLLO);
                }       
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }

        proyectos.get(nP).setFechaEntrega(LocalDate.now());

        JOptionPane.showMessageDialog(null, "Se ha entregado el proyecto "+proyectos.get(nP).getNombre());

    }






    @OperacionesPermitidas(descripcion = "Mostrar proyectos")
    public void aprobarProyecto(){
        // Mostramos todos lo proyectos
        mostrarProyectos();

        // Posición del proyecto seleccionadop
        int nP=0;
        boolean continuar = true;
        int calificación = 0;
        // Seleccionamos el proyecto correspondiente. Debe estar en estado Finalizado
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );
            if( opcion >= 0 && opcion<empleados.size()){
                if(proyectos.get(opcion).getEstado().equals(EstadoProyecto.FINALIZADO)){
                    nP = opcion;
                    continuar = false;
                }else{
                    JOptionPane.showMessageDialog(null, "El proyecto seleccionado debe estar en estado "+EstadoProyecto.FINALIZADO);
                }       
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }
        // Solicitamos la calificación del proyecto siendo un número del 0 al 10
        continuar = true;
        while (continuar) {
            calificación = solicitarEntero("Introduce la calificación del proyecto[0,10]: ");
            if(calificación>=0 && calificación <= 10) continuar  = false;
            else JOptionPane.showMessageDialog(null, "Introduce una calificación válida");
        }
        // Cambiamos el estado y añadimos la calificación
        proyectos.get(nP).setCalificación(calificación);
        proyectos.get(nP).setEstado(EstadoProyecto.APROBADO);
        JOptionPane.showMessageDialog(null, "Se ha aprobado el proyecto "+proyectos.get(nP).getNombre());
            
    }

    public void cancelarProyecto(){
        int nP=0;
        boolean continuar = true;
        // Seleccionamos el proyecto correspondiente. Debe ser uno que no este en estado Aprobado
        while (continuar) {
            int opcion = solicitarEntero("Introduce un proyecto siendo un número del 0 al "+ (proyectos.size()-1) );
            if( opcion >= 0 && opcion<empleados.size()){
                // Si el proyecto NO está aprobado
                if(! proyectos.get(opcion).getEstado().equals(EstadoProyecto.APROBADO)){
                    nP = opcion;
                    continuar = false;
                }else{
                    JOptionPane.showMessageDialog(null, "El proyecto seleccionado NO debe estar en estado "+EstadoProyecto.APROBADO);
                }       
            }
            else JOptionPane.showMessageDialog(null,"Número de proyecto no valido");   
        }

        proyectos.get(nP).setEstado(EstadoProyecto.CANCELADO);
        JOptionPane.showMessageDialog(null, "Se ha cancelado el proyecto "+proyectos.get(nP).getNombre());

    }


    public void eliminarEmpleado(){
        mostrarEmpleados();
        int encontrando =  -1;
        String id = JOptionPane.showInputDialog(null, "Introduce el id del empleado a eliminar");
        for(int i = 0; i<empleados.size();i++){
            if(empleados.get(i).getId() == id) {
                encontrando =i;
            }
        }
        if(encontrando==-1) JOptionPane.showMessageDialog(null, "El empleado no ha sido encontrando.");
        else{
            empleados.remove(encontrando);
            JOptionPane.showMessageDialog(null, "El empleado "+encontrando+" ha sido eliminado");
        
        }
    }

}
