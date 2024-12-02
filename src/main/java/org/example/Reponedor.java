package org.example;

public class Reponedor extends Thread {
    private float cantidad;
    private Tienda tienda;

    public Reponedor(float cantidad, Tienda tienda) {
        this.cantidad = cantidad;
        this.tienda = tienda;
    }

    @Override
    public void run() {
        try {
            // Simulamos la reposición periódica de dinero en la tienda
            while (true) {
                sleep(5000);  // El reponedor repone cada 5 segundos
                tienda.reponer(cantidad);  // Reponemos dinero en la tienda
                sleep(10000);  // Simulamos un tiempo para que los clientes compren
                tienda.vaciarTienda();  // Los productos se agotan después de un tiempo
            }
        } catch (InterruptedException e) {
            System.out.println("El reponedor ha sido interrumpido.");
        }
    }
}
