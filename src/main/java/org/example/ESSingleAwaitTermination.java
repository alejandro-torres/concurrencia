package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"CallToThreadStartDuringObjectConstruction","CallToPrintStackTrace","Convert2Lambda"})
public class ESSingleAwaitTermination {

    // En este ejemplo ponemos a prueba un hilo (newSingleThreadExecutor()) con el
    // m�todo awaitTermination(cantidadTiempo,UnidadTiempo) de ExecutorService, este m�todo fuerza al proceso principal
    // a esperar al hilo (o hilos) el tiempo indicado. Si el hilo termina antes de que se
    // complete el tiempo, el proceso principal continua desde ahí. Si el hilo tarda m�s
    // tiempo que la espera, el proceso principal continua pero el programa no se detiene
    // hasta que el hilo o hilos completan su tarea.
    public void execAwait(int sleep, int await) {
        try {
            ExecutorService exec = Executors.newSingleThreadExecutor();

            Runnable tarea = new Runnable() {
                @Override
                public void run() {
                    Main.Log("Inicio de la tarea");
                    try {
                        TimeUnit.SECONDS.sleep(sleep);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    Main.Log("Finaliza la tarea");
                }
            };

            exec.submit(tarea);

            exec.shutdown();
            exec.awaitTermination(await, TimeUnit.SECONDS);

            Main.Log("El hilo principal continua... ");

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
