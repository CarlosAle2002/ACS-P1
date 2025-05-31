package com.example.acsp1;

import com.example.acsp1.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Laptop", 999.99);
    }

    @Test
    void testConstructorCompleto() {
        Producto nuevoProducto = new Producto(2, "Mouse", 25.50);
        assertEquals(2, nuevoProducto.getId());
        assertEquals("Mouse", nuevoProducto.getNombre());
        assertEquals(25.50, nuevoProducto.getPrecio(), 0.01);
    }

    @Test
    void testConstructorPrecioNegativo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(1, "Producto", -10.0)
        );
        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }

    @Test
    void testSetPrecioNegativo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> producto.setPrecio(-100.0)
        );
        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }

    @Test
    void testEqualsProductosIguales() {
        Producto producto2 = new Producto(1, "Otro nombre", 500.0);
        assertTrue(producto.equals(producto2));
    }

    @Test
    void testEqualsProductosDiferentes() {
        Producto producto2 = new Producto(2, "Mouse", 25.50);
        assertFalse(producto.equals(producto2));
    }
}
