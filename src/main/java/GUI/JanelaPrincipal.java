package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * O "Palco" (JFrame) que gere todos os "Cenários" (JPanels) do jogo.
 * Usa um CardLayout para trocar entre o PainelAguarda e o PainelJogo.
 */
public class JanelaPrincipal {

    public final static String PAINEL_AGUARDA = "Ecrã de Espera";
    public final static String PAINEL_JOGO = "Ecrã de Jogo";

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel painelContentor; // O painel que contém os "cartões"

    private PainelAguarda painelAguarda;
    private PainelJogo painelJogo;

    public JanelaPrincipal(String ip, String port, String jogo, String equipa, String user) {
        frame = new JFrame("isKahoot - Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Janela de jogo maior
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelContentor = new JPanel(cardLayout);

        painelAguarda = new PainelAguarda(this, ip, port, jogo, equipa, user);
        painelJogo = new PainelJogo(this);

        painelContentor.add(painelAguarda, PAINEL_AGUARDA);
        painelContentor.add(painelJogo, PAINEL_JOGO);

        frame.add(painelContentor);

        mostrarPainel(PAINEL_AGUARDA);
    }

    /**
     * Método público que os painéis chamam para trocar o ecrã visível.
     */
    public void mostrarPainel(String nomeDoPainel) {
        cardLayout.show(painelContentor, nomeDoPainel);
    }

    /**
     * Torna a janela visível.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}