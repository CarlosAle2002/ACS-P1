package com.example.acsp1.model;

import java.util.Locale;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public void decrementarCantidad() {
        if (this.cantidad > 1) {
            this.cantidad--;
        }
    }

    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ItemCarrito that = (ItemCarrito) obj;
        return producto.equals(that.producto);
    }

    @Override
    public int hashCode() {
        return producto.hashCode();
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "ItemCarrito{producto=%s, cantidad=%d, subtotal=%.2f}",
                producto, cantidad, calcularSubtotal());
    }
}
