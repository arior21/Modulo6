import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        Map<Integer, Consumer<Void>> menuOpciones = new HashMap<>();

        menuOpciones.put(1, (v) -> sistema.agregarEmpleado());
        menuOpciones.put(2, (v) -> sistema.agregarProyecto());
        menuOpciones.put(3, (v) -> sistema.planificarProyecto());
        menuOpciones.put(4, (v) -> sistema.comenzarProyecto());
        menuOpciones.put(5, (v) -> sistema.finalizarProyecto());
        menuOpciones.put(6, (v) -> sistema.aprobarProyecto());
        menuOpciones.put(7, (v) -> sistema.cancelarProyecto());
        menuOpciones.put(8, (v) -> sistema.mostrarProyectos());
        menuOpciones.put(9, (v) -> sistema.mostrarEmpleados());
        menuOpciones.put(10, (v) -> sistema.eliminarEmpleado());


        boolean continuar = true;
    
        while (continuar) {
            String opcion = JOptionPane.showInputDialog(
                "1. Agregar empleados\n"+
                "2. Registrar proyecto\n"+
                "3. Planificar proyecto\n"+
                "4. Comenzar proyecto\n"+
                "5. Entregar proyecto\n"+
                "6. Finalizar proyecto\n"+
                "7. Cancelar proyecto\n"+
                "8. Mostrar todos los proyectos\n"+
                "9. Mostrar empleados\n"+
                "10. Eliminar empleado\n"+
                "11. Salir\n"
            );
            try{
                int opcionInt = Integer.parseInt(opcion);
                Consumer<Void> operacion = menuOpciones.get(opcionInt);
                if(operacion != null){
                    operacion.accept(null);
                }else if(opcionInt == 11){
                    continuar = false;
                    System.out.println("Saliendo del sistema tienda...");
                }else{
                    System.out.println("Opción no válida");
                }

            }catch(NumberFormatException e){
                System.out.println("Error: ingrese un número valido");
            }
        }

        
    }
}
