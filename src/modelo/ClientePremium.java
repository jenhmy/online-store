package modelo;

public class ClientePremium extends Cliente {

    private static final double DESCUENTO = 0.20;
    private static final double CUOTA_ANUAL = 30.0;

    public ClientePremium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    @Override
    public double calcularEnvio(double gastosBase) {
        return gastosBase * (1 - DESCUENTO);
    }

    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    @Override
    public String toString() {
        return "[PREMIUM] " + super.toString() +
                " - Cuota: " + CUOTA_ANUAL + "€" +
                " - Descuento envío: " + (DESCUENTO * 100) + "%";
    }
}