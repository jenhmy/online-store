import java.time.LocalDateTime;

public class Pedido {

    // =========================
    // DATOS DEL PEDIDO
    // =========================
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    // =========================
    // CONSTRUCTOR
    // Sirve para crear un pedido nuevo
    // =========================
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    // =========================
    // CALCULAR EL TOTAL DEL PEDIDO
    // Multiplica precio por cantidad
    // y suma el envío
    // Si el cliente es premium,
    // el envío tiene descuento
    // =========================
    public double calcularTotal() {
        double precioProductos = articulo.getPrecioVenta() * cantidad;
        double envio = articulo.getGastosEnvio();

        if (cliente instanceof ClientePremium) {
            ClientePremium premium = (ClientePremium) cliente;
            envio = premium.calcularEnvio(envio);
        }

        return precioProductos + envio;
    }

    // =========================
    // SABER SI EL PEDIDO SE PUEDE BORRAR
    // Solo se puede borrar si todavía no ha pasado
    // el tiempo de preparación del artículo
    // =========================
    public boolean puedeCancelar() {
        LocalDateTime limite = fechaHora.plusMinutes(articulo.getTiempoPreparacion());
        return LocalDateTime.now().isBefore(limite);
    }

    // =========================
    // TEXTO PARA MOSTRAR EL PEDIDO
    // =========================
    @Override
    public String toString() {
        return "Pedido nº " + numeroPedido +
                " | Cliente: " + cliente.getNombre() +
                " | Artículo: " + articulo.getDescripcion() +
                " | Cantidad: " + cantidad +
                " | Total: " + calcularTotal() + "€" +
                " | Fecha: " + fechaHora;
    }
}