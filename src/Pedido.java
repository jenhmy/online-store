//Clase pedido
import java.time.LocalDateTime;
import java.time.Duration;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    // Constructor
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    // Getters y setters
    public int getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(int numeroPedido) { this.numeroPedido = numeroPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    // Metodo para calcular total del pedido
    public double calcularTotal() {
        double subtotal = articulo.getPrecioVenta() * cantidad;
        double gastosEnvio = articulo.getGastosEnvio();

        // Si el cliente es Premium, aplicar el descuento
        if (cliente instanceof ClientePremium) {
            ClientePremium premium = (ClientePremium) cliente;
            gastosEnvio = premium.calcularEnvio(gastosEnvio);
        }

        return subtotal + gastosEnvio;
    }

    // Metodo para determinar si se puede cancelar
    public boolean puedeCancelar() {
        // Consideramos que se puede cancelar si no ha pasado el tiempo de preparación
        LocalDateTime limite = fechaHora.plusMinutes(articulo.getTiempoPreparacion());
        return LocalDateTime.now().isBefore(limite);
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " - " + articulo.getDescripcion() +
                " x" + cantidad + " - Cliente: " + cliente.getNombre() +
                " - Total: " + calcularTotal() + "€";
    }
}