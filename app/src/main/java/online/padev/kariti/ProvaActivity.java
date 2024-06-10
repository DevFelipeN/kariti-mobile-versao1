package online.padev.kariti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import online.padev.kariti.R;

public class ProvaActivity extends AppCompatActivity {
    ImageButton voltar, iconHelpProva;
    Button cadProva, gerarCartao, corrigirProva, visuProva;
    ArrayList<String> listeTurmas;
    BancoDados bancoDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);

        TextView textView = findViewById(R.id.textViewConcluido); // Seu TextView
        bancoDados = new BancoDados(this);

        // Verificar se há uma mensagem extra no Intent
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("mensagem")) {
            String mensagem = intent.getStringExtra("mensagem");
            textView.setText(mensagem); // Define o texto do TextView com a mensagem
            textView.setVisibility(View.VISIBLE); // Exibe o TextView
        } else {
            textView.setVisibility(View.GONE); // Oculta o TextView se não houver mensagem
        }


        voltar = findViewById(R.id.imgBtnVoltaDescola);
        iconHelpProva = findViewById(R.id.iconHelp);
        cadProva = findViewById(R.id.buttonCadProva);
        gerarCartao = findViewById(R.id.buttonGerarCatao);
        corrigirProva = findViewById(R.id.buttonCorrigirProva);
        visuProva = findViewById(R.id.buttonVisuProva);

        iconHelpProva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelpDetalhes();
            }
        });
        cadProva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastroProva();
            }
        });
        gerarCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaGerarCartao();
            }
        });
        corrigirProva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCorrigirProva();
            }
        });
        visuProva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaVisuProva();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void telaCadastroProva(){
        if(listTurma().equals(false)) {
            Intent intent = new Intent(this, CadProvaActivity.class);
            startActivity(intent);
        }else aviso();
    }
    public void telaGerarCartao(){
        Intent intent = new Intent(this, ProvaCartoesActivity.class);
        intent.putExtra("endereco", 02);
        startActivity(intent);
    }
    public void telaCorrigirProva(){
        Intent intent = new Intent(this, ProvaCorrigirActivity.class);
        startActivity(intent);
    }
    public void telaVisuProva(){
        Intent intent = new Intent(this, VisualProvaActivity.class);
        startActivity(intent);
    }
    public Boolean listTurma(){
        listeTurmas = (ArrayList<String>) bancoDados.obterNomeTurmas();
        if(listeTurmas.isEmpty()){
            return true;
        }else return false;
    }
    public void aviso(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!");
        builder.setMessage("Não existem turmas cadastradas. " +
                "Para realizar o cadastro de provas, realize o cadastro das turma em que atua e/ou que será aplicada a prova.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void dialogHelpDetalhes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajuda");
        builder.setMessage("Tela principal de prova.\n\n" +
                "> Cadastrar Prova - Selecionando essa opção, será derecionado a tela que solicita as informações necessárias para elaboração da prova e, em seguida solicita o preenchimento do gabarito.\n\n" +
                "> Gerar Cartões - Nesta opção podera realizar o download dos cartões resposta de uma prova já cadastrada na opção anterior.\n\n" +
                "> Corrigir Prova - Após seleciodada essa opção, basta realizar os passos sugeriodos pelo KARITI, iniciar correção clicando no botão 'Sacannear Cartão', capturar o QrCode da prova e capturar a imagem do cartão resposta, em seguida são listadas as provas capurada na proxima tela juntamente com duas opções continuar capturando mais provas ou finalizar a correção.\n\n" +
                "> Visualizar Prova - Nesta opção pode ser visualizado o resultado da correção das provas informando a quantidade de acertos e nota de cada aluno.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}