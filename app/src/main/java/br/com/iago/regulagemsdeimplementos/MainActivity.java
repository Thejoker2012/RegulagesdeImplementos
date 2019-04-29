package br.com.iago.regulagemsdeimplementos;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

import br.com.iago.regulagesdeimplementos.R;

public class MainActivity extends AppCompatActivity {

    TextView  tituloTextView,textViewResultadoTotal,textViewResultadoTotal2,textViewResultadoTotal3, textViewResultadoTotal4;
    EditText   doseEditText,  velocidadeEditText, larguraEditText;
    Button calcularButton, limparButton;
    ToggleButton selecionarToggleButton;

    //Strings de Texto
    String texto1 = "Digite o valor da Dose em kg/ha!";
    String texto2 = "Digite a velocidade!";
    String texto3 = "Digite a largura do equipamento!";
    int duracao = Toast.LENGTH_LONG;

    //Strings de Textos dos resultados
    String textoResultado1 = "1 - Para uma melhor precisão marque 50 metros e tire o tempo que a maquina irá gastar para percorrer o mesmo.";
    String textoResultado2 = "2 - Caso não saiba o tamanho da faixa de aplicação vá até o campo com um pouco de produto dentro do tanque e faça o teste. Sempre utilize a rotação que irá trabalhar.";
    String textoResultado3 = "3 - Após realisar os passos 1 e 2 preencha os campos acima com os valores obtidos e com a dose que irá utilizar.";
    String textoResultado4 = "4 - O sistema já faz a conversão automaticamente de km/h para m/s.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bloqueia a activity atual para não rotacionar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Formatando resultado com duas cadas após a virgula.
        final DecimalFormat df = new DecimalFormat("0.00");

        //Mapeando os TextViews
        tituloTextView = (TextView) findViewById(R.id.tituloTextView);
        textViewResultadoTotal = (TextView) findViewById(R.id.textViewResultadoTotal);
        textViewResultadoTotal2 = (TextView) findViewById(R.id.textViewResultadoTotal2);
        textViewResultadoTotal3 = (TextView) findViewById(R.id.textViewResultadoTotal3);
        textViewResultadoTotal4 = (TextView) findViewById(R.id.textViewResultadoTotal4);

        //Mapeando os EditText
        doseEditText = (EditText) findViewById(R.id.doseEditText);
        velocidadeEditText = (EditText) findViewById(R.id.velocidadeEditText);
        larguraEditText = (EditText) findViewById(R.id.larguraEditText);

        //Mapeando os Buttons
        calcularButton = (Button) findViewById(R.id.calcularButton);
        limparButton = (Button) findViewById(R.id.limparButton);

        //Mapeando os ToggleButtons
        selecionarToggleButton = (ToggleButton) findViewById(R.id.selecionarToggleButton);

        //Botão Calcular
        calcularButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if( doseEditText.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,texto1,duracao);
                    }
                    else if(velocidadeEditText.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,texto2,duracao);
                    }
                    else if(larguraEditText.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,texto3,duracao);
                    }
                    else{//Passando os valores
                        Double dose = Double.parseDouble(doseEditText.getText().toString());
                        Double velocidade = Double.parseDouble(velocidadeEditText.getText().toString());
                        Double largura = Double.parseDouble(larguraEditText.getText().toString());

                        if(selecionarToggleButton.isChecked()){
                            velocidade = velocidade / 3.6;
                        }
                        else{
                            velocidade = velocidade ;
                        }

                        //Calculando a quantidade de produto a ser coletada em Kg/s
                        Double resultado = (dose * velocidade * largura) / 10000;

                        //Passando os valores da soma para o campo resultado no layout
                        textViewResultadoTotal.setText("O resultado total e de " + String.valueOf(df.format(resultado)) + "kg/s");
                        textViewResultadoTotal2.setText("Em 15 segundos vai obter " + String.valueOf(df.format(resultado * 15)) + " kg/15s");
                        textViewResultadoTotal3.setText("Em 30 segundos vai obter " + String.valueOf(df.format(resultado * 30)) + " kg/30s");
                        textViewResultadoTotal4.setText("Em 30 segundos vai obter " + String.valueOf(df.format(resultado * 60)) + " kg/60s");
                    }
                }
        });
        //Botão para limpar os campos
        limparButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if( doseEditText.getText().toString().isEmpty() ||
                        velocidadeEditText.getText().toString().isEmpty()||
                        larguraEditText.getText().toString().isEmpty()){

                        textViewResultadoTotal.setText(textoResultado1);
                        textViewResultadoTotal2.setText(textoResultado2);
                        textViewResultadoTotal3.setText(textoResultado3);
                        textViewResultadoTotal4.setText(textoResultado4);
                    }
                    else{
                        //Passando os valores da soma para o campo resultado no layout
                        doseEditText.setText("");
                        velocidadeEditText.setText("");
                        larguraEditText.setText("");

                        //Passando os valores da soma para o campo resultado no layout
                        textViewResultadoTotal.setText(textoResultado1);
                        textViewResultadoTotal2.setText(textoResultado2);
                        textViewResultadoTotal3.setText(textoResultado3);
                        textViewResultadoTotal4.setText(textoResultado4);

                    }
                }
        });
    }



}
