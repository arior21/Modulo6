public class Gerente extends Empleado {
    private String area;

    public Gerente(String nombre, int antiguedad, String area) {
        super(nombre, antiguedad);
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
