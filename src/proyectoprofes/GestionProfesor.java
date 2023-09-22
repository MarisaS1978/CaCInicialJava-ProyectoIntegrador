package proyectoprofes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import static proyectoprofes.GestionArchivo.FILE_PATH;
import static proyectoprofes.Main.mostrarMenu;


public class GestionProfesor {

    //crea una instancia de la clase GestionArchivo y la asigna a la variable gestionArchivo
    //Me permite utilizar todo lo que tenga Gestion Archivo
    GestionArchivo gestionArchivo = new GestionArchivo();

    //crea una nueva instancia de ArrayList y la asigna a la variable listaProfesores.
    static List<Profesor> listaProfesores = new ArrayList<>();

    //constructor de la clase GestionProfesor
    public GestionProfesor() {
        this.listaProfesores = GestionArchivo.leerProfesorArchivo();
    }

    static String addProfesor() {
        String nombre, apellido, email, dni, materia;
        do {
            nombre = JOptionPane.showInputDialog(null, "Ingrese nombre: ", "", -1);
            if (nombre.length() < 3 || nombre.length() > 15) {
                JOptionPane.showMessageDialog(null, "El nombre debe tener entre 3 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        } while (true);

        do {
            apellido = JOptionPane.showInputDialog(null, "Ingrese apellido: ", "", -1);
            if (apellido.length() < 5 || apellido.length() > 15) {
                JOptionPane.showMessageDialog(null, "El apellido debe tener entre 3 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        } while (true);

        do {
            email = JOptionPane.showInputDialog(null, "Ingrese email: ", "", -1);
            if (!email.contains("@") || !email.contains(".") || email.length() >= 30) {
                JOptionPane.showMessageDialog(null, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        } while (true);


        do {
            dni = JOptionPane.showInputDialog(null, "Ingrese DNI: ", "", -1);
            if (dni.length() < 2 && dni.length() > 15) {
                JOptionPane.showMessageDialog(null, "El DNI debe tener entre 2 y 15 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!dni.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "El DNI debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        } while (true);

        do {
            materia = JOptionPane.showInputDialog(null, "Ingrese materia: ", "", -1);
            if (materia.length() < 2 && materia.length() > 15) {
                JOptionPane.showMessageDialog(null, "La materia debe tener entre 2 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        } while (true);
        int agregar = JOptionPane.showOptionDialog(null, "¿Desea agregar profesor?", "Agregar Profesor", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Agregar", "Cancelar"}, "");
        if (agregar == JOptionPane.YES_OPTION) {
            Profesor profesor = new Profesor(nombre, apellido, email, dni, materia);
            // Método que guarda el objeto en el archivo de texto
            GestionArchivo.altaProfesorArchivo(profesor);
            JOptionPane.showMessageDialog(null, "Operación exitosa", "Agregado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarMenu();
        }
        return "";
    }

    static String verListaProfesores() {
        //llama al método leerProfesorArchivo() de la clase GestionArchivo para obtener la lista de profesores desde el archivo.
        List<Profesor> profesores = GestionArchivo.leerProfesorArchivo();
        // Crear el modelo de tabla personalizado
        ProfesorTableModel tableModel = new ProfesorTableModel(profesores);

        // Crear la tabla y asignar el modelo
        JTable table = new JTable((TableModel) tableModel);

        JFrame frame = new JFrame("Lista de profesores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
        return "";
    }

    public static void modificarProfesor() {
        List<Profesor> profesores = listaProfesores;
        int indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Recuerde ver la lista antes de la modificacion\n"
                + "Ingrese la posición del profesor a modificar:", "Modificar Profesor", 0));
        if (indice >= 0 && indice < profesores.size()) {
            Profesor profesor = profesores.get(indice);
            int indi = Integer.parseInt(JOptionPane.showInputDialog(profesor + " \n ¿Qué dato desea modificar?\n"
                    + "1- Nombre\n"
                    + "2- Apellido\n"
                    + "3- Email\n"
                    + "4- Dni\n"
                    + "5- Materia\n"
                    + "0- Salir del menú"));
            switch (indi) {
                case 1:
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
                    profesor.setNombre(nuevoNombre);
                    if (nuevoNombre.length() < 2 || nuevoNombre.length() > 15) {
                        JOptionPane.showMessageDialog(null, "El nombre debe tener entre 2 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2:
                    String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido");
                    profesor.setApellido(nuevoApellido);
                    if (nuevoApellido.length() < 2 || nuevoApellido.length() > 15) {
                        JOptionPane.showMessageDialog(null, "El apellido debe tener entre 2 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:
                    String nuevoEmail = JOptionPane.showInputDialog("Ingrese el nuevo email");
                    profesor.setEmail(nuevoEmail);
                    if (nuevoEmail.length() < 2 || nuevoEmail.length() > 15) {
                        JOptionPane.showMessageDialog(null, "El email debe tener entre 2 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 4:
                    String nuevoDni = JOptionPane.showInputDialog("Ingrese el nuevo dni");
                    profesor.setDni(nuevoDni);
                    if (nuevoDni.length() < 2 || nuevoDni.length() > 15) {
                        JOptionPane.showMessageDialog(null, "El DNI debe tener entre 2 y 15 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!nuevoDni.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "El DNI debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 5:
                    String nuevoMateria = JOptionPane.showInputDialog("Ingrese la nueva materia");
                    profesor.setMateria(nuevoMateria);
                    if (nuevoMateria.length() < 2 || nuevoMateria.length() > 15) {
                        JOptionPane.showMessageDialog(null, "La materia debe tener entre 2 y 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

            actualizarProfesor(profesores);
            JOptionPane.showMessageDialog(null, "Profesor modificado exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún profesor con la posición especificada", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void deleteProfesor() {
        List<Profesor> profesores = listaProfesores;
        int indice;

        String input = JOptionPane.showInputDialog(null,
                "<html><body><font size='4'>Recuerde ver la lista antes de la modificación.<br>"
                        + "<font size='3'>Ingrese la posición del profesor a modificar:<br>"
                        + "<font size='2'>El primer registro es 0</font></body></html>",
                "Eliminar Profesor", JOptionPane.PLAIN_MESSAGE);

        // Comprobar si se ha seleccionado el botón de cancelar
        if (input == null) {
            mostrarMenu(); // Vuelve al menú principal
            return;
        }

        indice = Integer.parseInt(input);

        if (indice >= 0 && indice < profesores.size()) {
            Profesor profesor = profesores.get(indice);

            int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de que desea eliminar el siguiente profesor?\n\n" + profesor
                            + "\n\nPresione 'Si' para confirmar la eliminación.",
                    "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                profesores.remove(indice);
                actualizarProfesor(profesores);
                JOptionPane.showMessageDialog(null, "Profesor eliminado exitosamente",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                deleteProfesor(); // Vuelve al paso anterior
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún profesor con la posición especificada",
                    "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarProfesor(List<Profesor> profesores) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Profesor profesor : profesores) {
                buffer.write(profesor.getNombre() + "," + profesor.getApellido() + "," + profesor.getEmail() + "," + profesor.getDni() + "," + profesor.getMateria());
                buffer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Profesor buscarPorNombre(String nombre) {
        List<Profesor> profesores = GestionArchivo.leerProfesorArchivo();
        for (Profesor profesor : profesores) {
            if (profesor.getNombre().equalsIgnoreCase(nombre)) {
                return profesor;
            }
        }
        return null;
    }

    private static Profesor buscarPorDni(String dni) {
        List<Profesor> profesores = GestionArchivo.leerProfesorArchivo();
        for (Profesor profesor : profesores) {
            if (profesor.getDni().equals(dni)) {
                return profesor;
            }
        }
        return null;
    }

    public static void buscarProfesor() {
        String[] options = {"Nombre", "Dni"};
        int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción de búsqueda", "Búsqueda de profesor",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (opcion == 0) {
            // Búsqueda por Nombre
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor a buscar:");
            Profesor nombresEncontrado = buscarPorNombre(nombre.toLowerCase());
            if (nombresEncontrado != null) {
                JOptionPane.showMessageDialog(null, "Profesor encontrado:\n" + nombresEncontrado);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el profesor con el nombre especificado");
            }
        } else if (opcion == 1) {
            // Búsqueda por DNI
            String dni = JOptionPane.showInputDialog("Ingrese el DNI del profesor a buscar:");
            Profesor profesorEncontrado = buscarPorDni(dni);
            if (profesorEncontrado != null) {
                JOptionPane.showMessageDialog(null, "Profesor encontrado:\n" + profesorEncontrado);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el profesor con el DNI especificado");
            }

        }
    }

    // Clase interna que extiende AbstractTableModel para el modelo de tabla personalizado
    static class ProfesorTableModel extends AbstractTableModel {

        private final List<Profesor> profesores;
        private final String[] columnNames = {"Nombre", "Apellido", "Email", "DNI", "Materia"};

        public ProfesorTableModel(List<Profesor> profesores) {
            this.profesores = profesores;
        }

        @Override
        public int getRowCount() {
            return profesores.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            Profesor profesor = profesores.get(row);
            switch (column) {
                case 0:
                    return profesor.getNombre();
                case 1:
                    return profesor.getApellido();
                case 2:
                    return profesor.getEmail();
                case 3:
                    return profesor.getDni();
                case 4:
                    return profesor.getMateria();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}