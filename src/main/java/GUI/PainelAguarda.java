package GUI;

import javax.swing.*;
import java.awt.*;


public class PainelAguarda extends JPanel {

    private JanelaPrincipal janelaPrincipal; // O "Palco" que nos controla
    private JLabel labelStatus;

    public PainelAguarda(JanelaPrincipal janelaPrincipal, String ip, String port, String jogo, String equipa, String user) {
        this.janelaPrincipal = janelaPrincipal;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String texto = String.format("<html>A ligar como <b>%s</b> (Equipa: %s)<br>Ao servidor %s:%s...</html>", user, equipa, ip, port);
        labelStatus = new JLabel(texto, SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(labelStatus, BorderLayout.CENTER);

        JButton skipButton = new JButton("Skip (Debug)");
        skipButton.addActionListener(e -> {
            janelaPrincipal.mostrarPainel(JanelaPrincipal.PAINEL_JOGO);
        });

        JPanel southPanel = new JPanel(); // Painel para o botão
        southPanel.add(skipButton);
        this.add(southPanel, BorderLayout.SOUTH);

        // TODO: Criar uma nova Thread
    }
}