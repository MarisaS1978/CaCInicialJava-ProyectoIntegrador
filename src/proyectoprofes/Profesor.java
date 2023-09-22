package proyectoprofes;


public class Profesor {

    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String materia;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String email, String dni, String materia) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.materia = materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public String toString() {
        return


        "Datos del Profesor"+"\n"+

        "Nombre:  " + nombre + "\n"
                +"Apellido:  " + apellido + "\n"
                +"Email:  " + email + "\n"
                +"Dni:  " + dni + "\n"
                +"Materia:  " + materia ;
    }

}
