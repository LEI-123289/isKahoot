package GameState;

import LeituraPerguntas.*;

import java.util.List;
import java.util.ArrayList;

public class GameState {
    private final String codigoJogo;
    private final List<Pergunta> perguntas;
    private int indicePergunta;
    private final List<Equipa> equipas;

    public GameState(String codigoJogo, List <Pergunta> perguntas) {
        this.codigoJogo = codigoJogo;
        this.perguntas = perguntas;
        this.indicePergunta = -1;
        this.equipas = new ArrayList<>();

    }

    private Equipa findOrCreateEquipa(String nomeEquipa) {
        for(Equipa equipa : equipas) {
            if(equipa.getNome().equals(nomeEquipa)) {
                return equipa;
            }
        }

        Equipa novaEquipa = new Equipa(nomeEquipa);
        equipas.add(novaEquipa);
        return novaEquipa;
    }

    public boolean adicionarJogador(String nomeEquipa, String username) {
        Equipa equipa = findOrCreateEquipa(nomeEquipa);
        return equipa.addJogador(new Jogador(username));
    }

    public Pergunta getPerguntaAtual(){
        if(indicePergunta >= 0 && indicePergunta < perguntas.size()) {
            return perguntas.get(indicePergunta);
        }
        return null;
    }

    public Pergunta avancarParaProximaPergunta(){
        indicePergunta++;
        if(indicePergunta < perguntas.size()) {
            return perguntas.get(indicePergunta);
        }
        return null;
    }

    public List<Equipa> getEquipas() {
        return equipas;
    }
}
