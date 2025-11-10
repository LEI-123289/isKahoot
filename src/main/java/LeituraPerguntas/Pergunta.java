package LeituraPerguntas;
import java.util.List;



public class Pergunta{

    private String question;
    private int points;
    private int correct; // O índice da resposta correta
    private List<String> options;

    // Getters para o teu colega (e para ti) usarem
    public String getQuestion() {
        return question;
    }

    public int getPoints() {
        return points;
    }

    public int getCorrectOptionIndex() {
        return correct;
    }

    public List<String> getOptions() {
        return options;
    }

    // (Podes adicionar um toString() para debugging)
    @Override
    public String toString() {
        return "Pergunta{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", correct=" + correct +
                '}';
    }
}