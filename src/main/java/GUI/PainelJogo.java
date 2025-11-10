package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * O "Cenário" (JPanel) que mostra a Pergunta e as Respostas.
 * (Versão com cronómetro decrescente)
 */
public class PainelJogo extends JPanel {

    private JanelaPrincipal janelaPrincipal;

    // Componentes da GUI
    private JLabel labelTituloPergunta;
    private JLabel labelTextoPergunta;
    private JButton botaoA, botaoB, botaoC, botaoD;
    private Color corDefaultBotao;

    // --- MUDANÇA 1: Componentes do Cronómetro ---
    private JLabel labelCronometro;
    private javax.swing.Timer timer; // Importante usar o timer do javax.swing
    private int tempoRestante;
    private final static int TEMPO_CONTAGEM = 30; // Tempo em segundos

    public PainelJogo(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- MUDANÇA 2: Criar e posicionar o Cronómetro ---
        // Vamos criar um painel para o Norte que vai ter o Título e o Cronómetro
        JPanel painelNorte = new JPanel(new BorderLayout());

        labelTituloPergunta = new JLabel("Pergunta 1:", SwingConstants.CENTER);
        labelTituloPergunta.setFont(new Font("Arial", Font.BOLD, 24));
        painelNorte.add(labelTituloPergunta, BorderLayout.CENTER);

        // Cria o label do cronómetro
        labelCronometro = new JLabel("Tempo: " + TEMPO_CONTAGEM, SwingConstants.RIGHT);
        labelCronometro.setFont(new Font("Arial", Font.BOLD, 18));
        // Adiciona um pouco de padding à direita
        labelCronometro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        painelNorte.add(labelCronometro, BorderLayout.EAST);

        // Adiciona o painelNorte (que contém o título E o cronómetro) ao NORTE
        this.add(painelNorte, BorderLayout.NORTH);

        // --- Painel CENTRO (A Caixa da Pergunta) ---
        // (O teu código existente está perfeito)
        JPanel painelCaixaPergunta = new JPanel(new GridBagLayout());
        painelCaixaPergunta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        labelTextoPergunta = new JLabel("Quantos dias tem um ano?");
        labelTextoPergunta.setFont(new Font("Arial", Font.PLAIN, 20));
        painelCaixaPergunta.add(labelTextoPergunta);
        this.add(painelCaixaPergunta, BorderLayout.CENTER);

        // --- Painel SUL (Os Botões de Resposta) ---
        // (O teu código existente está perfeito)
        JPanel painelGrelhaRespostas = new JPanel(new GridLayout(2, 2, 15, 15));
        Dimension tamanhoBotao = new Dimension(300, 80);
        // ... (criação dos 4 botões e adição à grelha) ...
        botaoA = new JButton("A: 135");
        botaoB = new JButton("B: 280");
        botaoC = new JButton("C: 365");
        botaoD = new JButton("D: 369");

        botaoA.setFont(new Font("Arial", Font.BOLD, 18));
        botaoB.setFont(new Font("Arial", Font.BOLD, 18));
        botaoC.setFont(new Font("Arial", Font.BOLD, 18));
        botaoD.setFont(new Font("Arial", Font.BOLD, 18));

        botaoA.setPreferredSize(tamanhoBotao);
        botaoB.setPreferredSize(tamanhoBotao);
        botaoC.setPreferredSize(tamanhoBotao);
        botaoD.setPreferredSize(tamanhoBotao);

        botaoA.setOpaque(true);
        botaoB.setOpaque(true);
        botaoC.setOpaque(true);
        botaoD.setOpaque(true);
        this.corDefaultBotao = botaoA.getBackground();

        painelGrelhaRespostas.add(botaoA);
        painelGrelhaRespostas.add(botaoB);
        painelGrelhaRespostas.add(botaoC);
        painelGrelhaRespostas.add(botaoD);

        JPanel painelContentorSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelContentorSul.add(painelGrelhaRespostas);
        this.add(painelContentorSul, BorderLayout.SOUTH);

        // --- Ações dos Botões ---
        botaoA.addActionListener(e -> onRespostaClicada("A", botaoA));
        botaoB.addActionListener(e -> onRespostaClicada("B", botaoB));
        botaoC.addActionListener(e -> onRespostaClicada("C", botaoC));
        botaoD.addActionListener(e -> onRespostaClicada("D", botaoD));

        // --- MUDANÇA 3: Inicializar o Timer ---
        // O Timer vai disparar um evento a cada 1000ms (1 segundo)
        // O "e -> onTickTimer()" diz o que fazer a cada segundo
        timer = new javax.swing.Timer(1000, e -> onTickTimer());
        timer.setRepeats(true); // Queremos que ele continue a disparar
    }

    /**
     * Este método é chamado a cada segundo pelo nosso Timer.
     */
    private void onTickTimer() {
        tempoRestante--; // Decrementa o tempo
        labelCronometro.setText("Tempo: " + tempoRestante);

        // Mudar a cor para vermelho nos últimos 5 segundos
        if (tempoRestante <= 5) {
            labelCronometro.setForeground(Color.RED);
        }

        // Se o tempo acabar
        if (tempoRestante <= 0) {
            timer.stop(); // Para o cronómetro
            System.out.println("Tempo esgotado!");

            // Chama a lógica de resposta, mas sem nenhum botão (null)
            // para indicar que o tempo acabou
            onRespostaClicada(null, null);
        }
    }

    /**
     * Chamado quando um botão de resposta é clicado OU o tempo acaba.
     */
    private void onRespostaClicada(String opcao, JButton botaoClicado) {

        // --- MUDANÇA 4: Parar o cronómetro ---
        timer.stop();

        // 1. Desativa TODOS os botões
        desativarBotoes();

        if (botaoClicado != null) {
            // Se foi um clique de botão
            System.out.println("Respondeu: " + opcao);
            botaoClicado.setBackground(new Color(255, 165, 0)); // Laranja
        } else {
            // Se o tempo acabou (botaoClicado é null)
            System.out.println("O tempo acabou. Nenhuma resposta enviada.");
            labelCronometro.setText("Tempo: 0");
        }

        // TODO: Enviar resposta ao servidor

        // Simulação: Espera 1 segundo e avança para a próxima pergunta
        Timer proximaPerguntaTimer = new Timer(1000, e -> {
           // janelaPrincipal.mostrarProximaPergunta();
        });
        proximaPerguntaTimer.setRepeats(false);
        proximaPerguntaTimer.start();
    }

    /**
     * Método para atualizar a GUI com uma nova pergunta vinda do servidor
     */
    public void carregarPergunta(String titulo, String texto, String[] opcoes) {
        labelTituloPergunta.setText(titulo);
        labelTextoPergunta.setText("<html><center>" + texto + "</center></html>");

        botaoA.setText("A: " + opcoes[0]);
        botaoB.setText("B: " + opcoes[1]);
        botaoC.setText("C: " + opcoes[2]);
        botaoD.setText("D: " + opcoes[3]);

        // Re-ativa os botões para a nova pergunta
        ativarBotoes();

        // --- MUDANÇA 5: Iniciar o Cronómetro ---
        tempoRestante = TEMPO_CONTAGEM;
        labelCronometro.setText("Tempo: " + tempoRestante);
        labelCronometro.setForeground(Color.BLACK); // Repor a cor
        timer.start(); // Inicia a contagem!
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