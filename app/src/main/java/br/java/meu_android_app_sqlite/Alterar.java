package br.java.meu_android_app_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.java.meu_android_app_sqlite.banco.CriaBanco;
import br.java.meu_android_app_sqlite.controller.BancoController;

public class Alterar extends AppCompatActivity {

    EditText livro;
    EditText autor;
    EditText editora;

    Button alterar;
    Button deletar;

    Cursor cursor;

    BancoController controller;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        controller = new BancoController(getBaseContext());

        livro = (EditText) findViewById(R.id.editText4);
        autor = (EditText) findViewById(R.id.editText5);
        editora = (EditText) findViewById(R.id.editText6);

        alterar = (Button) findViewById(R.id.button2);
        deletar = (Button) findViewById(R.id.button3);

        cursor = controller.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.alterarRegistro(Integer.parseInt(codigo),
                        livro.getText().toString(),
                        autor.getText().toString(),
                        editora.getText().toString());

                Intent intent = new Intent(Alterar.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.deletarRegistro(Integer.parseInt(codigo));

                Intent intent = new Intent(Alterar.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });
    }
}