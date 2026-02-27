import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OnlineStore tienda = new OnlineStore();
        int opcion;

        do {
            System.out.println("\n=== ONLINE STORE ===");
            System.out.println("1. Gestión Artículos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: menuArticulos(sc, tienda); break;
                case 2: menuClientes(sc, tienda); break;
                case 3: menuPedidos(sc, tienda); break;
                case 0: System.out.println("Adiós!"); break;
                default: System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    // ========== MENÚ ARTÍCULOS ==========
    public static void menuArticulos(Scanner sc, OnlineStore tienda) {
        int opcion;
        do {
            System.out.println("\n--- ARTÍCULOS ---");
            System.out.println("1.1 Añadir Artículo");
            System.out.println("1.2 Mostrar Artículos");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String cod = sc.nextLine();
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    System.out.print("Precio venta: ");
                    double pv = Double.parseDouble(sc.nextLine());
                    System.out.print("Gastos envío: ");
                    double ge = Double.parseDouble(sc.nextLine());
                    System.out.print("Tiempo preparación (min): ");
                    int tp = Integer.parseInt(sc.nextLine());

                    Articulo a = new Articulo(cod, desc, pv, ge, tp);
                    tienda.añadirArticulo(a);
                    System.out.println("Artículo añadido");
                    break;

                case 2:
                    System.out.println("\nLISTA DE ARTÍCULOS:");
                    ArrayList<Articulo> articulos = tienda.obtenerTodosArticulos();
                    if (articulos.isEmpty()) {
                        System.out.println("No hay artículos");
                    } else {
                        for (int i = 0; i < articulos.size(); i++) {
                            System.out.println(articulos.get(i));
                        }
                    }
                    break;
            }
        } while (opcion != 0);
    }

    // ========== MENÚ CLIENTES ==========
    public static void menuClientes(Scanner sc, OnlineStore tienda) {
        int opcion;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("2.1 Añadir Cliente");
            System.out.println("2.2 Mostrar Todos");
            System.out.println("2.3 Mostrar Estándar");
            System.out.println("2.4 Mostrar Premium");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Domicilio: ");
                    String dom = sc.nextLine();
                    System.out.print("NIF: ");
                    String nif = sc.nextLine();
                    System.out.print("Tipo (1-Estándar, 2-Premium): ");
                    int tipo = Integer.parseInt(sc.nextLine());

                    Cliente c;
                    if (tipo == 1) {
                        c = new ClienteEstandar(email, nombre, dom, nif);
                    } else {
                        c = new ClientePremium(email, nombre, dom, nif);
                    }
                    tienda.añadirCliente(c);
                    System.out.println("Cliente añadido");
                    break;

                case 2:
                    System.out.println("\nTODOS LOS CLIENTES:");
                    ArrayList<Cliente> todos = tienda.obtenerTodosClientes();
                    if (todos.isEmpty()) {
                        System.out.println("No hay clientes");
                    } else {
                        for (int i = 0; i < todos.size(); i++) {
                            System.out.println(todos.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCLIENTES ESTÁNDAR:");
                    ArrayList<Cliente> est = tienda.obtenerClientesEstandar();
                    if (est.isEmpty()) {
                        System.out.println("No hay clientes estándar");
                    } else {
                        for (int i = 0; i < est.size(); i++) {
                            System.out.println(est.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nCLIENTES PREMIUM:");
                    ArrayList<Cliente> prem = tienda.obtenerClientesPremium();
                    if (prem.isEmpty()) {
                        System.out.println("No hay clientes premium");
                    } else {
                        for (int i = 0; i < prem.size(); i++) {
                            System.out.println(prem.get(i));
                        }
                    }
                    break;
            }
        } while (opcion != 0);
    }

    // ========== MENÚ PEDIDOS ==========
    public static void menuPedidos(Scanner sc, OnlineStore tienda) {
        int opcion;
        do {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("3.1 Añadir Pedido");
            System.out.println("3.2 Eliminar Pedido");
            System.out.println("3.3 Mostrar Pendientes");
            System.out.println("3.4 Mostrar Enviados");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Email cliente: ");
                    String email = sc.nextLine();
                    Cliente c = tienda.buscarCliente(email);

                    // Si no existe el cliente, lo creamos
                    if (c == null) {
                        System.out.println("Cliente no existe. Creando nuevo...");
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Domicilio: ");
                        String dom = sc.nextLine();
                        System.out.print("NIF: ");
                        String nif = sc.nextLine();
                        System.out.print("Tipo (1-Estándar, 2-Premium): ");
                        int tipo = Integer.parseInt(sc.nextLine());

                        if (tipo == 1) {
                            c = new ClienteEstandar(email, nombre, dom, nif);
                        } else {
                            c = new ClientePremium(email, nombre, dom, nif);
                        }
                        tienda.añadirCliente(c);
                        System.out.println("Cliente creado");
                    }

                    System.out.print("Código artículo: ");
                    String cod = sc.nextLine();
                    Articulo a = tienda.buscarArticulo(cod);

                    if (a == null) {
                        System.out.println("Artículo no existe");
                    } else {
                        System.out.print("Cantidad: ");
                        int cant = Integer.parseInt(sc.nextLine());
                        tienda.crearPedido(email, cod, cant);
                    }
                    break;

                case 2:
                    System.out.print("Número de pedido: ");
                    int num = Integer.parseInt(sc.nextLine());
                    tienda.borrarPedido(num);
                    break;

                case 3:
                    System.out.print("¿Filtrar por cliente? (s/n): ");
                    String resp = sc.nextLine();
                    if (resp.equals("s")) {
                        System.out.print("Email cliente: ");
                        String mail = sc.nextLine();
                        ArrayList<Pedido> pend = tienda.obtenerPedidosPendientesCliente(mail);
                        if (pend.isEmpty()) {
                            System.out.println("No hay pedidos pendientes para este cliente");
                        } else {
                            for (int i = 0; i < pend.size(); i++) {
                                System.out.println(pend.get(i));
                            }
                        }
                    } else {
                        ArrayList<Pedido> pend = tienda.obtenerPedidosPendientes();
                        if (pend.isEmpty()) {
                            System.out.println("No hay pedidos pendientes");
                        } else {
                            for (int i = 0; i < pend.size(); i++) {
                                System.out.println(pend.get(i));
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.print("¿Filtrar por cliente? (s/n): ");
                    String res = sc.nextLine();
                    if (res.equals("s")) {
                        System.out.print("Email cliente: ");
                        String mail = sc.nextLine();
                        ArrayList<Pedido> env = tienda.obtenerPedidosEnviadosCliente(mail);
                        if (env.isEmpty()) {
                            System.out.println("No hay pedidos enviados para este cliente");
                        } else {
                            for (int i = 0; i < env.size(); i++) {
                                System.out.println(env.get(i));
                            }
                        }
                    } else {
                        ArrayList<Pedido> env = tienda.obtenerPedidosEnviados();
                        if (env.isEmpty()) {
                            System.out.println("No hay pedidos enviados");
                        } else {
                            for (int i = 0; i < env.size(); i++) {
                                System.out.println(env.get(i));
                            }
                        }
                    }
                    break;
            }
        } while (opcion != 0);
    }
}