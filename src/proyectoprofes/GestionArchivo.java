package proyectoprofes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionArchivo {

    static final String FILE_PATH = "profesor.txt";

    //genera el alta de profesores en el txt Clase Profesor y crea variable miProfesor
    public static void altaProfesorArchivo(Profesor miProfesor) {
        //aca va todo el codigo para hacer el alta del profesor en el archivo
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            //buffer.wride()
            buffer.write(miProfesor.getNombre() + "," + miProfesor.getApellido() + "," + miProfesor.getEmail() + "," + miProfesor.getDni() + "," + miProfesor.getMateria());
            buffer.newLine();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //vamos a leer un archivo y mostrar los registros en pantalla
    //vamos a visualizar el arrayList de los registros
    //Leer profesor es leer una lista de profesores
    public static List<Profesor> leerProfesorArchivo() {
        List<Profesor> profesores = new ArrayList<>();
        try (BufferedReader leer = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                String[] textoArray = linea.split(",");
                String nombre = textoArray[0];
                String apellido = textoArray[1];
                String email = textoArray[2];
                String dni = textoArray[3];
                String materia = textoArray[4];
                Profesor miProfesor = new Profesor(nombre, apellido, email, dni, materia);
                profesores.add(miProfesor);
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
        return profesores;
    }

    public static void modificarProfeArchivo(int indice, Profesor miProfesor) {
        List<Profesor> profesores = leerProfesorArchivo(); // Obtener la lista de profesores desde el archivo
        if (indice >= 0 && indice < profesores.size()) {
            Profesor profesorModificado = new Profesor(
                    miProfesor.getNombre(),
                    miProfesor.getApellido(),
                    miProfesor.getEmail(),
                    miProfesor.getDni(),
                    miProfesor.getMateria()
            );
            profesores.set(indice, profesorModificado); // Modificar el profesor en la posición dada

            modificarProfesorArchivo(profesores); // Guardar cambios en el archivo
        } else {
            System.out.println("No se encontró el profesor a modificar");
        }
    }

    //trae la lista de registros
    static void modificarProfesorArchivo(List<Profesor> profesores) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Profesor profesor : profesores) {
                buffer.write(profesor.getNombre() + "," + profesor.getApellido() + "," + profesor.getEmail() + "," + profesor.getDni() + "," + profesor.getMateria());
                buffer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void eliminarProfesor(int indice) {
        List<Profesor> profesores = leerProfesorArchivo();
        if (indice >= 0 && indice < profesores.size()) {
            profesores.remove(indice);
            modificarProfesorArchivo(profesores);
        } else {
            System.out.println("no existe el profesor a eliminar");
        }
    }

}

