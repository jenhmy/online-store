package controlador;

import modelo.*;
import java.util.*;

public class OnlineStore {
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;

    public OnlineStore() {
        clientes = new ArrayList<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();

        // Datos de ejemplo
        clientes.add(new ClienteEstandar("juan@email.com", "Juan Pérez", "Calle Mayor 1", "12345678A"));
        clientes.add(new ClientePremium("maria@email.com", "María García", "Avda. Central 5", "87654321B"));

        articulos.add(new Articulo("A001", "Portátil HP", 599.99, 5.50, 1));
        articulos.add(new Articulo("A002", "Ratón inalámbrico", 19.99, 2.00, 1));
    }

    // CLIENTES
    public void añadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> obtenerTodosClientes() {
        return clientes;
    }

    public List<Cliente> obtenerClientesEstandar() {
        List<Cliente> lista = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c instanceof ClienteEstandar) {
                lista.add(c);
            }
        }
        return lista;
    }

    public List<Cliente> obtenerClientesPremium() {
        List<Cliente> lista = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c instanceof ClientePremium) {
                lista.add(c);
            }
        }
        return lista;
    }

    // ARTÍCULOS
    public void añadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public Articulo buscarArticulo(String codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo().equals(codigo)) {
                return a;
            }
        }
        return null;
    }

    public List<Articulo> obtenerTodosArticulos() {
        return articulos;
    }

    // PEDIDOS
    public Pedido crearPedido(String emailCliente, String codigoArticulo, int cantidad) {
        Cliente cliente = buscarCliente(emailCliente);
        Articulo articulo = buscarArticulo(codigoArticulo);

        if (cliente == null || articulo == null) {
            return null;
        }

        Pedido pedido = new Pedido(cliente, articulo, cantidad);
        pedidos.add(pedido);
        return pedido;
    }

    public boolean borrarPedido(int numeroPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getNumeroPedido() == numeroPedido && p.puedeCancelar()) {
                pedidos.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Pedido> obtenerPedidosPendientes() {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.estaEnviado()) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Pedido> obtenerPedidosEnviados() {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.estaEnviado()) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Pedido> obtenerPedidosPendientesCliente(String email) {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.estaEnviado() && p.getCliente().getEmail().equals(email)) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Pedido> obtenerPedidosEnviadosCliente(String email) {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.estaEnviado() && p.getCliente().getEmail().equals(email)) {
                lista.add(p);
            }
        }
        return lista;
    }
}