package vista;

import controlador.OnlineStore;
import modelo.*;
import java.util.*;

public class ConsolaVista {
    private OnlineStore controller;
    private Scanner scanner;

    public ConsolaVista() {
        controller = new OnlineStore();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ConsolaVista vista = new ConsolaVista();
        vista.mostrarMenu();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== ONLINE STORE ===");
            System.out.println("1. Gestionar Artículos");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion == 1) {
                    menuArticulos();
                } else if (opcion == 2) {
                    menuClientes();
                } else if (opcion == 3) {
                    menuPedidos();
                } else if (opcion == 0) {
                    System.out.println("¡Adiós!");
                } else {
                    System.out.println("Opción incorrecta");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: introduce un número");
                opcion = -1;
            }
        } while(opcion != 0);
    }

    // MENÚ ARTÍCULOS
    private void menuArticulos() {
        System.out.println("\n--- ARTÍCULOS ---");
        System.out.println("1. Añadir artículo");
        System.out.println("2. Ver todos los artículos");
        System.out.print("Elige: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 1) {
                añadirArticulo();
            } else if (opcion == 2) {
                verArticulos();
            } else {
                System.out.println("Opción incorrecta");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: introduce un número");
        }
    }

    private void añadirArticulo() {
        try {
            System.out.print("Código: ");
            String codigo = scanner.nextLine();

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Gastos de envío: ");
            double gastos = Double.parseDouble(scanner.nextLine());

            System.out.print("Tiempo preparación (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine());

            Articulo a = new Articulo(codigo, descripcion, precio, gastos, tiempo);
            controller.añadirArticulo(a);
            System.out.println("Artículo añadido correctamente");

        } catch (NumberFormatException e) {
            System.out.println("Error: formato de número incorrecto");
        }
    }

    private void verArticulos() {
        List<Articulo> lista = controller.obtenerTodosArticulos();
        if (lista.isEmpty()) {
            System.out.println("No hay artículos");
        } else {
            System.out.println("\n--- LISTA DE ARTÍCULOS ---");
            for (Articulo a : lista) {
                System.out.println(a);
            }
        }
    }

    // MENÚ CLIENTES
    private void menuClientes() {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Ver todos los clientes");
        System.out.println("3. Ver solo clientes estándar");
        System.out.println("4. Ver solo clientes premium");
        System.out.println("5. Buscar cliente por email");
        System.out.print("Elige: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 1) {
                añadirCliente();
            } else if (opcion == 2) {
                verClientes(controller.obtenerTodosClientes(), "TODOS LOS CLIENTES");
            } else if (opcion == 3) {
                verClientes(controller.obtenerClientesEstandar(), "CLIENTES ESTÁNDAR");
            } else if (opcion == 4) {
                verClientes(controller.obtenerClientesPremium(), "CLIENTES PREMIUM");
            } else if (opcion == 5) {
                buscarClientePorEmail();
            } else {
                System.out.println("Opción incorrecta");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: introduce un número");
        }
    }

    private void añadirCliente() {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();

        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        System.out.print("Tipo (E=Estándar, P=Premium): ");
        String tipo = scanner.nextLine().toUpperCase();

        Cliente c;
        if (tipo.equals("P")) {
            c = new ClientePremium(email, nombre, domicilio, nif);
        } else {
            c = new ClienteEstandar(email, nombre, domicilio, nif);
        }

        controller.añadirCliente(c);
        System.out.println("Cliente añadido correctamente");
    }

    private void verClientes(List<Cliente> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("No hay clientes");
        } else {
            System.out.println("\n--- " + titulo + " ---");
            for (Cliente c : lista) {
                System.out.println(c);
            }
        }
    }

    private void buscarClientePorEmail() {
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();

        Cliente c = controller.buscarCliente(email);
        if (c == null) {
            System.out.println("Cliente no encontrado");
        } else {
            System.out.println("\n--- CLIENTE ENCONTRADO ---");
            System.out.println(c);
        }
    }

    // MENÚ PEDIDOS
    private void menuPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        System.out.println("1. Nuevo pedido");
        System.out.println("2. Cancelar pedido");
        System.out.println("3. Ver todos los pedidos");
        System.out.println("4. Ver solo pendientes");
        System.out.println("5. Ver solo enviados");
        System.out.println("6. Buscar pedidos por cliente");
        System.out.print("Elige: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 1) {
                nuevoPedido();
            } else if (opcion == 2) {
                cancelarPedido();
            } else if (opcion == 3) {
                verTodosPedidos();
            } else if (opcion == 4) {
                verPendientes();
            } else if (opcion == 5) {
                verEnviados();
            } else if (opcion == 6) {
                buscarPedidosPorCliente();
            } else {
                System.out.println("Opción incorrecta");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: introduce un número");
        }
    }

    private void nuevoPedido() {
        try {
            System.out.print("Email del cliente: ");
            String email = scanner.nextLine();

            Cliente cliente = controller.buscarCliente(email);
            if (cliente == null) {
                System.out.println("El cliente no existe");
                System.out.print("¿Quieres crear el cliente? (S/N): ");
                String respuesta = scanner.nextLine().toUpperCase();
                if (respuesta.equals("S")) {
                    añadirCliente();
                }
                return;
            }

            System.out.print("Código del artículo: ");
            String codigo = scanner.nextLine();

            Articulo articulo = controller.buscarArticulo(codigo);
            if (articulo == null) {
                System.out.println("El artículo no existe");
                return;
            }

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            Pedido p = controller.crearPedido(email, codigo, cantidad);
            if (p != null) {
                System.out.println("Pedido creado: " + p);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: cantidad no válida");
        }
    }

    private void cancelarPedido() {
        try {
            System.out.print("Número de pedido: ");
            int num = Integer.parseInt(scanner.nextLine());

            if (controller.borrarPedido(num)) {
                System.out.println("Pedido cancelado");
            } else {
                System.out.println("No se puede cancelar: no existe o ya ha sido enviado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: número no válido");
        }
    }

    private void verTodosPedidos() {
        List<Pedido> pendientes = controller.obtenerPedidosPendientes();
        List<Pedido> enviados = controller.obtenerPedidosEnviados();

        if (pendientes.isEmpty() && enviados.isEmpty()) {
            System.out.println("No hay pedidos");
            return;
        }

        System.out.println("\n--- TODOS LOS PEDIDOS ---");

        if (!pendientes.isEmpty()) {
            System.out.println("\nPENDIENTES:");
            for (Pedido p : pendientes) {
                System.out.println("  " + p);
            }
        }

        if (!enviados.isEmpty()) {
            System.out.println("\nENVIADOS:");
            for (Pedido p : enviados) {
                System.out.println("  " + p);
            }
        }
    }

    private void verPendientes() {
        List<Pedido> lista = controller.obtenerPedidosPendientes();

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos pendientes");
        } else {
            System.out.println("\n--- PEDIDOS PENDIENTES ---");
            for (Pedido p : lista) {
                System.out.println(p);
            }
        }
    }

    private void verEnviados() {
        List<Pedido> lista = controller.obtenerPedidosEnviados();

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos enviados");
        } else {
            System.out.println("\n--- PEDIDOS ENVIADOS ---");
            for (Pedido p : lista) {
                System.out.println(p);
            }
        }
    }

    private void buscarPedidosPorCliente() {
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();

        Cliente cliente = controller.buscarCliente(email);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        List<Pedido> pendientes = controller.obtenerPedidosPendientesCliente(email);
        List<Pedido> enviados = controller.obtenerPedidosEnviadosCliente(email);

        if (pendientes.isEmpty() && enviados.isEmpty()) {
            System.out.println("Este cliente no tiene pedidos");
            return;
        }

        System.out.println("\n--- PEDIDOS DE " + email + " ---");

        if (!pendientes.isEmpty()) {
            System.out.println("\nPENDIENTES:");
            for (Pedido p : pendientes) {
                System.out.println("  " + p);
            }
        }

        if (!enviados.isEmpty()) {
            System.out.println("\nENVIADOS:");
            for (Pedido p : enviados) {
                System.out.println("  " + p);
            }
        }
    }
}