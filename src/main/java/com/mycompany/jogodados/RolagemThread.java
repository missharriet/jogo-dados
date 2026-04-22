package com.mycompany.jogodados;
public class RolagemThread extends Thread {

    private boolean rodando = true;

    public void parar() {
        rodando = false;
    }

    @Override
    public void run() {

        try {
            while (rodando) {
                System.out.print(".");
                Thread.sleep(300); // pausa de 300ms
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}