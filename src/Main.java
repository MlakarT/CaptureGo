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
        //System.out.println(igra.poteze());
        //igra.odigraj(new Poteza(0,0));
        //System.out.println(igra.poteze());
        // Igra.poteze works as intended
        Igra igra = new Igra();
        Poteza p1 = new Poteza(1,2);
        igra.odigraj(p1);
        OcenjenaPoteza op1 = new OcenjenaPoteza(p1, OceniPozicijo.oceniPozicijo(igra, igra.nasprotnik()));
        Poteza p2 = new Poteza(1,3);
        igra.odigraj(p2);
        OcenjenaPoteza op2 = new OcenjenaPoteza(p2, OceniPozicijo.oceniPozicijo(igra, igra.nasprotnik()));

        System.out.println(OcenjenaPoteza.max(op1,op2));
        System.out.println(OcenjenaPoteza.min(op1,op2));

        //todo launch game
        gui.Frame frame = new gui.Frame();
        frame.pack();
        frame.setVisible(true);
        Vodja.frame = frame;
    }
}