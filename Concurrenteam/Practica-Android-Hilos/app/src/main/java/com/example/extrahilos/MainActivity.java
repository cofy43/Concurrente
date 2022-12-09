package com.example.extrahilos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.extrahilos.Hilos.SumaMatrices;
import com.example.extrahilos.Hilos.SumaMatricesConcurrente;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText num_hilos;
    EditText edit_matriz;
    Button boton;
    TextView resultado, resultadoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_hilos = (EditText) findViewById(R.id.edit_num_hilos);
        edit_matriz = (EditText) findViewById(R.id.edit_matriz);
        boton = (Button) findViewById(R.id.ejecutar);
        resultado = (TextView) findViewById(R.id.resultado);
        resultadoC = (TextView) findViewById(R.id.resultadoC);

        boton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int hilos = Integer.parseInt(String.valueOf(num_hilos.getText()));
                String mat = "" + edit_matriz.getText();
                boolean incorrectOption = !mat.equals("10") && !mat.equals("100") && !mat.equals("1000");
                if (incorrectOption) {
                    edit_matriz.setError("Solo se aceptan los valores: 10, 100, 1000");
                    return;
                }
                String res = "";
                String resC = "";
                try{
                    int file = mat.equals("10") ? R.raw.mat10 : mat.equals("100") ? R.raw.mat100 : R.raw.mat1000;
                    //leemos las matrices
                    int[][] matriz = leer(file, Integer.parseInt(mat));

                    //creamos el objeto
                    SumaMatrices secuencial = new SumaMatrices(matriz, matriz);
                    SumaMatricesConcurrente concurrente = new SumaMatricesConcurrente(matriz, matriz);

                    //Si es secuencial tomamos tiempo con 1 hilo
                    long tInicio, tFin;
                    tInicio = System.nanoTime();
                    secuencial.sumaSecuencial();
                    tFin = System.nanoTime()-tInicio;
                    res = "Secuencial:\n"+tFin;
                    resultado.setText(res);

                    //En otro caso tomamos tiempo con los hilos puestos
                    List<Thread> hilosL = new ArrayList<>();
                    long tInicioC, tFinC;
                    tInicioC = System.nanoTime();
                    for(int i = 0; i < matriz.length; i++){
                        Thread t = new Thread(concurrente,""+i);
                        hilosL.add(t);
                        t.start();
                        if(hilosL.size() == hilos){
                            for(Thread threads: hilosL){
                                threads.join();
                            }
                            hilosL.clear();
                        }
                    }
                    for(Thread threads: hilosL){
                        threads.join();
                    }
                    tFinC = System.nanoTime()-tInicioC;
                    resC = "Concurrente:\n"+tFinC;
                    resultadoC.setText(resC);
                }catch (IOException | InterruptedException e){//La primer excepcion va, la segunda dependiendo de como leyeron su archvio
                    e.printStackTrace();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[][] leer(int file, int size) throws  IOException{
        int[][] matriz = new int[size][size];
        InputStream is = this.getResources().openRawResource(file);//actualicen esto, para poner cualquier archvio
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            matriz[i] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            i += 1;
        }
        for (int j = 0; j < matriz.length; j++) {
            line = "";
            for (int k = 0; k < matriz[j].length; k++) {
                line += " " + matriz[j][k];
            }
            System.out.println(line);
        }
        return matriz;
    }
}