package GameState;
import java.util.Objects;
public class Jogador {
    private final String username;

    public Jogador(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return username.equals(jogador.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
