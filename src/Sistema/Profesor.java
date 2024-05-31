package Sistema;

import Excepciones.AlumnoNoEncontrado;
import Interfaz.I_Metodos;
import Sistema.Enum.Mes;
import Sistema.Enum.Nivel;

import java.util.ArrayList;
import java.util.Date;

public class Profesor extends Persona implements I_Metodos<GestionAlumno> {

    public String password;
    public GestionAlumno alumnos;
    private ArrayList<Aviso> avisosGenerales;
    public Profesor(String nombre, String apellido, String mail, String password) {
        super( nombre, apellido, mail);
        this.password = password;
        this.alumnos = new GestionAlumno();
        avisosGenerales = new ArrayList<>();
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void ingresarUnAlumno (String nombre, String apellido, String mail, Nivel nivel)
    {
        alumnos.agregar(new Alumno(nombre,apellido,mail,nivel));
    }

    @Override
    public void agregar(GestionAlumno alumno) {

    }

    @Override
    public void eliminar(int id) {
        try {
            alumnos.eliminar(id);
            System.out.println(" eliminado correctamente");
        } catch (AlumnoNoEncontrado e) {
            System.out.println( e.getMessage());
        }

    }

    @Override
    public GestionAlumno buscar(int id) {
        try {
            alumnos.buscar(id);
        }catch (AlumnoNoEncontrado A)
        {
            System.out.println(A.getMessage());
        }
        return null;
    }

    @Override
    public StringBuilder listar() {
        StringBuilder sb=alumnos.listar();
        return sb;
    }

    public void mandarTarea (Tarea tarea)
    {
        for (Alumno alu: alumnos.alumnoHashSet) {
            alu.recibirTarea(tarea);
        }
    }
    public void mandarAvisoGenerales (Date fecha,String mensaje)
    {
        Aviso aviso = new Aviso(fecha,mensaje);// creo el aviso
        avisosGenerales.add(aviso);             /// lo agrego al array
        for (Alumno alu:alumnos.alumnoHashSet) {// recorro el set de alumnos y envio el aviso
            alu.recibirAviso(aviso);
        }
    }
    public void avisosPersonalisados(int id,Date fecha, String mensaje)throws AlumnoNoEncontrado
    {
        Alumno buscado = alumnos.buscar(id);
        if (buscado!=null)
        {
            Aviso aviso = new Aviso(fecha,mensaje);
            buscado.recibirAviso(aviso);
        }
    }
    public void cobrarCuota (int id, Mes mes, Cuota cuota)throws AlumnoNoEncontrado
    {
        Alumno buscado = alumnos.buscar(id);
        if(buscado!= null)
        {
            buscado.pagarCuota(mes,cuota);
        }
    }
    public void ComprobantePago(int id, Mes mes)throws AlumnoNoEncontrado
    {
        Alumno alumno = alumnos.buscar(id);

        if(alumno !=null)
        {
            alumno.Comprobante(mes);
        }else
            System.out.println(" No se encontro la cuota de ese mes");
    }
    public void tomarAsistencia (int id) throws AlumnoNoEncontrado {
        Alumno alumno = alumnos.buscar(id);
        if(alumno!=null) {
                alumno.registrarAsistencia(true);

        }else throw new AlumnoNoEncontrado("no se encontro el alumno");

    }

    @Override
    public String toString() {
        return "Profesor{" +
                "password='" + password + '\'' +
                ", alumnos=" + alumnos +
                ", avisosGenerales=" + avisosGenerales +
                "} " + super.toString();
    }
}