package LeituraPerguntas;
import java.util.List;

/**
 * Representa um quiz individual, que tem um nome e uma lista de perguntas.
 */
public class Quiz {

    private String name;
    private List<Pergunta> questions;

    public String getName() {
        return name;
    }

    public List<Pergunta> getQuestions() {
        return questions;
    }
}