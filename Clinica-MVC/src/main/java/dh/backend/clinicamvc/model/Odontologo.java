package dh.backend.clinicamvc.model;

public class Odontologo {
    private Integer id;
    private String numeroMatricula;
    private String apellido;
    private String nombre;

    public Odontologo(String apellido, String nombre, String numeroMatricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroMatricula = numeroMatricula;
    }

    public Odontologo(Integer id, String apellido, String nombre, String numeroMatricula) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroMatricula = numeroMatricula;
    }
    public Odontologo(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }


    @Override
    public String toString() {
        return "Paciente " +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", numeroMatricula='" + numeroMatricula;
    }
}
