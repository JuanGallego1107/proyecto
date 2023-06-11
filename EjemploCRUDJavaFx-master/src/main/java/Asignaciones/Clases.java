package Asignaciones;

public class Clases {

    private int id;
    private String profesor;
    private String asignatura;
    private String aula;
    private String hora;
    private int capacidad;
    private String comentarios;

    public Clases(int id, String profesor, String asignatura, String aula, String hora, int capacidad, String comentarios) {
        this.id = id;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.aula = aula;
        this.hora = hora;
        this.capacidad = capacidad;
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getAula() {
        return aula;
    }

    public String getHora() {
        return hora;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getComentarios() {
        return comentarios;
    }
}
