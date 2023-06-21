package inteligenca;

import java.sql.SQLOutput;
import java.util.List;

import logika.Igra;

import splosno.Poteza;

import inteligenca.OceniPozicijo;

public class Minimax extends Inteligenca {

    private final int globina;
    public Minimax (int globina) {
        this.globina = globina;
    }
    @Override
    public Poteza izberiPotezo (Igra igra) {
        //OcenjenaPoteza najboljsaPoteza = minimax(igra, this.globina, true);
        //return najboljsaPoteza.poteza;
        return najboljsaPoteza(igra).poteza;
    }

    @Override
    public void igrajPotezo(Igra igra, Poteza poteza) {
        igra.odigraj(poteza);
    }

    public OcenjenaPoteza minimax(Igra igra, int globina, boolean me) {
        OcenjenaPoteza ocenjenaPoteza = null;
        for (Poteza p : igra.poteze) {
            System.out.println("testing poteza " + p + " at depth " + globina);
            igra.odigraj(p);
            //check if we've reached either the end of minimax or end of game
            if (globina == 1 || igra.state == Igra.WHITE_WIN || igra.state == Igra.BLACK_WIN) {
                System.out.println("reached globina " + globina + " or end of game");
                if (me) { //if my turn then take highest
                    ocenjenaPoteza = OcenjenaPoteza.max(ocenjenaPoteza,
                            new OcenjenaPoteza(p,OceniPozicijo.oceniPozicijo(igra, igra.nasprotnik())));
                } else { //else take lowest
                    ocenjenaPoteza = OcenjenaPoteza.min(ocenjenaPoteza,
                            new OcenjenaPoteza(p,OceniPozicijo.oceniPozicijo(igra, igra.nasprotnik())));
                }
                igra.odOdigraj(p);
            } else if (me) { // if my turn
                System.out.println("its my turn");
                ocenjenaPoteza = OcenjenaPoteza.max(ocenjenaPoteza, minimax(igra, globina - 1, false));
                igra.odOdigraj(p);
            } else { // if !my turn
                System.out.println("its not my turn");
                ocenjenaPoteza = OcenjenaPoteza.min(ocenjenaPoteza, minimax(igra, globina - 1, true));
                igra.odOdigraj(p);
            }
            System.out.println("reached the end of minimax block, removing poteza " + p);

        }
    return ocenjenaPoteza;


        //todo po defaultu je nazacetku ocenjena poteza null
        //OcenjenaPoteza best = null;
        //todo pogledamo use mozne poteze v trenutni igri
        //List<Poteza> odprtePoteze = igra.poteze();
        // podamo zacetno overrideable oceno
        //int me = igra.state;
        //todo gremo po ush potezah
        //for (Poteza p : odprtePoteze) {
        //        //pogledamo kakšno oceno dobimo, če odigramo potezo
        //        Igra kopija = Igra.copy(igra);
        //        kopija.odigraj(p);
        //        OcenjenaPoteza ocenjenaPoteza;
                // ce je igre konc, hocemo avtomatsko zakluct minimax rekurzijo
        //        if (kopija.state == Igra.CAPTURED_BLACK || kopija.state == Igra.CAPTURED_WHITE) {
        //            System.out.println("game over for " + kopija.state);
        //            if (me == Igra.BLACK_STATE && kopija.state == Igra.CAPTURED_WHITE) {
        //                return new OcenjenaPoteza(p, OceniPozicijo.WIN);
        //            } else if (me == Igra.WHITE_STATE && kopija.state == Igra.CAPTURED_BLACK) {
        //                return new OcenjenaPoteza(p, OceniPozicijo.WIN);
        //            } else {
        //                return new OcenjenaPoteza(p, OceniPozicijo.LOSS);
        //            }
        //        }
                //ce igre ni konc nadaljujemo minimaxa ce je se mozno
        //        if (globina > 1) {
                    //če mamo se minimax, pogledamo katero od potez bo izbral NASPROTNIK,
        //            System.out.println("Doing minimax again on depth " + (globina-1));
        //            OcenjenaPoteza minimaxPoteza = minimax(kopija, globina-1);
        //            ocenjenaPoteza = new OcenjenaPoteza(minimaxPoteza.poteza, - minimaxPoteza.cost);
        //        }
                //cene zaklucmo in ocenmo pozicijo
        //        else {
        //            int ocena = OceniPozicijo.oceniPozicijo(kopija, igra.state);
        //            ocenjenaPoteza = new OcenjenaPoteza(p, ocena);
        //        }
        //        if (best == null || ocenjenaPoteza.cost > best.cost || ocenjenaPoteza.cost < best.cost) {best = ocenjenaPoteza;}
        //    }
        //podamo končno oceno
        //System.out.println("picked " + best);
        //return best;





        //System.out.println("Shut up im thinking..");
        //OcenjenaPoteza najboljsaPoteza = null;
        //List<Poteza> moznePoteze = igra.poteze();
        // int ocena;
        //for (Poteza p: moznePoteze) {
            //System.out.println(".. thinking of " + p);
        //    Igra kopijaIgre = Igra.copy(igra);
        //    kopijaIgre.odigraj (p);
        //    switch (kopijaIgre.state) {
                //check if game over
        //        case Igra.CAPTURED_BLACK -> {
        //            ocena = (igra.state == Igra.WHITE_STATE ? WIN : LOSS);
        //            System.out.println("Will capture black");
        //        }
        //        case Igra.CAPTURED_WHITE -> {
        //            ocena = (igra.state == Igra.BLACK_STATE ? WIN : LOSS);
        //            System.out.println("Will capture white");
        //        }
        //        default -> {
                    // nekdo je na potezi
        //            if (globina == 1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre, p, igra.state).cost;
                    // globina > 1
        //            else {
        //                ocena = minimax(kopijaIgre, globina - 1).cost;
        //                System.out.println("Doing minimax again on depth" + (globina - 1));
        //            }
        //        }
        //    }
        //    if (najboljsaPoteza == null
                    // max, če je p moja poteza
                    //todo vedno se ta izvede
        //            || ocena > najboljsaPoteza.cost
                    // sicer min
        //            || ocena < najboljsaPoteza.cost)
        //        najboljsaPoteza = new OcenjenaPoteza (p, ocena);
        //}
        //System.out.println("Picked " + najboljsaPoteza.poteza + " of value " + najboljsaPoteza.cost);
        //return najboljsaPoteza;

    }

    public OcenjenaPoteza najboljsaPoteza (Igra igra) {
        int ai = igra.state;
        OcenjenaPoteza bestMove = null;
        for (Poteza p : igra.poteze) {
            igra.odigraj(p);
            int score = minimax2(igra, 0, false, ai,5);
            igra.odOdigraj(p);
            bestMove = OcenjenaPoteza.max(bestMove, new OcenjenaPoteza(p, score));
        }
        return bestMove;
    }
    public int minimax2 (Igra igra, int globina, boolean maximisingPlayer, int ai, int depthLimit) {
        if (globina == depthLimit || igra.winner() != 0) {
            int score;
            switch (igra.state) {
                case Igra.BLACK_STATE, Igra.WHITE_STATE -> {score = (ai == Igra.BLACK_STATE ? OceniPozicijo.oceniPlosco(igra) : -OceniPozicijo.oceniPlosco(igra));}
                case Igra.BLACK_WIN -> {score = (ai == Igra.BLACK_STATE ? OceniPozicijo.WIN : OceniPozicijo.LOSS);}
                case Igra.WHITE_WIN -> {score = (ai == Igra.WHITE_STATE ? OceniPozicijo.WIN : OceniPozicijo.LOSS);}
                default ->  score = 0;
            }
            return score;
        } else if (maximisingPlayer) {
            int bestScore = OceniPozicijo.LOSS;
            for (Poteza p : igra.poteze) {
                igra.odigraj(p);
                int score = minimax2(igra, globina +1, false, ai, depthLimit);
                igra.odOdigraj(p);
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        } else {
            int bestScore = OceniPozicijo.WIN;
            for (Poteza p : igra.poteze) {
                igra.odigraj(p);
                int score = minimax2(igra, globina +1, true, ai, depthLimit);
                igra.odOdigraj(p);
                bestScore = Math.min(bestScore, score);
            }
            return bestScore;
        }
    }
}