package com.pavel.burrito.Lista;

import com.pavel.burrito.Lista.Nota;

public class NoteList {
    private Nota notaIni;
    private Nota notaFin;

    public NoteList(){
        this.notaFin = null;
        this.notaIni = null;
    }

    public boolean isEmpty(){ //is empty jsjs
        if(notaIni == null)
            return true;
        else
            return false;
    }

    public int size(){ //tamano de la lista
        int cont = 0;
        Nota temp = notaIni; //Se toma temp como nodo, solo para recorrer la lista
        while (temp != null){
            cont++;
            temp = temp.getNext();
        }
        return cont;
    }

    public void emptyList(){
        notaFin = null;
        notaIni = null;
    }

    public void add(Nota nueva){ //agregar nota al final de la lista
        if(isEmpty()){
            notaIni = nueva;
            notaFin = nueva;
        }else{
            notaFin.setNext(nueva);
            notaFin = nueva;
        }
    }

    public void addBegin(Nota nueva){ //agregar nota al inicio de la lista
        if(isEmpty()){
            notaIni = nueva;
            notaFin = nueva;
        }else{
            nueva.setNext(notaIni);
            notaIni = nueva;
        }
    }

    //Agregué este por si acaso
    public void addAt(Nota nueva, int pos){ //agregar nota en una posicion
        if(isEmpty()){
            notaIni = nueva;
            notaFin = nueva;
        }else{
            if((pos < 0) || (pos > size())){
                System.out.println("NO SE QUE QUIERAS HACER EN ESTE CASO SJSJ");
            }else{
                int cont = 0;
                Nota temp = notaIni;//Se toma temp como nodo, solo para recorrer la lista
                while(cont < (pos-1)){
                    temp = temp.getNext();
                    cont++;
                }

                nueva.setNext(temp.getNext());
                temp.setNext(nueva);
            }
        }
    }

    //Borrar una nota en una posicion,
    //Esto podria ser util pa borrar las notas, si es que quieren
    public void eraseAt(int pos) throws Exception{
        if(isEmpty())
            throw new Exception("Poner un TOAST aqui o no se sjsj");
        if((pos < 0) || (pos > size())) //rangos de la lista
            throw new Exception("Lo mismo aqui sjsjsj");

        if(size() == 1) //si el tamano es uno, pss vaciarla
            emptyList();
        else{
            if(pos == 0) //si la pos es 0, solo cambiar la notaInicial
                notaIni = notaIni.getNext();
            else{
                int cont = 0;
                Nota temp = notaIni; //Se toma temp como nodo, solo para recorrer la lista
                while(cont < (pos-1)){
                    temp = temp.getNext();
                    cont++;
                }

                temp.setNext(temp.getNext().getNext());
                if(pos == (size()-1))
                    notaFin = temp;
            }
        }
    }

    public String getNotaAt(int pos){ //obtener el texto de una Nota de la lista
        Nota temp = notaIni; //Se toma  temp como nodo, solo para recorrer la lista
        int cont = 0;
        while(cont < pos){
            temp = temp.getNext();
            cont++;
        }
        return temp.getsNota();
    }

    public String getTituloAt(int pos){ //obtener el titulo de una Nota de la lista
        Nota temp = notaIni; //Se toma  temp como nodo, solo para recorrer la lista
        int cont = 0;
        while(cont < pos){
            temp = temp.getNext();
            cont++;
        }
        return temp.getsTitulo();
    }

    public Nota getAt(int pos){ //REGRESA EL OBJETO NOTA DE LA LISTA
        Nota temp = notaIni; //Se toma  temp como nodo, solo para recorrer la lista
        int cont = 0;
        while(cont < pos){
            temp = temp.getNext();
            cont++;
        }
        return temp;
    }

    public void set (int pos , Nota nota){
        try {
            eraseAt(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addAt(nota , pos);
    }
}
