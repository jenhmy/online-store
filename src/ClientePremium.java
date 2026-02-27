//Subclase Cliente Premium
public class ClientePremium extends Cliente {

    private double descuento = 0.20;
    private double cuotaAnual = 30.0;

    //Constructor
    public ClientePremium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
    }

    //Metodo calcularEnvio
    public double calcularEnvio(double gastosBase) {
        return gastosBase * (1 - descuento);
    }

    // Getters para cuota y descuento
    public double getCuotaAnual() {
        return cuotaAnual;
    }

    public double getDescuento() {
        return descuento;
    }

}