import java.time.LocalDate;

public class Proyecto {
    private String nombre;
    private int prioridad;
    private LocalDate fechaInicio;
    private LocalDate fechaEntregaMaxima;
    private LocalDate fechaEntrega;
    private int calificación;
    private EstadoProyecto estado;

    


    public Proyecto(String nombre, int prioridad, LocalDate fechaInicio, EstadoProyecto estado) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaEntregaMaxima() {
        return fechaEntregaMaxima;
    }

    public void setFechaEntregaMaxima(LocalDate fechaEntregaMaxima) {
        this.fechaEntregaMaxima = fechaEntregaMaxima;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }

    public String mostrarDetalles(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre proyecto: ").append(nombre).append("\n");
        sb.append("-Prioridad: ").append(prioridad).append("\n");

        return sb.toString();
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }




    
    
}
