package org.example;


import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutorServiceCallable implements Callable<String> {

    // En este ejemplo, trabajamos con la interfaz Callable(método call()) que es similar a Runnable(método run())
    // pero con las diferencias que su método propaga Exception y devuelve un valor genérico.
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public String call() throws Exception {
        Main.Log("Inicio de la tarea");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.Log("Finaliza la tarea");
        return "Resultado de la tarea";
    }

    @SuppressWarnings("UseSpecificCatch")
    public void executorCall() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            //Future es una clase que monitoriza nuestro hilo y encapsula la devolución de call()
            Future<String> future
                    = exec.submit(new ExecutorServiceCallable());


            int cont = 0;
            String resultado = "";
            Main.Log(future.isDone());
            while (!future.isDone()) { // isDone() nos informa de si el hilo ha terminado su tarea
                TimeUnit.MILLISECONDS.sleep(100);
                cont++;
                Main.Log("Vuelta nº"+cont);
            }
            if(!future.isCancelled()) { // isCancelled() nos devuelve booleano en función de si nuestro hilo ha sido cancelado o no
                resultado = future.get();// get() espera a que el hilo termine su tarea para devolvernos el valor
                future.cancel(true); // Este método no hace nada aqui pero sirve para cancelar el hilo
            } 
            Main.Log(future.isDone());

            Main.Log(resultado);

            exec.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(ExecutorServiceCallable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
