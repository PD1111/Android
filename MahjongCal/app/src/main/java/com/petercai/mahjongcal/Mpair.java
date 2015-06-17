package com.petercai.mahjongcal;

public class Mpair {

    private int pairID;

    public Mpair(Mtype mtype) {
        pairID = mtype.getTile();
    }

    public int getPairID() {
        return pairID;
    }

}
