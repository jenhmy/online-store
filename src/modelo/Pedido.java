package modelo;

import java.time.LocalDateTime;
import java.time.Duration;

public class Pedido {
    private static int contador = 1;

    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.numeroPedido = contador++;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public int getNumeroPedido() { return numeroPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }

    public double calcularTotal() {
        double subtotal = articulo.getPrecioVenta() * cantidad;
        double envio = cliente.calcularEnvio(articulo.getGastosEnvio());
        return subtotal + envio;
    }

    public boolean puedeCancelar() {
        LocalDateTime ahora = LocalDateTime.now();
        Duration tiempo = Duration.between(fechaHora, ahora);
        return tiempo.toMinutes() < articulo.getTiempoPreparacion();
    }

    public boolean estaEnviado() {
        return !puedeCancelar();
    }

    @Override
    public String toString() {
        String estado = estaEnviado() ? "ENVIADO" : "PENDIENTE";
        return "Pedido #" + numeroPedido + " - " + cliente.getEmail() + " - " +
                articulo.getDescripcion() + " x" + cantidad + " - Total: " +
                calcularTotal() + "€ - " + estado;
    }
}