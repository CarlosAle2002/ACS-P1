package com.example.acsp1;

import com.example.acsp1.model.Carrito;
import com.example.acsp1.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    private Carrito carrito;
    private Producto laptop;
    private Producto mouse;
    private Producto teclado;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        laptop = new Producto(1, "Laptop", 1000.0);
        mouse = new Producto(2, "Mouse", 25.0);
        teclado = new Producto(3, "Teclado", 75.0);
    }

    @Test
    void testCarritoVacioInicial() {
        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.obtenerCantidadItems());
        assertEquals(0.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testAgregarProducto() {
        carrito.agregarProducto(laptop, 1);
        assertFalse(carrito.estaVacio());
        assertEquals(1, carrito.obtenerCantidadItems());
        assertEquals(1000.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testAgregarMultiplesProductos() {
        carrito.agregarProducto(laptop, 1);
        carrito.agregarProducto(mouse, 2);
        carrito.agregarProducto(teclado, 1);

        assertEquals(3, carrito.obtenerCantidadItems());
        assertEquals(4, carrito.obtenerCantidadTotalProductos());
        assertEquals(1125.0, carrito.calcularTotal(), 0.01); // 1000 + 50 + 75
    }

    @Test
    void testAgregarProductoExistente() {
        carrito.agregarProducto(laptop, 1);
        carrito.agregarProducto(laptop, 2);

        assertEquals(1, carrito.obtenerCantidadItems());
        assertEquals(3, carrito.obtenerCantidadTotalProductos());
        assertEquals(3000.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testAgregarProductoNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> carrito.agregarProducto(null, 1)
        );
        assertEquals("El producto no puede ser null", exception.getMessage());
    }

    @Test
    void testAgregarProductoCantidadCero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> carrito.agregarProducto(laptop, 0)
        );
        assertEquals("La cantidad debe ser mayor a 0", exception.getMessage());
    }

    @Test
    void testEliminarProducto() {
        carrito.agregarProducto(laptop, 2);
        carrito.agregarProducto(mouse, 1);

        boolean eliminado = carrito.eliminarProducto(laptop);

        assertTrue(eliminado);
        assertEquals(1, carrito.obtenerCantidadItems());
        assertEquals(25.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testEliminarProductoInexistente() {
        carrito.agregarProducto(mouse, 1);
        boolean eliminado = carrito.eliminarProducto(laptop);
        assertFalse(eliminado);
        assertEquals(1, carrito.obtenerCantidadItems());
    }

    @Test
    void testModificarCantidadProducto() {
        carrito.agregarProducto(laptop, 2);
        boolean modificado = carrito.modificarCantidadProducto(laptop, 5);

        assertTrue(modificado);
        assertEquals(5000.0, carrito.calcularTotal(), 0.01);
        assertEquals(5, carrito.obtenerCantidadTotalProductos());
    }

    @Test
    void testModificarCantidadProductoInexistente() {
        carrito.agregarProducto(mouse, 1);
        boolean modificado = carrito.modificarCantidadProducto(laptop, 3);
        assertFalse(modificado);
        assertEquals(1, carrito.obtenerCantidadTotalProductos());
    }

    @Test
    void testModificarCantidadCero() {
        carrito.agregarProducto(laptop, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> carrito.modificarCantidadProducto(laptop, 0)
        );
        assertEquals("La cantidad debe ser mayor a 0", exception.getMessage());
    }

    @Test
    void testVaciarCarrito() {
        carrito.agregarProducto(laptop, 2);
        carrito.agregarProducto(mouse, 1);

        assertFalse(carrito.estaVacio());
        carrito.vaciarCarrito();

        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.obtenerCantidadItems());
        assertEquals(0.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testGetItems() {
        carrito.agregarProducto(laptop, 1);
        carrito.agregarProducto(mouse, 2);

        var items = carrito.getItems();
        assertEquals(2, items.size());

        items.clear();
        assertEquals(2, carrito.obtenerCantidadItems());
    }

    @Test
    void testToStringCarritoVacio() {
        String resultado = carrito.toString();
        assertEquals("Carrito vacío", resultado);
    }

    @Test
    void testEscenarioCompletoCarrito() {
        carrito.agregarProducto(laptop, 1);
        carrito.agregarProducto(mouse, 2);
        carrito.agregarProducto(teclado, 1);

        assertEquals(3, carrito.obtenerCantidadItems());
        assertEquals(1125.0, carrito.calcularTotal(), 0.01);

        carrito.modificarCantidadProducto(mouse, 3);
        assertEquals(1150.0, carrito.calcularTotal(), 0.01);

        carrito.eliminarProducto(teclado);
        assertEquals(2, carrito.obtenerCantidadItems());
        assertEquals(1075.0, carrito.calcularTotal(), 0.01);

        carrito.vaciarCarrito();
        assertTrue(carrito.estaVacio());
        assertEquals(0.0, carrito.calcularTotal(), 0.01);
    }
}
