package br.java.meu_android_app_sqlite.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.java.meu_android_app_sqlite.banco.CriaBanco;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserirDados(String titulo, String autor, String editora) {
        ContentValues valores;
        long resultado;

        db =  banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";

        else
            return "Registro inserido com sucesso";
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String [] campos = {banco.ID, banco.TITULO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null,  null, null, null, null);

        if (cursor != null ) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }
}
