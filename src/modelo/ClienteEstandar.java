package modelo;

public class ClienteEstandar extends Cliente {

    public ClienteEstandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    @Override
    public double calcularEnvio(double gastosBase) {
        return gastosBase;
    }

    @Override
    public String toString() {
        return "[ESTÁNDAR] " + super.toString();
    }
}