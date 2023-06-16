package Hospital;

public class Cita {
    public Cita(String a, String c, String d, String g){
        medico = a;
        paciente = c;
        fecha = d;
        motivo = g;
    }

    public String toString(){
        return "Medico: " + medico + " Paciente " + paciente + " Fecha Hora: " + fecha + "Motivo de consulta: " + motivo;
    }

    private String medico;
    private String paciente;
    private String fecha;
    private String motivo;
}
