package com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alura.agenda.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends SQLiteOpenHelper{

    public PacienteDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Pacientes ( id_paciente INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, endereco TEXTO," +
                " telefone TEXTO, site TEXTO,nota REAL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Pacientes";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void inserir(Paciente paciente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", paciente.getNome());
        dados.put("endereco", paciente.getEndereco());
        dados.put("telefone", paciente.getTelefone());
        dados.put("site", paciente.getSite());
        dados.put("nota", paciente.getNota());

        db.insert("Pacientes", null, dados);
    }

    public List<Paciente> buscar() {
        String sql = "SELECT * FROM Pacientes;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor ponteiro = db.rawQuery(sql, null);
        List<Paciente> pacientes = new ArrayList<>();

        while (ponteiro.moveToNext()){
            Paciente paciente = new Paciente();

           paciente.setId(ponteiro.getLong(ponteiro.getColumnIndex("id_paciente")));
           paciente.setNome(ponteiro.getString(ponteiro.getColumnIndex("nome")));
           paciente.setEndereco(ponteiro.getString(ponteiro.getColumnIndex("endereco")));
           paciente.setTelefone(ponteiro.getString(ponteiro.getColumnIndex("telefone")));
           paciente.setSite(ponteiro.getString(ponteiro.getColumnIndex("site")));
           paciente.setNota(ponteiro.getDouble(ponteiro.getColumnIndex("nota")));

           pacientes.add(paciente);
       }

       ponteiro.close();
       return pacientes;
    }

    public void deletar(Paciente paciente) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {paciente.getId().toString()};
        db.delete("Pacientes", "id_paciente = ?", params);

    }
}

