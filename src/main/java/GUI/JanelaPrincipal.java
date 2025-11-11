package GUI;

import javax.swing.*;
import java.awt.*;
import LeituraPerguntas.CarregadorPerguntas;
import LeituraPerguntas.Pergunta;
import java.util.List;

public class JanelaPrincipal {

    public final static String PAINEL_AGUARDA = "Ecrã de Espera";
    public final static String PAINEL_JOGO = "Ecrã de Jogo";

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel painelContentor;

    private PainelAguarda painelAguarda;
    private PainelJogo painelJogo;
    private List<Pergunta> listaDePerguntas;
    private int indicePerguntaAtual = 0;

    public JanelaPrincipal(String ip, String port, String jogo, String equipa, String user) {
        frame = new JFrame("isKahoot - Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelContentor = new JPanel(cardLayout);

        painelAguarda = new PainelAguarda(this, ip, port, jogo, equipa, user);
        painelJogo = new PainelJogo(this);

        CarregadorPerguntas carregador = new CarregadorPerguntas();
        this.listaDePerguntas = carregador.carregarPerguntas("src/main/resources/perguntas.json");
        if (this.listaDePerguntas == null || this.listaDePerguntas.isEmpty()) {
            System.err.println("JANELA PRINCIPAL: Não foi possível carregar perguntas!");
        }

        painelContentor.add(painelAguarda, PAINEL_AGUARDA);
        painelContentor.add(painelJogo, PAINEL_JOGO);

        frame.add(painelContentor);

        mostrarPainel(PAINEL_AGUARDA);
    }

    public void mostrarPainel(String nomeDoPainel) {
        cardLayout.show(painelContentor, nomeDoPainel);
    }

    public void mostrarProximaPergunta() {
        if (listaDePerguntas == null || listaDePerguntas.isEmpty()) {
            System.err.println("ERRO: Nenhuma pergunta carregada. O jogo não pode continuar.");
            painelJogo.carregarPergunta("Erro", "Não foi possível carregar as perguntas.", new String[4], 0);
            mostrarPainel(PAINEL_JOGO);
            return;
        }

        if (indicePerguntaAtual >= listaDePerguntas.size()) {
            System.out.println("FIM DO JOGO (simulação): Acabaram as perguntas.");
            mostrarPainel(PAINEL_AGUARDA);
            indicePerguntaAtual = 0;
            return;
        }

        Pergunta p = listaDePerguntas.get(indicePerguntaAtual);

        String titulo = "Pergunta " + (indicePerguntaAtual + 1);
        String texto = p.getQuestion();
        String[] opcoes = p.getOptions().toArray(new String[0]);

        int indiceCorreto = p.getCorrectOptionIndex();

        painelJogo.carregarPergunta(titulo, texto, opcoes, indiceCorreto);

        mostrarPainel(PAINEL_JOGO);

        indicePerguntaAtual++;
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}