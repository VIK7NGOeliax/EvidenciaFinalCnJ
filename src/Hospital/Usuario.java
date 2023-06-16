package Hospital;

public class Usuario {
    private String nombre;
    private String clave;

    public Usuario(String nm, String cl){
        nombre = nm;
        clave = cl;
    }


    @Override
    public boolean equals(Object frasco){
        if(frasco instanceof Usuario){
            Usuario nadmin = (Usuario)frasco;
            if(nombre.equals(nadmin.nombre) && clave.equals(nadmin.clave))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
