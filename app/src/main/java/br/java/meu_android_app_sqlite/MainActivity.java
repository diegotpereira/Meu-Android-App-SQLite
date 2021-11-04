package br.java.meu_android_app_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.java.meu_android_app_sqlite.controller.BancoController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button) findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController controller = new BancoController(getBaseContext());
                EditText titulo = (EditText) findViewById(R.id.editText);
                EditText autor = (EditText) findViewById(R.id.editText2);
                EditText editora = (EditText) findViewById(R.id.editText3);

                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();
                String resultado;

                resultado = controller.inserirDados(tituloString, autorString, editoraString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this,Consulta.class);
                startActivity(i);
            }
        });
    }
}