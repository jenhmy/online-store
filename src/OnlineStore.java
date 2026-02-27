import java.time.LocalDateTime;
import java.util.ArrayList;

public class OnlineStore {
    private ArrayList<Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;

    // Constructor
    public OnlineStore() {
        this.clientes = new ArrayList<>();
        this.articulos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // ========== MÉTODOS DE CLIENTES ==========

    public void añadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String email) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equals(email)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    public ArrayList<Cliente> obtenerTodosClientes() {
        return clientes;
    }

    public ArrayList<Cliente> obtenerClientesEstandar() {
        ArrayList<Cliente> estandares = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof ClienteEstandar) {
                estandares.add(clientes.get(i));
            }
        }
        return estandares;
    }

    public ArrayList<Cliente> obtenerClientesPremium() {
        ArrayList<Cliente> premiums = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof ClientePremium) {
                premiums.add(clientes.get(i));
            }
        }
        return premiums;
    }

    // ========== MÉTODOS DE ARTÍCULOS ==========

    public void añadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public Articulo buscarArticulo(String codigo) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getCodigo().equals(codigo)) {
                return articulos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Articulo> obtenerTodosArticulos() {
        return articulos;
    }

    // ========== MÉTODOS DE PEDIDOS ==========

    public void crearPedido(String email, String codigoArticulo, int cantidad) {
        // Buscar cliente
        Cliente cliente = buscarCliente(email);

        // Buscar artículo
        Articulo articulo = buscarArticulo(codigoArticulo);

        // Crear número de pedido (el tamaño actual + 1)
        int numeroPedido = pedidos.size() + 1;

        // Crear y añadir pedido
        Pedido nuevoPedido = new Pedido(numeroPedido, cliente, articulo, cantidad, LocalDateTime.now());
        pedidos.add(nuevoPedido);
        System.out.println("Pedido creado con número: " + numeroPedido);
    }

    public void borrarPedido(int numeroPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNumeroPedido() == numeroPedido) {
                if (pedidos.get(i).puedeCancelar()) {
                    pedidos.remove(i);
                    System.out.println("Pedido cancelado");
                } else {
                    System.out.println("No se puede cancelar, ya pasó el tiempo de preparación");
                }
                return;
            }
        }
        System.out.println("Pedido no encontrado");
    }

    public ArrayList<Pedido> obtenerPedidosPendientes() {
        ArrayList<Pedido> pendientes = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).puedeCancelar()) {
                pendientes.add(pedidos.get(i));
            }
        }
        return pendientes;
    }

    public ArrayList<Pedido> obtenerPedidosEnviados() {
        ArrayList<Pedido> enviados = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            if (!pedidos.get(i).puedeCancelar()) {
                enviados.add(pedidos.get(i));
            }
        }
        return enviados;
    }

    public ArrayList<Pedido> obtenerPedidosPendientesCliente(String email) {
        ArrayList<Pedido> pendientes = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getCliente().getEmail().equals(email) && p.puedeCancelar()) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    public ArrayList<Pedido> obtenerPedidosEnviadosCliente(String email) {
        ArrayList<Pedido> enviados = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getCliente().getEmail().equals(email) && !p.puedeCancelar()) {
                enviados.add(p);
            }
        }
        return enviados;
    }
}