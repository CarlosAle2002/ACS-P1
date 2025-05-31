package com.example.acsp1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Optional<ItemCarrito> itemExistente = buscarItemPorProducto(producto);

        if (itemExistente.isPresent()) {
            ItemCarrito item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
    }

    public boolean eliminarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        return items.removeIf(item -> item.getProducto().equals(producto));
    }

    public boolean modificarCantidadProducto(Producto producto, int nuevaCantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Optional<ItemCarrito> itemExistente = buscarItemPorProducto(producto);

        if (itemExistente.isPresent()) {
            itemExistente.get().setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::calcularSubtotal)
                .sum();
    }

    public int obtenerCantidadItems() {
        return items.size();
    }

    public int obtenerCantidadTotalProductos() {
        return items.stream()
                .mapToInt(ItemCarrito::getCantidad)
                .sum();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }

    private Optional<ItemCarrito> buscarItemPorProducto(Producto producto) {
        return items.stream()
                .filter(item -> item.getProducto().equals(producto))
                .findFirst();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Carrito vacío";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Carrito de compras:\n");
        for (ItemCarrito item : items) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        sb.append(String.format("Total: $%.2f", calcularTotal()));

        return sb.toString();
    }
}
