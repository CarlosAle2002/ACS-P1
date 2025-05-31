package com.example.acsp1;

import com.example.acsp1.model.Carrito;
import com.example.acsp1.model.ItemCarrito;
import com.example.acsp1.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcsP1Application {

    private static Carrito carrito = new Carrito();
    private static List<Producto> catalogoProductos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarCatalogo();
        mostrarBienvenida();

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    agregarProductoAlCarrito();
                    break;
                case 3:
                    eliminarProductoDelCarrito();
                    break;
                case 4:
                    modificarCantidadProducto();
                    break;
                case 5:
                    mostrarCarrito();
                    break;
                case 6:
                    vaciarCarrito();
                    break;
                case 7:
                    mostrarResumenCarrito();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("¡Gracias por usar nuestro carrito de compras!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }

            if (continuar) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void inicializarCatalogo() {
        catalogoProductos.add(new Producto(1, "Laptop HP", 45000.00));
        catalogoProductos.add(new Producto(2, "Mouse Logitech", 1500.00));
        catalogoProductos.add(new Producto(3, "Teclado Mecánico", 3500.00));
        catalogoProductos.add(new Producto(4, "Monitor 24\"", 12000.00));
        catalogoProductos.add(new Producto(5, "Auriculares", 2500.00));
        catalogoProductos.add(new Producto(6, "Webcam HD", 4000.00));
        catalogoProductos.add(new Producto(7, "Disco Duro 1TB", 5500.00));
        catalogoProductos.add(new Producto(8, "Memoria RAM 16GB", 8000.00));
    }

    private static void mostrarBienvenida() {
        System.out.println("--------------------------------------------");
        System.out.println("         CARRITO DE COMPRAS ");
        System.out.println("--------------------------------------------");
        System.out.println("¡Bienvenido a nuestra tienda de tecnología!");
        System.out.println("--------------------------------------------\n");
    }

    private static void mostrarMenu() {
        System.out.println("\n|-----------------------------------------|");
        System.out.println("|               MENÚ PRINCIPAL            |");
        System.out.println("|-----------------------------------------|");
        System.out.println("| 1.  Ver catálogo de productos           |");
        System.out.println("| 2.  Agregar producto al carrito         |");
        System.out.println("| 3.  Eliminar producto del carrito       |");
        System.out.println("| 4.  Modificar cantidad de producto      |");
        System.out.println("| 5.  Ver carrito actual                  |");
        System.out.println("| 6.  Vaciar carrito                      |");
        System.out.println("| 7.  Resumen del carrito                 |");
        System.out.println("| 8.  Salir                               |");
        System.out.println("|-----------------------------------------|");
        System.out.print("Seleccione una opción (1-8): ");
    }

    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void mostrarCatalogo() {
        System.out.println("\n CATÁLOGO DE PRODUCTOS");
        System.out.println("--------------------------------------------");
        System.out.printf("%-4s %-20s %15s\n", "ID", "PRODUCTO", "PRECIO (RD$)");
        System.out.println("--------------------------------------------");

        for (Producto producto : catalogoProductos) {
            System.out.printf("%-4d %-20s %,15.2f\n",
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio());
        }
        System.out.println("--------------------------------------------");
    }

    private static void agregarProductoAlCarrito() {
        System.out.println("\n➕ AGREGAR PRODUCTO AL CARRITO");
        System.out.println("--------------------------------------------");

        mostrarCatalogo();

        System.out.print("\nIngrese el ID del producto: ");
        try {
            int idProducto = Integer.parseInt(scanner.nextLine().trim());
            Producto producto = buscarProductoPorId(idProducto);

            if (producto == null) {
                System.out.println(" Producto no encontrado.");
                return;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            carrito.agregarProducto(producto, cantidad);
            System.out.println(" Producto agregado exitosamente:");
            System.out.println("   " + producto.getNombre() + " (Cantidad: " + cantidad + ")");

        } catch (NumberFormatException e) {
            System.out.println(" Error: Debe ingresar números válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private static void eliminarProductoDelCarrito() {
        System.out.println("\n➖ ELIMINAR PRODUCTO DEL CARRITO");
        System.out.println("--------------------------------------------");

        if (carrito.estaVacio()) {
            System.out.println(" El carrito está vacío.");
            return;
        }

        mostrarProductosEnCarrito();

        System.out.print("\nIngrese el ID del producto a eliminar: ");
        try {
            int idProducto = Integer.parseInt(scanner.nextLine().trim());
            Producto producto = buscarProductoPorId(idProducto);

            if (producto == null) {
                System.out.println(" Producto no encontrado.");
                return;
            }

            boolean eliminado = carrito.eliminarProducto(producto);
            if (eliminado) {
                System.out.println(" Producto eliminado exitosamente: " + producto.getNombre());
            } else {
                System.out.println(" El producto no está en el carrito.");
            }

        } catch (NumberFormatException e) {
            System.out.println(" Error: Debe ingresar un número válido.");
        }
    }

    private static void modificarCantidadProducto() {
        System.out.println("\n✏ MODIFICAR CANTIDAD DE PRODUCTO");
        System.out.println("--------------------------------------------");

        if (carrito.estaVacio()) {
            System.out.println(" El carrito está vacío.");
            return;
        }

        mostrarProductosEnCarrito();

        System.out.print("\nIngrese el ID del producto: ");
        try {
            int idProducto = Integer.parseInt(scanner.nextLine().trim());
            Producto producto = buscarProductoPorId(idProducto);

            if (producto == null) {
                System.out.println(" Producto no encontrado.");
                return;
            }

            System.out.print("Ingrese la nueva cantidad: ");
            int nuevaCantidad = Integer.parseInt(scanner.nextLine().trim());

            boolean modificado = carrito.modificarCantidadProducto(producto, nuevaCantidad);
            if (modificado) {
                System.out.println(" Cantidad modificada exitosamente:");
                System.out.println("   " + producto.getNombre() + " (Nueva cantidad: " + nuevaCantidad + ")");
            } else {
                System.out.println(" El producto no está en el carrito.");
            }

        } catch (NumberFormatException e) {
            System.out.println(" Error: Debe ingresar números válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private static void mostrarCarrito() {
        System.out.println("\n CARRITO DE COMPRAS");
        System.out.println("--------------------------------------------");

        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.printf("%-4s %-20s %8s %12s %15s\n",
                "ID", "PRODUCTO", "CANTIDAD", "PRECIO UNIT.", "SUBTOTAL");
        System.out.println("--------------------------------------------");

        for (ItemCarrito item : carrito.getItems()) {
            Producto producto = item.getProducto();
            System.out.printf("%-4d %-20s %8d %,12.2f %,15.2f\n",
                    producto.getId(),
                    producto.getNombre(),
                    item.getCantidad(),
                    producto.getPrecio(),
                    item.calcularSubtotal());
        }

        System.out.println("--------------------------------------------");
        System.out.printf("TOTAL: RD$ %,.2f\n", carrito.calcularTotal());
        System.out.println("--------------------------------------------");
    }

    private static void mostrarProductosEnCarrito() {
        System.out.println("\nProductos en el carrito:");
        System.out.println("--------------------------------------------");

        for (ItemCarrito item : carrito.getItems()) {
            Producto producto = item.getProducto();
            System.out.printf("ID: %d - %s (Cantidad: %d)\n",
                    producto.getId(),
                    producto.getNombre(),
                    item.getCantidad());
        }
    }

    private static void vaciarCarrito() {
        System.out.println("\n️ VACIAR CARRITO");
        System.out.println("--------------------------------------------");

        if (carrito.estaVacio()) {
            System.out.println(" El carrito ya está vacío.");
            return;
        }

        System.out.print("¿Está seguro de que desea vaciar el carrito? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            carrito.vaciarCarrito();
            System.out.println(" Carrito vaciado exitosamente.");
        } else {
            System.out.println(" Operación cancelada.");
        }
    }

    private static void mostrarResumenCarrito() {
        System.out.println("\n RESUMEN DEL CARRITO");
        System.out.println("--------------------------------------------");

        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.println("Cantidad de tipos de productos: " + carrito.obtenerCantidadItems());
        System.out.println("Cantidad total de productos: " + carrito.obtenerCantidadTotalProductos());
        System.out.printf("Total a pagar: RD$ %,.2f\n", carrito.calcularTotal());

        System.out.println("\nDesglose por producto:");
        System.out.println("--------------------------------------------");
        for (ItemCarrito item : carrito.getItems()) {
            System.out.printf("• %s x%d = RD$ %,.2f\n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
    }

    private static Producto buscarProductoPorId(int id) {
        return catalogoProductos.stream()
                .filter(producto -> producto.getId() == id)
                .findFirst()
                .orElse(null);
    }
}