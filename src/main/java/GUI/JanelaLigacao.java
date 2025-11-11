package GUI;
import javax.swing.*;
import java.awt.*;

public class JanelaLigacao {
    private JFrame frame;
    private JTextField ipField, portField, jogoField, equipaField, userField;
    private JButton botaoLigar;

    public JanelaLigacao() {
        frame = new JFrame("isKahoot - Ligar ao Jogo!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel painelLogin = new JPanel(new GridLayout(5, 2, 10, 10));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelLogin.add(new JLabel("IP:"));
        ipField = new JTextField("LocalHost");
        painelLogin.add(ipField);

        painelLogin.add(new JLabel("Port:"));
        portField = new JTextField("12345");
        painelLogin.add(portField);

        painelLogin.add(new JLabel("Jogo:"));
        jogoField = new JTextField("1");
        painelLogin.add(jogoField);

        painelLogin.add(new JLabel("Equipa:"));
        equipaField = new JTextField("A");
        painelLogin.add(equipaField);

        painelLogin.add(new JLabel("User:"));
        userField = new JTextField("O meu nome");
        painelLogin.add(userField);

        JPanel painelBotao = new JPanel(new FlowLayout());
        botaoLigar = new JButton("Ligar");
        painelBotao.add(botaoLigar);
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        frame.add(painelLogin, BorderLayout.CENTER);
        frame.add(painelBotao, BorderLayout.SOUTH);

        botaoLigar.addActionListener(e -> onLigarClicado());
    }

    private void onLigarClicado() {
        String ip = ipField.getText();
        String port = portField.getText();
        String jogo = jogoField.getText();
        String equipa = equipaField.getText();
        String user = userField.getText();

        // TODO: Adicionar validação (verificar se os campos não estão vazios)

        JanelaPrincipal janelaPrincipal = new JanelaPrincipal(ip, port, jogo, equipa, user);
        janelaPrincipal.mostrar();

        frame.dispose();
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}