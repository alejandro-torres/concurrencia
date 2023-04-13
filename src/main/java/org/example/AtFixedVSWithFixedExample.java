package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


@SuppressWarnings({"CallToThreadStartDuringObjectConstruction","CallToPrintStackTrace","Convert2Lambda","SleepWhileInLoop"})
public class AtFixedVSWithFixedExample {

    private static int count = 0;

    /*
    Diferencias entre AtFixedRate y WithFixedDelay:
    
    Por ejemplo, supongamos que programo una alarma para que suene con una frecuencia fija de una vez por hora, y cada vez que suena, tomo una taza de café, lo que demora 10 minutos. Supongamos que comienza a la medianoche, tendría:

        00:00: Start making coffee
        00:10: Finish making coffee
        01:00: Start making coffee
        01:10: Finish making coffee
        02:00: Start making coffee
        02:10: Finish making coffee
    
        Si programo con un retraso fijo de una hora, tendría:

        00:00: Start making coffee
        00:10: Finish making coffee
        01:10: Start making coffee
        01:20: Finish making coffee
        02:20: Start making coffee
        02:30: Finish making coffee
    
        Cuál quieres depende de tu tarea.
     */
    public void atFixedRateExample() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            count++;
            System.out.println("Running...task1 - count : " + count);
        };

        // init Delay = 5, repeat the task every 1 second
//        ScheduledFuture<?> scheduledFuture = ses.scheduleWithFixedDelay(task1, 3, 3, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 3, 2, TimeUnit.SECONDS);

        while (true) {
            System.out.println("count :" + count);
            try {
                System.out.println(" Antes de dormir " + LocalDateTime.now());
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(AtFixedVSWithFixedExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (count == 5) {
                System.out.println("Count is" + count + ", cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                ses.shutdown();
                Main.Log("atFixedRate termina");
                break;
            }

        }

    }

    public void withFixedDelayExample() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            count++;
            System.out.println("Running...task2 - count : " + count);
        };

        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleWithFixedDelay(task1, 2, 2, TimeUnit.SECONDS);

        while (true) {
            System.out.println("count :" + count);
            try {
                System.out.println(" Antes de dormir " + LocalDateTime.now());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AtFixedVSWithFixedExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (count == 5) {
                System.out.println("Count is " + count + ", cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                ses.shutdown();
                Main.Log("withFixedDelay termina");
                break;
            }

        }

    }
}
