package com.pavel.burrito.Lista;

public class Nota {
    private String sTitulo;
    private String sNota;
    private Nota next;

    public Nota() {
    }

    public Nota(String sTitulo, String sNota) {
        this.sTitulo = sTitulo;
        this.sNota = sNota;
    }

    public String getsTitulo() {
        return sTitulo;
    }

    public void setsTitulo(String sTitulo) {
        this.sTitulo = sTitulo;
    }

    public String getsNota() {
        return sNota;
    }

    public void setsNota(String sNota) {
        this.sNota = sNota;
    }

    public Nota getNext() {
        return next;
    }

    public void setNext(Nota next) {
        this.next = next;
    }
}
