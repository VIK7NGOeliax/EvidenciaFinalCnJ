package Hospital;

public class Medico {
    public Medico(String k, String e){
        nombre = k;
        especialidad = e;
    }

    public String toString(){
        return "Medico: " + nombre + "Especialidad: " + especialidad;
    }

    private String nombre;
    private String especialidad;
}
