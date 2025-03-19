public class Desarrollador extends Empleado{

    private CategoriaEmpleado categoria;

    public Desarrollador (String nombre, int antiguedad, CategoriaEmpleado categoria){
        super(nombre, antiguedad);
        this.categoria = categoria;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }
    
}
