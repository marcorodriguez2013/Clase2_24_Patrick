package com.example.clase2_24_patrick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText txt_dni;
    Button btn_buscar;
    TextView txt_mostrar;
    String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_dni = findViewById(R.id.txt_dni);
        btn_buscar = findViewById(R.id.btn_buscar);
        txt_mostrar = findViewById(R.id.txt_mostrar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_mostrar.setText(ExtraerDNI(txt_dni.getText().toString()));
            }
        });
    }
    public String ExtraerDNI(String dni){
        try{
            URL url = new URL("http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI="+dni);
            URLConnection con = null;
            con = url.openConnection();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),"ISO-8859-1"));
            StringBuilder sb = new StringBuilder();
            try{
                String s;
                while ((s = r.readLine())!=null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                r.close();
            }
             resultado = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
