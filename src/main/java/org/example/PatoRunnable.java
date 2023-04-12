package org.example;

public class PatoRunnable implements Runnable{

    private long id;
    private String nombre;
    public static Integer graznidos = 0;

    public PatoRunnable(){}

    public PatoRunnable(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public synchronized void run() {
        int numGraznidos = (int) (Math.random()*10+2);
        for (int i = 0; i < numGraznidos; i++){
            try {
                System.out.println("A DORMIR!!" + Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("A DESPERTAR!!" + Thread.currentThread().getName());

                graznidos++;

            } catch (InterruptedException e) {
                System.out.println("Se ha roto!" + e);
            }
        }
        System.out.println(this.nombre + " grazna " + numGraznidos + " veces y en total hay " + graznidos + " graznidos");
        Main.Log("id = " + this.id + ", nombre = " + this.nombre + ", ha terminado!!!!!");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
