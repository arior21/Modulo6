import java.time.LocalDate;

public class Proyecto {
    private String nombre;
    private int prioridad;
    private LocalDate fechaEntrega;

    public Proyecto(String nombre, int prioridad, LocalDate fechaEntrega) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.fechaEntrega = fechaEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    
    
}
