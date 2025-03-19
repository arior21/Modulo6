public class Gerente extends Empleado {
    private String area;

    public Gerente(String nombre, int antiguedad, double sueldo, String area) {
        super(nombre, antiguedad, sueldo, PuestoEmpleado.GERENTE);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String mostrarDetalles() {
        // TODO Auto-generated method stub
        return super.mostrarDetalles()+"Área: "+area+"\n";
    }

    
    
}
