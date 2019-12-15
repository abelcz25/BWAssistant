package com.pavel.burrito.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.pavel.burrito.Adaptador.AdaptadorRe;
import com.pavel.burrito.Lista.Nota;
import com.pavel.burrito.Lista.NoteList;
import com.pavel.burrito.R;

public class NotaActivity extends AppCompatActivity {

    private NoteList notas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorRe adaptador;
    private FloatingActionButton fabAdd , fabOrder , fabSearch;
    private EditText edtTitulo , edtNota, edtBusqueda;
    private int count = 0 , countNotas = 0 , temp;
    Nota[] arreglo;

    //private String [] tNotas = new String[2];  //ARREGLO PARA GUARDAR LOS TITULOS DE LAS NOTAS Y FACILITAR ORDENAMIENTO DESPUES


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        Toast.makeText(NotaActivity.this , "Welcome!! To burrito assistant" , Toast.LENGTH_SHORT).show();

        //Edit Text
        edtNota = findViewById(R.id.edtNota);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtBusqueda = findViewById(R.id.busqueda);

        //FAB
        fabAdd = findViewById(R.id.fabAdd);
        fabOrder = findViewById(R.id.fabOrder);
        fabSearch = findViewById(R.id.fabSearch);
        fabSearch.setBackgroundColor(Color.GRAY);

        fabSearch.setEnabled(false);

        //Instanciamos lista de notas
        notas = new NoteList();


        recyclerView = findViewById(R.id.recyclerView); //Se obtiene el view desde lek layout
        layoutManager = new LinearLayoutManager(this);

        callAdapter();


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Boton de agregar notas
                String titulo = edtTitulo.getText().toString();
                String nota = edtNota.getText().toString();
                if (!titulo.equals("") && !nota.equals("")) {
                    agregarEnMas(titulo, nota); //tNotas

                }else{
                    Toast.makeText(NotaActivity.this , "Enter all the info" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabOrder.setOnClickListener(new View.OnClickListener() { //Boton para ordenar
            @Override
            public void onClick(View v) {
                Toast.makeText(NotaActivity.this , "Order" , Toast.LENGTH_SHORT).show();
                arreglo = new Nota[notas.size()];
                llenarArray(arreglo);
                noteOrder(arreglo);
                notas.emptyList();
                llenarLista(notas);
                callAdapter();
                fabSearch.setEnabled(true);

            }
        });

        fabSearch.setOnClickListener(new View.OnClickListener() { //Boton para buscar
            @Override
            public void onClick(View v) {
                String busq = edtBusqueda.getText().toString();
                try{
                    Toast.makeText(NotaActivity.this , "Search" , Toast.LENGTH_SHORT).show();
                    char busqC = busq.charAt(0);
                    int pFind = binSearch(arreglo,busqC);
                    if (pFind!= -1){

                        //AGREGAR AQUI FUNCION PARA MOSTRAR LA NOTA, COMO SI HUBIERA DADO CLIC EN ELLA

                        Toast.makeText(NotaActivity.this , "Element in position: "+ (pFind+1), Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(NotaActivity.this , "Cannot find any element" , Toast.LENGTH_SHORT).show();
                //Toast.makeText(NotaActivity.this , posicion , Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(NotaActivity.this, "First enter a text to search", Toast.LENGTH_SHORT).show();
                }}
        });
    }

    private void deletNota (int position){ //Elimina una posicion de una nota
        try {
            notas.eraseAt(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adaptador.notifyItemRemoved(position);
    }

    //METODO QUE AGREGUE NOTAS Y GUARDE LOS DATOS EN ARRAY
    public void agregarEnMas(String sTitulo, String sNota){

            notas.addAt(new Nota(sTitulo , sNota) , count);

            adaptador.notifyItemInserted(count);
            layoutManager.scrollToPosition(count);

            if (temp != count) {
                count = temp;
                count--;
                temp--;
            }

            temp++;
            count++;
            countNotas++;


    }
  public static int binSearch(Nota [] arreglo, char val){ //Busqueda binaria
        return binSearchRec(arreglo, val, 0, arreglo.length - 1);
    }

    private static int binSearchRec(Nota [] arreglo, char val,int lo, int hi){
        if (lo <= hi){
            int mid = lo + ((hi-lo)/2);
            if (val == arreglo[mid].getsTitulo().toLowerCase().charAt(0)){
                return mid;
            }else if(val > arreglo[mid].getsTitulo().toLowerCase().charAt(0)){//SI ES MAYOR

                return binSearchRec(arreglo, val, mid + 1, hi);



            }else{//SI ES MENOR
                return binSearchRec(arreglo, val, lo, mid - 1);
            }
        }else
            return -1;
    }

    //Ordena numericamente de menor a mayor,
    //tomando como entero unicamente el ULTIMO DIGITO DEL STRING
    public static void noteOrder(Nota [] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j].getsTitulo().toLowerCase().charAt(0) < arreglo[min].getsTitulo().toLowerCase().charAt(0)) {
                    min = j;
                }
            }
            //Swap
            Nota temp = arreglo[i];
            arreglo[i] = arreglo[min];
            arreglo[min] = temp;
        }

    }
    public void callAdapter (){ //Funcion para crear el adaptador
        adaptador = new AdaptadorRe(notas, R.layout.cardview, new AdaptadorRe.OnItemClickListener() {
            @Override
            public void click(Nota nota, int position) { //La interface que se hizo desde el adaptador

                Nota v = notas.getAt(position);
                Intent intent = new Intent(NotaActivity.this , DataActivity.class);
                intent.putExtra("Title",v.getsTitulo());
                intent.putExtra("Note" , v.getsNota());
                startActivity(intent);

            }

            @Override
            public void clickLong(Nota nota, int position) {
                deletNota(position);
                temp = count;
                count = position;
                countNotas--;
            }
        });

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(layoutManager); //
        recyclerView.setAdapter(adaptador);
    }

    public void llenarArray (Nota[] arreglo){
        for (int i = 0; i < arreglo.length ; i++) {
            arreglo[i] = notas.getAt(i);
        }
    }

    public void llenarLista (NoteList list){
        for (int i = 0; i < arreglo.length; i++) {
            list.addAt(arreglo[i] , i);
        }
    }

}
