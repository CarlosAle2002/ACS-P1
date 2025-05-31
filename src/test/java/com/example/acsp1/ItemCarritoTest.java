package com.example.acsp1;

import com.example.acsp1.model.Producto;
import com.example.acsp1.model.ItemCarrito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ItemCarritoTest {

    private Producto producto;
    private ItemCarrito itemCarrito;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Laptop", 1000.0);
        itemCarrito = new ItemCarrito(producto, 2);
    }

    @Test
    void testConstructor() {
        assertEquals(producto, itemCarrito.getProducto());
        assertEquals(2, itemCarrito.getCantidad());
    }

    @Test
    void testConstructorProductoNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ItemCarrito(null, 1)
        );
        assertEquals("El producto no puede ser null", exception.getMessage());
    }

    @Test
    void testConstructorCantidadCero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ItemCarrito(producto, 0)
        );
        assertEquals("La cantidad debe ser mayor a 0", exception.getMessage());
    }

    @Test
    void testCalcularSubtotal() {
        double subtotalEsperado = 1000.0 * 2;
        assertEquals(subtotalEsperado, itemCarrito.calcularSubtotal(), 0.01);
    }

    @Test
    void testIncrementarCantidad() {
        int cantidadInicial = itemCarrito.getCantidad();
        itemCarrito.incrementarCantidad();
        assertEquals(cantidadInicial + 1, itemCarrito.getCantidad());
    }

    @Test
    void testDecrementarCantidad() {
        int cantidadInicial = itemCarrito.getCantidad();
        itemCarrito.decrementarCantidad();
        assertEquals(cantidadInicial - 1, itemCarrito.getCantidad());
    }

    @Test
    void testDecrementarCantidadMinimo() {
        ItemCarrito item = new ItemCarrito(producto, 1);
        item.decrementarCantidad();
        assertEquals(1, item.getCantidad());
    }

    @Test
    void testSetCantidadCero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> itemCarrito.setCantidad(0)
        );
        assertEquals("La cantidad debe ser mayor a 0", exception.getMessage());
    }

    @Test
    void testEqualsItemsIguales() {
        ItemCarrito item2 = new ItemCarrito(producto, 3);
        assertTrue(itemCarrito.equals(item2));
    }

    @Test
    void testEqualsItemsDiferentes() {
        Producto otroProducto = new Producto(2, "Mouse", 25.0);
        ItemCarrito item2 = new ItemCarrito(otroProducto, 2);
        assertFalse(itemCarrito.equals(item2));
    }
}
