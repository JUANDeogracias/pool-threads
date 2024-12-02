package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tienda {
    public float saldo;
    private final Lock lock = new ReentrantLock();
    public boolean disponible = false;  // Indica si la tienda está reabastecida
    public float precio_producto = 9.99F;  // Precio de los productos

    public Tienda(float saldo) {
        this.saldo = saldo;
    }

    // Método de compra
    public synchronized void comprar(float dinero) {
        try {
            // Esperamos hasta que la tienda esté reabastecida
            while (!disponible) {
                System.out.println(Thread.currentThread().getName() + " está esperando a que el reponedor reponga.");
                wait();  // Esperamos que el reponedor reponga la tienda
            }

            // Verificamos si el cliente tiene suficiente dinero
            if (dinero >= precio_producto) {
                float cambio = dinero - precio_producto;  // Calculamos el cambio que debe devolver
                saldo += precio_producto;  // La tienda recibe el dinero del cliente
                System.out.println(Thread.currentThread().getName() + " compró un producto. Cambio: " + cambio + "€. Saldo actual: " + saldo + "€.");
            } else {
                System.out.println(Thread.currentThread().getName() + " no tiene suficiente dinero.");
            }

            System.out.println(Thread.currentThread().getName() + " ha terminado la compra.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método de reposición
    public synchronized void reponer(float dinero) {
        if (!disponible) {
            saldo += dinero;  // El reponedor agrega dinero a la tienda
            System.out.println("La tienda ha sido reponida con " + dinero + "€. Saldo actual: " + saldo + "€.");
            disponible = true;  // Indicamos que la tienda ahora tiene productos disponibles
            notifyAll();  // Despertamos a los clientes que están esperando
        }
    }

    // Método para vaciar la tienda (cuando los productos se agoten)
    public synchronized void vaciarTienda() {
        disponible = false;  // La tienda ya no tiene productos
    }
}
