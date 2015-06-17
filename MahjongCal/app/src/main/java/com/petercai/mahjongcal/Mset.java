package com.petercai.mahjongcal;

public class Mset {

    private int setID;

    public Mset(Mtype mtype[]) {
        setSetID(mtype);
    }

    public void setSetID(Mtype mtype[]) {
        int first = min(mtype[0].getTile(), mtype[1].getTile(), mtype[2].getTile());
        if (mtype[0].getTile() == mtype[1].getTile()) {
            setID = first + 30;
        } else {
            setID = first;
        }
    }

    public int getSetID() {
        return setID;
    }

    public int min(int first, int second, int third) {
        int a = first < second ? first:second;
        return (a < third ? a : third);
    }

}
