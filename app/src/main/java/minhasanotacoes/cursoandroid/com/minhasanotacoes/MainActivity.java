package minhasanotacoes.cursoandroid.com.minhasanotacoes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private ImageView btnSalvar;
    private EditText lblTexto;
    private static final String ARQUIVO_PREFERENCIA = "anotacoes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = (ImageView) findViewById(R.id.btnSalvar);
        lblTexto = (EditText) findViewById(R.id.lblTexto);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String anotacao = lblTexto.getText().toString();
                gravarNoArquivo(anotacao);

                Toast.makeText(getApplicationContext(), "Texto Salvo", Toast.LENGTH_LONG).show();

            }
        });

        //Recuperar arquivo
        if(lerArquivo() != null){
            lblTexto.setText(lerArquivo());
        }

    }

    private void gravarNoArquivo(String texto){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ARQUIVO_PREFERENCIA, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();
        } catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo(){
        String resultado = "";
        try{
            //Abrir Arquivo
            InputStream arquivo = openFileInput(ARQUIVO_PREFERENCIA);
            if(arquivo != null){
                //ler o arquivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                //Gerar buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //Recupeerar textos do arquivo
                String linhaArquivo = "";
                while ((linhaArquivo = bufferedReader.readLine()) != null){
                    resultado += linhaArquivo;
                }

                arquivo.close();

            }
        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }

        return resultado;
    }

}
