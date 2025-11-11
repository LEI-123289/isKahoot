package GUI;

import javax.swing.*;
import java.awt.*;

public class PainelJogo extends JPanel {

    private JanelaPrincipal janelaPrincipal;
    private JLabel labelTituloPergunta;
    private JLabel labelTextoPergunta;
    private JButton botaoA, botaoB, botaoC, botaoD;
    private Color corDefaultBotao;
    private JLabel labelCronometro;
    private javax.swing.Timer timer;
    private int tempoRestante;
    private final static int TEMPO_CONTAGEM = 30;

    private int indiceCorretoAtual;

    public PainelJogo(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelNorte = new JPanel(new BorderLayout());

        labelTituloPergunta = new JLabel("Pergunta:", SwingConstants.CENTER);
        labelTituloPergunta.setFont(new Font("Arial", Font.BOLD, 24));
        painelNorte.add(labelTituloPergunta, BorderLayout.CENTER);

        labelCronometro = new JLabel("Tempo: " + TEMPO_CONTAGEM, SwingConstants.RIGHT);
        labelCronometro.setFont(new Font("Arial", Font.BOLD, 18));
        labelCronometro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        painelNorte.add(labelCronometro, BorderLayout.EAST);

        this.add(painelNorte, BorderLayout.NORTH);

        JPanel painelCaixaPergunta = new JPanel(new GridBagLayout());
        painelCaixaPergunta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        labelTextoPergunta = new JLabel("A carregar pergunta...");
        labelTextoPergunta.setFont(new Font("Arial", Font.PLAIN, 20));
        painelCaixaPergunta.add(labelTextoPergunta);
        this.add(painelCaixaPergunta, BorderLayout.CENTER);

        JPanel painelGrelhaRespostas = new JPanel(new GridLayout(2, 2, 15, 15));
        Dimension tamanhoBotao = new Dimension(300, 80);

        botaoA = new JButton("A");
        botaoB = new JButton("B");
        botaoC = new JButton("C");
        botaoD = new JButton("D");

        JButton[] botoes = {botaoA, botaoB, botaoC, botaoD};
        for (JButton btn : botoes) {
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setPreferredSize(tamanhoBotao);
            btn.setOpaque(true);
            painelGrelhaRespostas.add(btn);
        }

        this.corDefaultBotao = botaoA.getBackground();

        JPanel painelContentorSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelContentorSul.add(painelGrelhaRespostas);
        this.add(painelContentorSul, BorderLayout.SOUTH);

        botaoA.addActionListener(e -> onRespostaClicada(0, botaoA));
        botaoB.addActionListener(e -> onRespostaClicada(1, botaoB));
        botaoC.addActionListener(e -> onRespostaClicada(2, botaoC));
        botaoD.addActionListener(e -> onRespostaClicada(3, botaoD));

        timer = new javax.swing.Timer(1000, e -> onTickTimer());
        timer.setRepeats(true);
    }

    private void onTickTimer() {
        tempoRestante--;
        labelCronometro.setText("Tempo: " + tempoRestante);

        if (tempoRestante <= 5) {
            labelCronometro.setForeground(Color.RED);
        }

        if (tempoRestante <= 0) {
            System.out.println("Tempo esgotado!");
            avancarParaProximaPergunta(null, null);
        }
    }

    private void onRespostaClicada(int indiceClicado, JButton botaoClicado) {

        boolean estaCerto = (indiceClicado == this.indiceCorretoAtual);
        String feedback = estaCerto ? "(Certo)" : "(Errado)";

        char opcaoChar = (char) ('A' + indiceClicado);

        System.out.println("Respondeu: " + opcaoChar + " " + feedback);

        avancarParaProximaPergunta(String.valueOf(opcaoChar), botaoClicado);
    }

    private void avancarParaProximaPergunta(String opcao, JButton botaoClicado) {
        timer.stop();
        desativarBotoes();

        if (botaoClicado != null) {
            botaoClicado.setBackground(new Color(255, 165, 0));
        } else {
            labelCronometro.setText("Tempo: 0");
            labelCronometro.setForeground(Color.RED);
        }

        Timer proximaPerguntaTimer = new Timer(1500, e -> {
            janelaPrincipal.mostrarProximaPergunta();
        });
        proximaPerguntaTimer.setRepeats(false);
        proximaPerguntaTimer.start();
    }

    public void carregarPergunta(String titulo, String texto, String[] opcoes, int indiceCorreto) {
        labelTituloPergunta.setText(titulo);
        labelTextoPergunta.setText("<html><center>" + texto + "</center></html>");

        botaoA.setText("A: " + (opcoes.length > 0 ? opcoes[0] : ""));
        botaoB.setText("B: " + (opcoes.length > 1 ? opcoes[1] : ""));
        botaoC.setText("C: " + (opcoes.length > 2 ? opcoes[2] : ""));
        botaoD.setText("D: " + (opcoes.length > 3 ? opcoes[3] : ""));

        this.indiceCorretoAtual = indiceCorreto;

        ativarBotoes();

        tempoRestante = TEMPO_CONTAGEM;
        labelCronometro.setText("Tempo: " + tempoRestante);
        labelCronometro.setForeground(Color.BLACK);
        timer.start();
    }

    private void desativarBotoes() {
        botaoA.setEnabled(false);
        botaoB.setEnabled(false);
        botaoC.setEnabled(false);
        botaoD.setEnabled(false);
    }

    private void ativarBotoes() {
        botaoA.setEnabled(true);
        botaoB.setEnabled(true);
        botaoC.setEnabled(true);
        botaoD.setEnabled(true);

        botaoA.setBackground(corDefaultBotao);
        botaoB.setBackground(corDefaultBotao);
        botaoC.setBackground(corDefaultBotao);
        botaoD.setBackground(corDefaultBotao);
    }
}