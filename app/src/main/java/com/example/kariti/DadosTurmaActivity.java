package com.example.kariti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DadosTurmaActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    ImageButton voltar;
    ImageView menuPnt;
    TextView turmaCad;
    ArrayList<String> listAlTurma;
    BancoDados bancoDados;
    ListView listView;
    ArrayList<String> listAlunosDturma;
    String id_turma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_turma);

        voltar = findViewById(R.id.imgBtnVoltar);
        menuPnt = findViewById(R.id.menu_icon);
        listView = findViewById(R.id.listViewAlunosTurma);
        turmaCad = findViewById(R.id.textViewTurmaCad);
        bancoDados = new BancoDados(this);

        id_turma = String.valueOf(getIntent().getExtras().getInt("idTurma"));
        String pegaTurma = bancoDados.pegaNomeTurma(id_turma);
        turmaCad.setText(pegaTurma);

        listAlunosDturma = (ArrayList<String>) bancoDados.listAlunosDturma(id_turma);
        EscolaAdapter adapter = new EscolaAdapter(this, listAlunosDturma, listAlunosDturma);
        listView.setAdapter(adapter);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void PopMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.actuvity_menuturma);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuEditar) {
            telaEditar();
            Toast.makeText(DadosTurmaActivity.this, "Editar Turma selecionada", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuExcluir) {
            Toast.makeText(DadosTurmaActivity.this, "Excluir Turma selecionado", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.menuExclTurmAlun) {
            Toast.makeText(DadosTurmaActivity.this, "Excluir Turma e Aluno selecionado", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    public void telaEditar(){
        Intent intent = new Intent(this, EditarTurmaActivity.class);
        intent.putExtra("id_turma", id_turma);
        startActivity(intent);
    }
}