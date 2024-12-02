package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda(0);  // Inicializa la tienda con 0€ para que los clientes esperen al reponedor

        Reponedor reponedor = new Reponedor(100.F, tienda);  // Reponedor con 100€
        reponedor.start();  // Inicia el reponedor

        ExecutorService clientes = Executors.newFixedThreadPool(5);  // Pool de clientes

        // Crear y enviar 10 clientes al pool de hilos
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente("Cliente " + i, 10.F, tienda);  // Cada cliente tiene 10€ para comprar
            clientes.submit(cliente);
        }

        try {
            Thread.sleep(20000);  // Simula la ejecución durante 20 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            clientes.shutdown();  // Detiene el pool de clientes
        }
    }
}
