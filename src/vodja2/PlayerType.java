package vodja2;

public enum PlayerType {
    R, C;

    @Override
    public String toString() {
        switch (this) {
            case C: return "človek";
            case R: return "řačunalnik";
            default: assert false; return "";
        }
    }

}