public class Desarrollador extends Empleado{

    private CategoriaEmpleado categoria;

    public Desarrollador (String nombre, int antiguedad, double sueldo, CategoriaEmpleado categoria){
        super(nombre, antiguedad, sueldo, PuestoEmpleado.DESARROLLADOR);
        this.categoria = categoria;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String mostrarDetalles(){
        return super.toString() + "\nCategoría: " + categoria;
    }
}
