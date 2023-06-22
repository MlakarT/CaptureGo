package inteligenca2;

import splosno.Poteza;

public class OcenjenaPoteza {
    public Poteza poteza;
    public int cost;

    public OcenjenaPoteza(Poteza p, int cost) {
        this.poteza = p;
        this.cost = cost;
    }

    @Override
    public String toString () {
        return "OcenjenaPoteza("+ poteza +", " + cost+")";
    }

    public static OcenjenaPoteza max(OcenjenaPoteza a, OcenjenaPoteza b) {
        //only poteza a is null
        //if a is null then i is always bigger -> coincides with min value -inf
        return (a == null ? b : (a.cost >= b.cost ? a : b));
    }

    public static OcenjenaPoteza min (OcenjenaPoteza a, OcenjenaPoteza b) {
        //only poteza a is null
        //if a is null then b is always smaller -> coincides with max value +inf
        return (a == null ? b : (a.cost <= b.cost ? a : b));
    }
}
