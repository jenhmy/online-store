public abstract class Cliente {

    // =========================
    // DATOS DEL CLIENTE
    // =========================
    private String email;
    private String nombre;
    private String domicilio;
    private String nif;

    // =========================
    // CONSTRUCTOR
    // Sirve para crear un cliente nuevo
    // =========================
    public Cliente(String email, String nombre, String domicilio, String nif) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
    }

    // =========================
    // GETTERS Y SETTERS
    // Sirven para leer y cambiar los datos
    // =========================
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    // =========================
    // TEXTO BASE PARA MOSTRAR EL CLIENTE
    // =========================
    @Override
    public String toString() {
        return "Nombre: " + nombre +
                " | Email: " + email +
                " | Domicilio: " + domicilio +
                " | NIF: " + nif;
    }
}