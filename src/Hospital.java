package Hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hospital {
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    public static HashMap<Integer, Paciente> listaPacientes = new HashMap<Integer, Paciente>();
    public static HashMap<Integer, Medico> listaMedicos = new HashMap<Integer, Medico>();
    public static HashMap<Integer, Cita> listaCitas = new HashMap<Integer, Cita>();
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        crearOpciones(listaPacientes, listaMedicos);

        if(autorizarAcceso()){
            System.out.println("Acceso autorizado");
            menu();
        }
        else
            System.out.println("\nAcceso denegado");

        System.out.println("Fin");
    }

    public static void crearOpciones(HashMap listaPacientes, HashMap listaMedicos){

        String inputFilenameMedicos = "C:\\Users\\eliax\\Documents\\Actividades GitGit\\Listas\\Medicos.txt\\Medicos1.txt";
        String inputFilenamePacientes = "C:\\Users\\eliax\\Documents\\Actividades GitGit\\Listas\\Pacientes.txt\\Pacientes1.txt";
        BufferedReader bufferedReader = null;
        String Nombre = "";
        String Especialidad = "";
        try{
            bufferedReader = new BufferedReader(new FileReader(inputFilenameMedicos));

            String luna;
            while ((luna = bufferedReader.readLine()) != null){
                int espacio = luna.indexOf(',');
                Nombre = luna.substring(0, espacio);
                Especialidad = luna.substring(espacio+1, luna.length());
                int contra = listaMedicos.size();
                listaMedicos.put(contra+1, new Medico(Nombre, Especialidad));
            }
        }catch(IOException e){
            System.out.println("IOException catched while reading: " + e.getMessage());
        }finally{
            try{
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

        try{
            bufferedReader = new BufferedReader(new FileReader(inputFilenamePacientes));
            String luna;
            while ((luna = bufferedReader.readLine()) != null){
                Nombre = luna;
                int contra = listaPacientes.size();
                listaPacientes.put(contra+1, new Paciente(luna));
            }
        }catch(IOException e){
            System.out.println("IOException catched while reading: " + e.getMessage());
        }finally{
            try{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

    private static boolean autorizarAcceso( ){
        usuarios.add(new Usuario("admin", "admin"));
        usuarios.add(new Usuario("admin2", "admin2"));
        usuarios.add(new Usuario("admin3", "admin3"));

        System.out.println("****AGENDAR CITA****");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Clave: ");
        String clave = teclado.nextLine();

        Usuario admin = new Usuario(nombre, clave);

        return usuarios.contains(admin);
    }

    private static void menu(){
        Scanner stray = new Scanner(System.in);
        boolean salir = false;
        int decida;

        while (!salir){
            System.out.println("\n Seleccione una opcion");
            System.out.println("1. Doctor");
            System.out.println("2. Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Salir");
            try{
                System.out.println("Escriba la opcion:");
                decida = stray.nextInt();
                int contra = 0;
                String nombre = "";
                switch(decida){
                    case 1:
                        String especialidad = "";
                        System.out.println("Nombre del Doctor: ");
                        nombre = teclado.nextLine();
                        System.out.println("Especialidad: ");
                        especialidad = teclado.nextLine();
                        contra = listaMedicos.size();
                        listaMedicos.put(contra+1, new Medico(nombre, especialidad));
                        break;
                    case 2:
                        System.out.println("Nombre del Paciente: ");
                        nombre = teclado.nextLine();
                        contra = listaPacientes.size();
                        listaPacientes.put(contra+1, new Paciente(nombre));
                    case 3:
                        int medico;
                        int paciente;
                        String fecha;
                        String causa;
                        boolean valido = false;
                        do{
                            System.out.println("LISTA DE DOCTORES");
                            for (Iterator<Map.Entry<Integer, Medico>> enlista = listaMedicos.entrySet().iterator(); enlista.hasNext(); ){
                                Map.Entry<Integer, Medico> entry = enlista.next();
                                String sacando = String.format("%s. %s", entry.getKey(), entry.getValue());
                                System.out.println(sacando);
                            }
                            System.out.println("Seleccione al Doctor: ");
                            medico = Integer.parseInt(teclado.nextLine());
                            valido = listaMedicos.containsKey(medico);
                        }while(valido != true);
                        valido = false;
                        do{
                            System.out.println("LISTA DE PACIENTES");
                            for (Iterator<Map.Entry<Integer, Paciente>> enlista = listaPacientes.entrySet().iterator(); enlista.hasNext();){
                                Map.Entry<Integer, Paciente> entry = enlista.next();
                                String sacando = String.format("%s. %s", entry.getKey(), entry.getValue());
                                System.out.println(sacando);
                            }
                            System.out.println("Selecione el paciente: ");
                            paciente = Integer.parseInt(teclado.nextLine());
                            valido = listaPacientes.containsKey(paciente);
                        }while(valido != true);
                        valido = false;
                        Date cate = null;
                        do{
                            System.out.println("Seleccione la Fecha: ");
                            System.out.println("Introduzca la fecha en yyyy-MM-dd/HH:mm:ss");
                            Scanner fechado = new Scanner(System.in);

                            fecha = fechado.nextLine();
                            SimpleDateFormat dafo = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
                            String detalle = fecha;
                            try{
                                cate = dafo.parse(detalle);
                                valido = true;
                            }catch (Exception e){ System.out.println("Formato invalido de fecha");}

                            if (dafo != null){
                                if(!dafo.format(cate).equals(detalle)){
                                    System.out.println("Formato invalido de fecha");
                                }else{
                                    valido = true;
                                }
                            }
                        }while(valido != true);
                        System.out.println("Motivo de la cita: ");
                        causa = teclado.nextLine();
                        contra = listaCitas.size();
                        String Medico = listaMedicos.get(medico).toString();
                        int espacio = Medico.indexOf(',');
                        Medico = Medico.substring(espacio+2, Medico.length()).toString();
                        espacio = Medico.indexOf(',');
                        Medico = Medico.substring(0, espacio).toString();
                        Medico = Medico.substring(0, Medico.length()-13);
                        String Paciente = listaPacientes.get(paciente).toString();
                        espacio = Paciente.indexOf(',');
                        Paciente = Paciente.substring(espacio+2, Paciente.length()).toString();
                        listaCitas.put(contra+1, new Cita(Medico, Paciente, cate.toString(), causa));
                        System.out.println(listaCitas.get(contra+1));
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo numero del 1 al 4");
                }
            }catch (InputMismatchException e){
                System.out.println("Tiene que insertar un numero");
                stray.next();
            }
        }
    }
}
