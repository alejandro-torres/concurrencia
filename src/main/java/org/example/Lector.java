package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lector implements Runnable {

    CyclicBarrier barrier;
    List<Integer> lista;
    String url;
    int duration;
    // En este ejemplo usamos un CyclicBarrier con sobrecarga (sdeterminamos el número de hilos que va a esperar la barrera,
    // le damos una tarea (run()) que los hilos de forma conjunta completaran cuando todos hayan llegado a la barrera. Una vez
    // terminada la tarea especificada en el CyclicBarrier los hilos continuan con su proceso independientemente.
    public Lector(CyclicBarrier barrier, List<Integer> lista, String url, int duration) {
        this.barrier = barrier;
        this.lista = lista;
        this.url = url;
        this.duration = duration;
    }
      
    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            Files.lines(Path.of(url))
                    .map(s -> s.length())
                    .forEach(n -> lista.add(n));
            Main.Log("Antes de llegar a la barrera");
            barrier.await();
            // ejecuta el run() de la barrera
            Main.Log("Después de la barrera");
            
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName())
                    .log(Level.SEVERE, "Archivo no encontrado", ex);
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, "La barrera se ha roto", ex);
        } 
    
    }
    
}
