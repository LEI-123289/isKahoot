package GameState;
import java.util.List;
import java.util.ArrayList;
public class Equipa {
    private final String nome;
    private int pontuacao;
    private final List<Jogador> jogadores;

    public Equipa(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.jogadores = new ArrayList<Jogador>();
    }

    public boolean addJogador(Jogador jogador) {
        if(jogadores.size() >= 2) {
            return false;
        }
        if(jogadores.contains(jogador)) {
            return false;
        }
        jogadores.add(jogador);
        return true;
    }

    public void addPontos(int pontos){
        this.pontuacao += pontos;
    }


    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
