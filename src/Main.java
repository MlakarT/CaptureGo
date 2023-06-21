import inteligenca.OceniPozicijo;
import inteligenca.OcenjenaPoteza;
import logika.Igra;
import splosno.Poteza;
import vodja.Vodja;

import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
        //todo stuff comes here
        //Igra igra = new Igra();
        //System.out.println(igra.poteze()+ "\n" + igra.state);
        //igra.odigraj(new Poteza(0,0));
        //System.out.println(igra.poteze()+ "\n" + igra.state);
        //System.out.println(igra.groups);
        //igra.odOdigraj(new Poteza(0,0));
        //System.out.println(igra.poteze()+ "\n" + igra.state);
        //System.out.println(igra.groups + "\n");

        //todo launch game
        gui.Frame frame = new gui.Frame();
        frame.pack();
        frame.setVisible(true);
        Vodja.frame = frame;
    }
}