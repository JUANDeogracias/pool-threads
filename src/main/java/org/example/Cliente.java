package org.example;

public class Cliente extends Thread {
    private String nombre;
    private float cantidad;
    private Tienda tienda;

    public Cliente(String nombre, float cantidad, Tienda tienda) {
        setName(nombre);
        this.cantidad = cantidad;
        this.tienda = tienda;
    }

    @Override
    public void run() {
        tienda.comprar(cantidad);  // El cliente intenta comprar un producto
    }
}
