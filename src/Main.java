import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        OnlineStore tienda = new OnlineStore();
        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println("        ONLINE STORE");
            System.out.println("==============================");
            System.out.println("1. Gestión de artículos");
            System.out.println("2. Gestión de clientes");
            System.out.println("3. Gestión de pedidos");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    menuArticulos(teclado, tienda);
                    break;
                case 2:
                    menuClientes(teclado, tienda);
                    break;
                case 3:
                    menuPedidos(teclado, tienda);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        teclado.close();
    }

    // =========================
    // MENÚ DE ARTÍCULOS
    // =========================
    public static void menuArticulos(Scanner teclado, OnlineStore tienda) {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
            System.out.println("1. Añadir artículo");
            System.out.println("2. Mostrar artículos");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String codigo = teclado.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = teclado.nextLine();

                    System.out.print("Precio de venta: ");
                    double precioVenta = leerDouble(teclado);

                    System.out.print("Gastos de envío: ");
                    double gastosEnvio = leerDouble(teclado);

                    System.out.print("Tiempo de preparación en minutos: ");
                    int tiempoPreparacion = leerEntero(teclado);

                    Articulo articuloNuevo = new Articulo(
                            codigo,
                            descripcion,
                            precioVenta,
                            gastosEnvio,
                            tiempoPreparacion
                    );

                    if (tienda.añadirArticulo(articuloNuevo)) {
                        System.out.println("Artículo añadido correctamente.");
                    } else {
                        System.out.println("No se puede añadir. Ya existe un artículo con ese código.");
                    }
                    break;

                case 2:
                    System.out.println("\nLISTA DE ARTÍCULOS");
                    ArrayList<Articulo> articulos = tienda.obtenerTodosArticulos();

                    if (articulos.isEmpty()) {
                        System.out.println("No hay artículos guardados.");
                    } else {
                        for (int i = 0; i < articulos.size(); i++) {
                            System.out.println(articulos.get(i));
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // =========================
    // MENÚ DE CLIENTES
    // =========================
    public static void menuClientes(Scanner teclado, OnlineStore tienda) {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Mostrar todos los clientes");
            System.out.println("3. Mostrar clientes estándar");
            System.out.println("4. Mostrar clientes premium");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    Cliente clienteNuevo = pedirDatosCliente(teclado);

                    if (tienda.añadirCliente(clienteNuevo)) {
                        System.out.println("Cliente añadido correctamente.");
                    } else {
                        System.out.println("No se puede añadir. Ya existe un cliente con ese email.");
                    }
                    break;

                case 2:
                    System.out.println("\nTODOS LOS CLIENTES");
                    ArrayList<Cliente> todosLosClientes = tienda.obtenerTodosClientes();

                    if (todosLosClientes.isEmpty()) {
                        System.out.println("No hay clientes guardados.");
                    } else {
                        for (int i = 0; i < todosLosClientes.size(); i++) {
                            System.out.println(todosLosClientes.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCLIENTES ESTÁNDAR");
                    ArrayList<Cliente> clientesEstandar = tienda.obtenerClientesEstandar();

                    if (clientesEstandar.isEmpty()) {
                        System.out.println("No hay clientes estándar.");
                    } else {
                        for (int i = 0; i < clientesEstandar.size(); i++) {
                            System.out.println(clientesEstandar.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nCLIENTES PREMIUM");
                    ArrayList<Cliente> clientesPremium = tienda.obtenerClientesPremium();

                    if (clientesPremium.isEmpty()) {
                        System.out.println("No hay clientes premium.");
                    } else {
                        for (int i = 0; i < clientesPremium.size(); i++) {
                            System.out.println(clientesPremium.get(i));
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // =========================
    // MENÚ DE PEDIDOS
    // =========================
    public static void menuPedidos(Scanner teclado, OnlineStore tienda) {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Añadir pedido");
            System.out.println("2. Eliminar pedido");
            System.out.println("3. Mostrar pedidos pendientes");
            System.out.println("4. Mostrar pedidos enviados");
            System.out.println("0. Volver");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    System.out.print("Email del cliente: ");
                    String emailCliente = teclado.nextLine();

                    Cliente clienteBuscado = tienda.buscarCliente(emailCliente);

                    // Si el cliente no existe, lo creamos en ese momento
                    if (clienteBuscado == null) {
                        System.out.println("Ese cliente no existe. Vamos a crearlo ahora.");
                        Cliente nuevoCliente = pedirDatosClienteConEmail(teclado, emailCliente);

                        if (tienda.añadirCliente(nuevoCliente)) {
                            System.out.println("Cliente creado correctamente.");
                        } else {
                            System.out.println("No se ha podido crear el cliente.");
                        }
                    }

                    System.out.print("Código del artículo: ");
                    String codigoArticulo = teclado.nextLine();

                    Articulo articuloBuscado = tienda.buscarArticulo(codigoArticulo);

                    if (articuloBuscado == null) {
                        System.out.println("No se puede crear el pedido porque ese artículo no existe.");
                    } else {
                        System.out.print("Cantidad de unidades: ");
                        int cantidad = leerEntero(teclado);

                        if (tienda.crearPedido(emailCliente, codigoArticulo, cantidad)) {
                            System.out.println("Pedido creado correctamente.");
                        } else {
                            System.out.println("No se ha podido crear el pedido.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Número del pedido que quieres borrar: ");
                    int numeroPedido = leerEntero(teclado);

                    Pedido pedidoBuscado = tienda.buscarPedido(numeroPedido);

                    if (pedidoBuscado == null) {
                        System.out.println("Ese pedido no existe.");
                    } else {
                        if (pedidoBuscado.puedeCancelar()) {
                            if (tienda.borrarPedido(numeroPedido)) {
                                System.out.println("Pedido eliminado correctamente.");
                            } else {
                                System.out.println("No se ha podido eliminar el pedido.");
                            }
                        } else {
                            System.out.println("No se puede eliminar porque ya ha pasado el tiempo de preparación.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("¿Quieres filtrar por cliente? (s/n): ");
                    String respuestaPendientes = teclado.nextLine();

                    if (respuestaPendientes.equalsIgnoreCase("s")) {
                        System.out.print("Escribe el email del cliente: ");
                        String emailFiltro = teclado.nextLine();

                        ArrayList<Pedido> pedidosPendientesCliente = tienda.obtenerPedidosPendientesCliente(emailFiltro);

                        if (pedidosPendientesCliente.isEmpty()) {
                            System.out.println("No hay pedidos pendientes para ese cliente.");
                        } else {
                            for (int i = 0; i < pedidosPendientesCliente.size(); i++) {
                                System.out.println(pedidosPendientesCliente.get(i));
                            }
                        }
                    } else {
                        ArrayList<Pedido> pedidosPendientes = tienda.obtenerPedidosPendientes();

                        if (pedidosPendientes.isEmpty()) {
                            System.out.println("No hay pedidos pendientes.");
                        } else {
                            for (int i = 0; i < pedidosPendientes.size(); i++) {
                                System.out.println(pedidosPendientes.get(i));
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.print("¿Quieres filtrar por cliente? (s/n): ");
                    String respuestaEnviados = teclado.nextLine();

                    if (respuestaEnviados.equalsIgnoreCase("s")) {
                        System.out.print("Escribe el email del cliente: ");
                        String emailFiltro = teclado.nextLine();

                        ArrayList<Pedido> pedidosEnviadosCliente = tienda.obtenerPedidosEnviadosCliente(emailFiltro);

                        if (pedidosEnviadosCliente.isEmpty()) {
                            System.out.println("No hay pedidos enviados para ese cliente.");
                        } else {
                            for (int i = 0; i < pedidosEnviadosCliente.size(); i++) {
                                System.out.println(pedidosEnviadosCliente.get(i));
                            }
                        }
                    } else {
                        ArrayList<Pedido> pedidosEnviados = tienda.obtenerPedidosEnviados();

                        if (pedidosEnviados.isEmpty()) {
                            System.out.println("No hay pedidos enviados.");
                        } else {
                            for (int i = 0; i < pedidosEnviados.size(); i++) {
                                System.out.println(pedidosEnviados.get(i));
                            }
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // =========================
    // PEDIR LOS DATOS DE UN CLIENTE NUEVO
    // Este método pregunta todo lo necesario
    // y devuelve el cliente ya creado
    // =========================
    public static Cliente pedirDatosCliente(Scanner teclado) {
        System.out.print("Email: ");
        String email = teclado.nextLine();

        return pedirDatosClienteConEmail(teclado, email);
    }

    // =========================
    // PEDIR LOS DATOS DE UN CLIENTE NUEVO
    // usando un email que ya tenemos escrito
    // =========================
    public static Cliente pedirDatosClienteConEmail(Scanner teclado, String email) {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = teclado.nextLine();

        System.out.print("NIF: ");
        String nif = teclado.nextLine();

        int tipoCliente;
        do {
            System.out.print("Tipo de cliente (1 = estándar, 2 = premium): ");
            tipoCliente = leerEntero(teclado);

            if (tipoCliente != 1 && tipoCliente != 2) {
                System.out.println("Debes escribir 1 o 2.");
            }
        } while (tipoCliente != 1 && tipoCliente != 2);

        if (tipoCliente == 1) {
            return new ClienteEstandar(email, nombre, domicilio, nif);
        } else {
            return new ClientePremium(email, nombre, domicilio, nif);
        }
    }

    // =========================
    // LEER UN NÚMERO ENTERO SIN QUE EL PROGRAMA SE ROMPA
    // Si el usuario escribe algo incorrecto,
    // lo vuelve a pedir
    // =========================
    public static int leerEntero(Scanner teclado) {
        while (true) {
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Debes escribir un número entero. Inténtalo de nuevo: ");
            }
        }
    }

    // =========================
    // LEER UN NÚMERO DECIMAL SIN QUE EL PROGRAMA SE ROMPA
    // =========================
    public static double leerDouble(Scanner teclado) {
        while (true) {
            try {
                return Double.parseDouble(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Debes escribir un número válido. Inténtalo de nuevo: ");
            }
        }
    }
}