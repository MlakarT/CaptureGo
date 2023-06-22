package inteligenca2;

import logika2.Igra2;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.List;
import java.util.Random;

public class Minimax extends Inteligenca2{
    public int depthLimit;
    protected static final Poteza[] directions = {new Poteza(1,0),new Poteza(0,1),new Poteza(-1,0),new Poteza(0,-1)};
    public Minimax ( int limit) {
        this.depthLimit = limit;

    }
    @Override
    public Poteza izberiPotezo (Igra2 igra) {
        return najboljsaPoteza(igra).poteza;
    }



    public OcenjenaPoteza najboljsaPoteza (Igra2 igra) {
        if (igra.izvedenePoteze.size() == 0) {
            Random r = new Random();
            int x = r.nextInt(1,igra.size-2); // da ga nikol ne postav na rob
            int y = r.nextInt(1,igra.size-2);
            return new OcenjenaPoteza(new Poteza(x,y), 0);
        }
        Igra2 kopija = Igra2.copy(igra);
        KdoIgra ai = kopija.naVrsti;
        inteligenca2.OcenjenaPoteza bestMove = null;
        for (int j = 0; j < kopija.size; ++j) {
            for (int i = 0; i < kopija.size; ++i) {
                if (kopija.grid[j][i] != 'e') continue;
                Poteza p = new Poteza(i,j);
                kopija.playMove(p);
                kopija.setGroups();
                kopija.nasprotnik();
                int score = minimax2(kopija, 0, false, ai );
                kopija.undoMove(p);
                kopija.setGroups();
                bestMove = OcenjenaPoteza.max(bestMove, new OcenjenaPoteza(p, score));
                if (bestMove.cost >= 10000  || bestMove.cost <= -10000) break;
            }
        }
        return bestMove;
    }
    /* public int minimax2(Igra2 igra, int globina, boolean maximisingPlayer, KdoIgra ai) {
        return 1;
    } */

    public int minimax2 (Igra2 igra, int globina, boolean maximisingPlayer, KdoIgra ai) {
        int WIN = 10000;
        int LOSS = -WIN;
        if (globina == depthLimit || igra.gameOver) {
            int score;
            //score for ai
            if (igra.winner == igra.black) {
                score = (ai == igra.black ? WIN : LOSS);
            } else if (igra.winner == igra.white) {
                score = (ai == igra.white ? WIN : LOSS);
            } else {
                score = (ai == igra.black ? oceniPlosco(igra) : -oceniPlosco(igra));
            }
            return score;
        } else if (maximisingPlayer) {
            int bestScore =  LOSS;
            for (int j = 0; j < igra.size; ++j) {
                for (int i = 0; i < igra.size; ++i) {
                    if (igra.grid[j][i] != 'e') continue;
                    Poteza p = new Poteza(i,j);
                    igra.playMove(p);
                    igra.setGroups();
                    igra.nasprotnik();
                    int score = minimax2(igra, globina +1, false, ai);
                    igra.undoMove(p);
                    igra.setGroups();
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = WIN;
            for (int j = 0; j < igra.size; ++j) {
                for (int i = 0; i < igra.size; ++i) {
                    if (igra.grid[j][i] != 'e') continue;
                    Poteza p = new Poteza(i,j);
                    igra.playMove(p);
                    igra.setGroups();
                    igra.nasprotnik();
                    int score = minimax2(igra, globina +1, true, ai);
                    igra.undoMove(p);
                    igra.setGroups();
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }
    private int checkAdjacents(Igra2 igra, Poteza p, KdoIgra barvaPoteze) {
        int total = 0;
        for (Poteza dir : directions) {
            Poteza p1 = new Poteza(p.x() + dir.x(), p.y() + dir.y());
            if (0 <= p1.x() && p1.x() < igra.size && 0 <= p1.y() && p1.y() < igra.size ) {
                if (barvaPoteze == igra.barvaFromGrid(p1) ) { total += 2; }
                else if (igra.barvaFromGrid(p1) == null) { ++total; }
                //the null part also accounts for liberties
            }
        }
        return total;
    }
    private int oceniPlosco(Igra2 igra) {
        int WIN = 10000;
        int LOSS = -WIN;
        int blackCost = 0;
        int whiteCost = 0;
        if (igra.gameOver) {
            if (igra.winner == igra.black) {blackCost = WIN; whiteCost = LOSS;}
            else if (igra.winner == igra.white) {whiteCost = WIN; blackCost = LOSS;}
        } else {
            for (int j = 0; j < igra.size; ++j){
                for (int i = 0; i < igra.size; ++i) {
                    if (igra.grid[j][i] == 'B') {
                        ++blackCost;
                        blackCost += checkAdjacents(igra, new Poteza(i,j), igra.black);
                    } else if ( igra.grid[j][i] == 'W') {
                        ++whiteCost;
                        whiteCost += checkAdjacents(igra, new Poteza(i,j), igra.white);
                    }
                }
            }
        }
        return blackCost - whiteCost;
    }
}
