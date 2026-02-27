public class ClienteEstandar extends Cliente {

    // =========================
    // CONSTRUCTOR
    // =========================
    public ClienteEstandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    // =========================
    // TEXTO PARA MOSTRAR EL CLIENTE ESTÁNDAR
    // =========================
    @Override
    public String toString() {
        return "[CLIENTE ESTÁNDAR] " + super.toString();
    }
}