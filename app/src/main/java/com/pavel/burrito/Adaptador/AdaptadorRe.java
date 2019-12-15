package com.pavel.burrito.Adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavel.burrito.Lista.Nota;
import com.pavel.burrito.Lista.NoteList;
import com.pavel.burrito.R;

public class AdaptadorRe extends RecyclerView.Adapter<AdaptadorRe.ViewHolder> {  //Se usa  <Adapatadpr.ViewHolder> para mandar al recycler view la clase ViewHolder de los items
    private NoteList notas;
    private int layout; //View para inflar de cada item
    private OnItemClickListener clkListener;

    public AdaptadorRe(NoteList notas, int layout, OnItemClickListener clkListener) {
        this.notas = notas; //Las notas
        this.layout = layout; //Es la ventana que se va a poner en cada item
        this.clkListener = clkListener; //Va a atrapar el click que cardview va a mandar desde el item

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Aqui es donde se crea el view holder
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout , viewGroup , false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
        //Aqui se infla la vista desde aqui y hacemos una instancia de nuestra clase viewHolder que le va a mandar los datos a la superClase de ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Aqui es mandos los datos y aqui en donde se van a poder actualizar
        //Se actualizan los datos
        viewHolder.bind(notas.getAt(position) , clkListener); //Se manda lo datos de la notas y se manda el evento on click hacia un metodo del viewhHolder para actualizar los datos
    }
    //Numero de item que tenemos en la lista
    @Override
    public int getItemCount() {
        return notas.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView titulo;
        public TextView nota;

        public ViewHolder(@NonNull View itemView) {  //Este constructor manda el view al ViewHolder y infla desde la clase
            super(itemView);
            this.titulo = itemView.findViewById(R.id.txvTitulo);
            this.nota = itemView.findViewById(R.id.txvNota);


        }
        //En bind vamos a hacer la funcionalidad del click listener
        public void bind(final Nota nota , final OnItemClickListener clcListener){
            this.titulo.setText(nota.getsTitulo());
            this.nota.setText(nota.getsNota());

            //itemview viene de clase view holder del recycler view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clcListener.click(nota , getAdapterPosition());  //Esta es la posicion donde se va hacer
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clcListener.clickLong(nota , getAdapterPosition());
                    return false;
                }
            });
        }
    }



    public interface OnItemClickListener {  //Cuando se realiza un click se usara esta interface para cacharlo
        void click(Nota nota ,int position); //Y este metodo se usara desde la nota para mandar los datos y obtener la posicion
        void clickLong (Nota nota ,int position); //Este meteodo se usara para implementar el long click y poder eliminar las notasssssssssssssssasssssssssssssss
    }
}