import java.time.LocalDateTime;
import java.util.ArrayList;

public class OnlineStore {

    // =========================
    // LISTAS PRINCIPALES
    // Aquí guardamos todos los datos
    // =========================
    private ArrayList<Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;

    // Este número sirve para que cada pedido tenga
    // un número distinto aunque se borre alguno
    private int siguienteNumeroPedido;

    // =========================
    // CONSTRUCTOR
    // Crea las listas vacías al empezar
    // =========================
    public OnlineStore() {
        clientes = new ArrayList<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();
        siguienteNumeroPedido = 1;
    }

    // =========================
    // CLIENTES
    // =========================

    // Añade un cliente solo si no existe otro con el mismo email
    public boolean añadirCliente(Cliente cliente) {
        if (buscarCliente(cliente.getEmail()) != null) {
            return false;
        }

        clientes.add(cliente);
        return true;
    }

    // Busca un cliente por su email
    public Cliente buscarCliente(String email) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equalsIgnoreCase(email)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    // Devuelve todos los clientes
    public ArrayList<Cliente> obtenerTodosClientes() {
        return new ArrayList<>(clientes);
    }

    // Devuelve solo los clientes estándar
    public ArrayList<Cliente> obtenerClientesEstandar() {
        ArrayList<Cliente> lista = new ArrayList<>();

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof ClienteEstandar) {
                lista.add(clientes.get(i));
            }
        }

        return lista;
    }

    // Devuelve solo los clientes premium
    public ArrayList<Cliente> obtenerClientesPremium() {
        ArrayList<Cliente> lista = new ArrayList<>();

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof ClientePremium) {
                lista.add(clientes.get(i));
            }
        }

        return lista;
    }

    // =========================
    // ARTÍCULOS
    // =========================

    // Añade un artículo solo si no existe otro con el mismo código
    public boolean añadirArticulo(Articulo articulo) {
        if (buscarArticulo(articulo.getCodigo()) != null) {
            return false;
        }

        articulos.add(articulo);
        return true;
    }

    // Busca un artículo por su código
    public Articulo buscarArticulo(String codigo) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return articulos.get(i);
            }
        }
        return null;
    }

    // Devuelve todos los artículos
    public ArrayList<Articulo> obtenerTodosArticulos() {
        return new ArrayList<>(articulos);
    }

    // =========================
    // PEDIDOS
    // =========================

    // Crea un pedido si el cliente existe, el artículo existe
    // y la cantidad es mayor que 0
    public boolean crearPedido(String email, String codigoArticulo, int cantidad) {
        Cliente cliente = buscarCliente(email);
        Articulo articulo = buscarArticulo(codigoArticulo);

        if (cliente == null || articulo == null || cantidad <= 0) {
            return false;
        }

        Pedido nuevoPedido = new Pedido(
                siguienteNumeroPedido,
                cliente,
                articulo,
                cantidad,
                LocalDateTime.now()
        );

        pedidos.add(nuevoPedido);
        siguienteNumeroPedido++;

        return true;
    }

    // Borra un pedido solo si todavía se puede cancelar
    public boolean borrarPedido(int numeroPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNumeroPedido() == numeroPedido) {
                if (pedidos.get(i).puedeCancelar()) {
                    pedidos.remove(i);
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    // Busca un pedido por su número
    public Pedido buscarPedido(int numeroPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNumeroPedido() == numeroPedido) {
                return pedidos.get(i);
            }
        }
        return null;
    }

    // Devuelve los pedidos que todavía no se han enviado
    public ArrayList<Pedido> obtenerPedidosPendientes() {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).puedeCancelar()) {
                lista.add(pedidos.get(i));
            }
        }

        return lista;
    }

    // Devuelve los pedidos que ya no se pueden cancelar
    public ArrayList<Pedido> obtenerPedidosEnviados() {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            if (!pedidos.get(i).puedeCancelar()) {
                lista.add(pedidos.get(i));
            }
        }

        return lista;
    }

    // Devuelve los pedidos pendientes de un cliente concreto
    public ArrayList<Pedido> obtenerPedidosPendientesCliente(String email) {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);

            if (pedidoActual.getCliente().getEmail().equalsIgnoreCase(email) && pedidoActual.puedeCancelar()) {
                lista.add(pedidoActual);
            }
        }

        return lista;
    }

    // Devuelve los pedidos enviados de un cliente concreto
    public ArrayList<Pedido> obtenerPedidosEnviadosCliente(String email) {
        ArrayList<Pedido> lista = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);

            if (pedidoActual.getCliente().getEmail().equalsIgnoreCase(email) && !pedidoActual.puedeCancelar()) {
                lista.add(pedidoActual);
            }
        }

        return lista;
    }
}