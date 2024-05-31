package Sistema;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String mail;

    public Persona(String nombre, String apellido, String mail) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        boolean esIgual = false;
        if (o != null) {
            if (o instanceof Persona persona) {
                if (this.apellido.equals(persona.apellido)) {
                    esIgual = true;
                }
            }
        }
        return esIgual;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}