import java.time.LocalDate;

public class Proyecto {
    private String nombre;
    private int prioridad;
    private LocalDate fechaInicio;
    private LocalDate fechaEntregaMaxima;
    private LocalDate fechaEntrega;
    private int calificación;
    private EstadoProyecto estado;


    public Proyecto(String nombre, int prioridad, EstadoProyecto estado) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
        this.calificación = -1;
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
        sb.append("-Estado: ").append(estado.toString()).append("\n");
       
        if(fechaInicio == null) sb.append("-Fecha inicio: ").append("Aún no hay fecha inicio").append("\n");
        else  sb.append("-Fecha inicio: ").append(fechaInicio.toString()).append("\n");

        if(fechaEntregaMaxima == null) sb.append("-Fecha entrega máxima: ").append("Aún no hay fecha máxima").append("\n");
        else sb.append("-Fecha entrega máxima: ").append(fechaEntregaMaxima.toString()).append("\n");
        
        if(fechaEntrega == null) sb.append("-Fecha de finalización: ").append("Aún no no se entregó").append("\n");
        else sb.append("-Fecha entrega máxima: ").append(fechaEntregaMaxima.toString()).append("\n");
        
        if(calificación == -1) sb.append("-Calificación: ").append("Aún no está calificado").append("\n");
        else sb.append("-Calificación: ").append(fechaEntrega.toString()).append("\n");


        return sb.toString();
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }




    
    
}
