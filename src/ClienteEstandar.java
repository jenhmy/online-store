//Subclase Cliente Estandar
public class ClienteEstandar extends Cliente {

    //Constructor
    public ClienteEstandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    @Override
    public String toString() {
        return "[ESTÁNDAR] " + super.toString();
    }
}