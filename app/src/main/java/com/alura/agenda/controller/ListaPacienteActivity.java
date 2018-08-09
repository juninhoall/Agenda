package com.alura.agenda.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alura.agenda.R;
import com.alura.agenda.dao.PacienteDAO;
import com.alura.agenda.model.Paciente;

import java.util.List;

public class ListaPacienteActivity extends Activity {

    private ListView listaPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaPacientes = findViewById(R.id.listaAlunos);

        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item_lista, int position, long id) {
                Paciente paciente = (Paciente) listaPacientes.getItemAtPosition(position);
                Toast.makeText(ListaPacienteActivity.this, "Paciente " + paciente.getNome() , Toast.LENGTH_SHORT).show();

            }
        });

        Button novoAluno = findViewById(R.id.lista_novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaPacienteActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaPacientes);
    }

    private void carregarLista() {
        PacienteDAO alunoDAO = new PacienteDAO(this);

        List<Paciente> alunos = alunoDAO.buscar();

        listaPacientes = findViewById(R.id.listaAlunos);

        ArrayAdapter<Paciente> adapterAlunos =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        listaPacientes.setAdapter(adapterAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
      MenuItem deletar =   menu.add("Deletar"); // outra opção
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Paciente paciente = (Paciente) listaPacientes.getItemAtPosition(info.position);

                PacienteDAO pacienteDAO = new PacienteDAO(ListaPacienteActivity.this);
                pacienteDAO.deletar(paciente);
                pacienteDAO.close();

                carregarLista();

                return false;
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
        // Fazer com esse metodo depois como reforço, criar xml e pegar o id doc ara
    }
}
