import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Consumer<Void>> menuOpciones = new HashMap<>();

        //menuOpciones.put(1, (v) -> sistema.agregarProducto());
        //menuOpciones.put(2, (v) -> sistema.mostrarInventario());
        //menuOpciones.put(3, (v) -> sistema.aplicarDescuentoTodos());
        //menuOpciones.put(4, (v) -> sistema.agregarProducto());
        //menuOpciones.put(5, (v) -> sistema.mostrarInventario());
        //menuOpciones.put(6, (v) -> sistema.aplicarDescuentoTodos());
        //menuOpciones.put(7, (v) -> sistema.agregarProducto());
        //menuOpciones.put(8, (v) -> sistema.mostrarInventario());
        //menuOpciones.put(9, (v) -> sistema.aplicarDescuentoTodos());


        boolean continuar = true;

        while (continuar) {
            String opcion = sistema.solicitarInput(
                "1. Agregar empleados\n"+
                "2. Registrar proyecto\n"+
                "3. Planificar proyecto\n"+
                "4. Comenzar proyecto\n"+
                "5. Entregar proyecto\n"+
                "6. Finalizar proyecto\n"+
                "7. Cancelar proyecto\n"+
                "8. Mostrar todos los proyectos\n"+
                "9. Mostrar empleados\n"+
                "10. Salir\n"
            );
            try{
                int opcionInt = Integer.parseInt(opcion);
                Consumer<Void> operacion = menuOpciones.get(opcion);
                if(operacion != null){
                    operacion.accept(null);
                }else if(opcionInt == 10){
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
