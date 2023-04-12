package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final Instant INICIO = Instant.now();


    public static void main(String[] args) {
        /*
        BuscarPalabra b = new BuscarPalabra("Torpedo");
        BuscarPalabra b2 = new BuscarPalabra("Rodrigor");
        BuscarPalabra b3 = new BuscarPalabra("Lucas");
        BuscarPalabra b4 = new BuscarPalabra("fistro");
        BuscarPalabra b5 = new BuscarPalabra("Quietooor");

        System.out.println(Thread.currentThread().getName() + " ha terminado");
        */
        /*
        PatoThread p = new PatoThread(0, "Lucas");
        PatoThread p1 = new PatoThread(1, "Juan");
        PatoThread p2 = new PatoThread(2, "Juana");
        PatoThread p3 = new PatoThread(3, "Blanca");
        PatoThread p4 = new PatoThread(4, "Darwin");

        p.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();*/


        PatoRunnable pr = new PatoRunnable(0, "Donald");
        PatoRunnable pr1 = new PatoRunnable(1, "Jake");
        PatoRunnable pr2 = new PatoRunnable(2, "Tatcher");
        PatoRunnable pr3 = new PatoRunnable(3, "ElColetas");
        PatoRunnable pr4 = new PatoRunnable(4, "M.Rajoy");

        new Thread(pr).start();
        new Thread(pr1).start();
        new Thread(pr2).start();
        new Thread(pr3).start();
        new Thread(pr4).start();


        try {
            TimeUnit.SECONDS.sleep(21);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(Thread.currentThread().getName() + " números de graznidos: " + PatoThread.graznidos);

        System.out.println(Thread.currentThread().getName() + " números de graznidos: " + PatoRunnable.graznidos);


    }


    public static void Log(Object mensaje) {
        System.out.println(String.format("%s [%s] %s",
                Duration.between(INICIO,
                        Instant.now()),
                Thread.currentThread().getName(),
                mensaje.toString()));
    }


}