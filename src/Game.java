import general.KdoIgra;
import general.Poteza;

public class Game {
    private Poteza turn;
    private KdoIgra player;
    public Game() {

    }
    public static String info(Game game) {
        return "It's" + game.player.ime() + "'s turn.";
    }
}
