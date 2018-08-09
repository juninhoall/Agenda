package com.alura.agenda.helpers;

import android.widget.EditText;
import android.widget.RatingBar;

import com.alura.agenda.R;
import com.alura.agenda.controller.FormularioActivity;
import com.alura.agenda.model.Paciente;

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;

    // Referencia da activity, para poder chamar os ids
    public FormularioHelper(FormularioActivity activity){ // Construtor
         campoNome =  activity.findViewById(R.id.formulario_nome);
         campoEndereco = activity.findViewById(R.id.formulario_endereco);
         campoTelefone =  activity.findViewById(R.id.formulario_telefone);
         campoSite = activity.findViewById(R.id.formulario_site);
         campoNota = activity.findViewById(R.id.formulario_nota);
    }

    public Paciente getAluno() {
        Paciente aluno = new Paciente();
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return aluno;
    }
}
