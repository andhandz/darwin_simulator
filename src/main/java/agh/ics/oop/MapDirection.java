package agh.ics.oop;

public enum MapDirection {
    N,
    NE,
    E,
    ES,
    S,
    SW,
    W,
    WN;

    public String toString() {
        return switch(this){
            case N -> "N";
            case NE -> "NE";
            case E -> "E";
            case ES-> "ES";
            case S -> "S";
            case SW -> "SW";
            case W -> "W";
            case WN -> "WN";
        };
    }
    public Vector2d toUnitVector(){
        return switch(this){
            case N -> new Vector2d(0,1);
            case NE -> new Vector2d(1,1);
            case E -> new Vector2d(1,0);
            case ES-> new Vector2d(1,-1);
            case S -> new Vector2d(0,-1);
            case SW -> new Vector2d(-1,-1);
            case W -> new Vector2d(-1,0);
            case WN -> new Vector2d(-1,1);
        };
    }

    public MapDirection next(){
        return switch(this){
            case N -> NE;
            case NE -> E;
            case E -> ES;
            case ES-> S;
            case S -> SW;
            case SW -> W;
            case W -> WN;
            case WN -> N;
        };
    }
}
