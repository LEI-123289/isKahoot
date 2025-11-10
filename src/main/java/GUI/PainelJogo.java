package GUI;

import javax.swing.*;
import java.awt.*;

public class PainelJogo extends JPanel {

    private JanelaPrincipal janelaPrincipal;

    private JLabel labelTituloPergunta;
    private JLabel labelTextoPergunta;
    private JButton botaoA, botaoB, botaoC, botaoD;

    private Color corDefaultBotao;

    public PainelJogo(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        labelTituloPergunta = new JLabel("Pergunta 1:", SwingConstants.CENTER);
        labelTituloPergunta.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(labelTituloPergunta, BorderLayout.NORTH);

        JPanel painelCaixaPergunta = new JPanel(new GridBagLayout());
        painelCaixaPergunta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        painelCaixaPergunta.setBackground(Color.CYAN);
        labelTextoPergunta = new JLabel("Quantos dias tem um ano?");
        labelTextoPergunta.setFont(new Font("Arial", Font.PLAIN, 20));
        painelCaixaPergunta.add(labelTextoPergunta);
        this.add(painelCaixaPergunta, BorderLayout.CENTER);


        JPanel painelGrelhaRespostas = new JPanel(new GridLayout(2, 2, 15, 15));
        Dimension tamanhoBotao = new Dimension(300, 80);

        botaoA = new JButton("A: 135");
        botaoA.setFont(new Font("Arial", Font.BOLD, 18));
        botaoA.setPreferredSize(tamanhoBotao);

        botaoB = new JButton("B: 280");
        botaoB.setFont(new Font("Arial", Font.BOLD, 18));
        botaoB.setPreferredSize(tamanhoBotao);

        botaoC = new JButton("C: 365");
        botaoC.setFont(new Font("Arial", Font.BOLD, 18));
        botaoC.setPreferredSize(tamanhoBotao);

        botaoD = new JButton("D: 369");
        botaoD.setFont(new Font("Arial", Font.BOLD, 18));
        botaoD.setPreferredSize(tamanhoBotao);


        botaoA.setOpaque(true);
        botaoB.setOpaque(true);
        botaoC.setOpaque(true);
        botaoD.setOpaque(true);

        // --- MUDANÇA 3: Guardar a cor original ---
        this.corDefaultBotao = botaoA.getBackground();

        painelGrelhaRespostas.add(botaoA);
        painelGrelhaRespostas.add(botaoB);
        painelGrelhaRespostas.add(botaoC);
        painelGrelhaRespostas.add(botaoD);

        JPanel painelContentorSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelContentorSul.add(painelGrelhaRespostas);
        this.add(painelContentorSul, BorderLayout.SOUTH);

        // --- MUDANÇA 4: As ações dos botões ---
        botaoA.addActionListener(e -> onRespostaClicada("A", botaoA));
        botaoB.addActionListener(e -> onRespostaClicada("B", botaoB));
        botaoC.addActionListener(e -> onRespostaClicada("C", botaoC));
        botaoD.addActionListener(e -> onRespostaClicada("D", botaoD));
    }


    private void onRespostaClicada(String opcao, JButton botaoClicado) {
        System.out.println("Respondeu: " + opcao);

        // 1. Desativa TODOS os botões
        desativarBotoes();

        // 2. Pinta o botão selecionado de uma cor
        // (Laranja é uma boa cor neutra para "selecionado")
        botaoClicado.setBackground(new Color(0, 165, 197));

        // TODO: Enviar resposta ao servidor
    }

    public void carregarPergunta(String titulo, String texto, String[] opcoes) {
        labelTituloPergunta.setText(titulo);
        labelTextoPergunta.setText("<html><center>" + texto + "</center></html>");

        botaoA.setText("A: " + opcoes[0]);
        botaoB.setText("B: " + opcoes[1]);
        botaoC.setText("C: " + opcoes[2]);
        botaoD.setText("D: " + opcoes[3]);

        // Re-ativa os botões para a nova pergunta
        ativarBotoes();
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

        // --- MUDANÇA 5: Repor a cor original ---
        botaoA.setBackground(corDefaultBotao);
        botaoB.setBackground(corDefaultBotao);
        botaoC.setBackground(corDefaultBotao);
        botaoD.setBackground(corDefaultBotao);
    }
}