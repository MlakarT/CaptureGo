import logika.Igra;
import splosno.Poteza;
import vodja.Vodja;

public class Main {
    public static void main(String[] args) {
        //todo stuff comes here
        //Igra igra = new Igra();
        //System.out.println(igra.poteze());
        //igra.odigraj(new Poteza(0,0));
        //System.out.println(igra.poteze());
        // Igra.poteze works as intended

        //todo launch game
        gui.Frame frame = new gui.Frame();
        frame.pack();
        frame.setVisible(true);
        Vodja.frame = frame;
    }
}