package LeituraPerguntas;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class CarregadorPerguntas {

    /**
     * Carrega as perguntas de um ficheiro JSON.
     *
     * @param caminhoFicheiro O caminho para o ficheiro (ex: "src/main/resources/perguntas.json")
     * @return Uma lista de objetos Pergunta
     */
    public List<Pergunta> carregarPerguntas(String caminhoFicheiro) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(caminhoFicheiro)) {

            QuizFile quizFile = gson.fromJson(reader, QuizFile.class);

            if (quizFile != null && quizFile.getQuizzes() != null && !quizFile.getQuizzes().isEmpty()) {
                Quiz primeiroQuiz = quizFile.getQuizzes().get(0);

                return primeiroQuiz.getQuestions();
            } else {
                System.err.println("O ficheiro de perguntas está vazio ou mal formatado.");
                return null;
            }

        } catch (Exception e) {
            System.err.println("Erro fatal ao ler o ficheiro de perguntas JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
