package proyectoprofes;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        mostrarMenu();
    }

    public static void mostrarMenu() {
        GestionProfesor GestionProfesor = new GestionProfesor();

        // Ruta de la imagen
        String imagePath = "Codo1.png";
        ImageIcon icon = createScaledIcon(imagePath, 130, 100); // Tamaño personalizado


        Integer opcion = null;
        while (opcion == null || opcion != 0) {
            String input = (String) JOptionPane.showInputDialog(null,
                    createMenuMessage(),
                    "Gestión Profesores",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    null,
                    null);

            if (input == null) {
                opcion = 0; // Si se presiona "Cancelar" o la "X", se sale del menú
            } else {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        GestionProfesor.addProfesor();
                        break;
                    case 2:
                        GestionProfesor.verListaProfesores();
                        break;
                    case 3:
                        GestionProfesor.modificarProfesor();
                        break;
                    case 4:
                        GestionProfesor.deleteProfesor();
                        break;
                    case 5:
                        GestionProfesor.buscarProfesor();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, icon, "¡Hasta Luego! Adiós", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida", "Opción Inválida", JOptionPane.WARNING_MESSAGE, icon);
                        break;
                }
            }
        }
    }

    public static String createMenuMessage() {
        return "1- Alta Profesor\n"
                + "2- Ver lista de profesores\n"
                + "3- Modificar alta de profesores\n"
                + "4- Eliminar profesor\n"
                + "5- Ver profesor especifico\n"
                + "0- Salir del menú";
    }

    public static ImageIcon createScaledIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}