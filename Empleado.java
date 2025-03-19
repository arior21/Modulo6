public class Empleado {
    private String nombre;
    private int antiguedad;
    private double sueldo;
    private PuestoEmpleado puesto;   

    public Empleado(String nombre, int antiguedad, double sueldo, PuestoEmpleado puesto) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
        this.sueldo = sueldo;
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }


    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }


    public String mostrarDetalles(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Antigüedad: ").append(antiguedad).append("\n");
        sb.append("Sueldo: ").append(sueldo).append("\n");
        return sb.toString();
    }

}
