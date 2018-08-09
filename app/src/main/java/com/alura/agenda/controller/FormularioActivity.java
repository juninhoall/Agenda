package com.alura.agenda.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.alura.agenda.helpers.FormularioHelper;
import com.alura.agenda.R;
import com.alura.agenda.dao.PacienteDAO;
import com.alura.agenda.model.Paciente;

public class FormularioActivity extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Button botaoSalvar = (Button) findViewById(R.id.menu_formulario_ok);
         helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Paciente aluno = helper.getAluno();
                PacienteDAO dao = new PacienteDAO(this);

                dao.inserir(aluno);
                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno Salvo " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                finish();
             break;
        }

        return super.onOptionsItemSelected(item);
    }
}
