import splosno.Poteza;

public class Main {
    public static void main(String[] args) {
        //todo stuff comes here
        Igra game = new Igra();
        boolean jeMozno = game.odigraj(new Poteza(0,0));
        System.out.println(jeMozno);

        Frame frame = new Frame();
        frame.pack();
        frame.setVisible(true);
    }

}