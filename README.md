# Práctica #1 - Carrito de Compras con Pruebas Unitarias JUnit
## Carlos Ale. Mena Moronta ID: 10144554
### Este proyecto implementa un sistema de carrito de compras utilizando Java y programación orientada a objetos, con un enfoque en pruebas unitarias usando JUnit 5 para garantizar la calidad del código.
## Descripción del Proyecto
### El sistema permite gestionar un carrito de compras con las siguientes funcionalidades:
#### - Agregar productos al carrito
#### - Eliminar productos del carrito
#### - Modificar cantidades de productos
#### - Calcular el total de la compra
#### - Gestionar items del carrito
## Clases Implementadas
### Producto
#### - Atributos: id, nombre, precio
#### - Funcionalidades: Getters/Setters, validación de precio, equals/hashCode
#### - Validaciones: Precio no negativo
### ItemCarrito
#### - Atributos: producto, cantidad
#### - Funcionalidades: Cálculo de subtotal, incrementar/decrementar cantidad
#### - Validaciones: Producto no nulo, cantidad mayor a 0
### Carrito
#### - Funcionalidades:
#### - Agregar productos (con acumulación si ya existe)
#### - Eliminar productos
#### - Modificar cantidades
#### - Calcular total
#### - Obtener estadísticas del carrito
#### - Vaciar carrito
### Practica1Application (Clase Principal)
#### - Funcionalidades:
#### - Interfaz de usuario por consola interactiva
#### - Menú principal con 8 opciones
#### - Catálogo predefinido de 8 productos tecnológicos
#### - Gestión completa del carrito de compras
#### - Validación de entrada de usuario
#### - Formateo de precios en pesos dominicanos (RD$)
## Ejecución de Pruebas
### Clonar el repositorio:
### git clone https://github.com/CarlosAle2002/ACS-P1.git
## Ejecutar la aplicación principal:
### Ejecutar Practica1Application.java desde el IDE
### O usar Maven: mvn exec
## Ejecutar pruebas unitarias:
### Ejecutar directamente e individualmente desde un IDE: ProductoTest.java, ItemCarritoTest.java, CarritoTest.java
### O ejecutar todas: mvn test
---

# Cobertura de Pruebas,
| **Categoría**        | **Número de Pruebas**  | **Descripción** |
|----------------------|------------------------|-----------------|
| **ProductoTest**     | 5                     | Constructor, getters/setters, validaciones, equals/hashCode. |
| **ItemCarritoTest**  | 10                     | Constructor, cálculos, modificaciones, validaciones. |
| **CarritoTest**      | 15                     | Operaciones CRUD, cálculos, validaciones, casos límite. |
| **Total**            | 30                     | Cobertura completa de funcionalidades. |
---
