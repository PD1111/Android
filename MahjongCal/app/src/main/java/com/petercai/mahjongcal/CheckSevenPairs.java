package com.petercai.mahjongcal;

public class CheckSevenPairs implements Check {

    private Mtype pairs[] = new Mtype[7];
    private int yibeikou1;
    private int yibeikou2;
    private int yibeikou3;

    private boolean lichi;
    private boolean menqing;
    private boolean jimo;
    private boolean fish;
    private boolean moon;
    private boolean ls;// 岭上开花
    private boolean qiangkang;
    private boolean yifa;
    private boolean doublelz;

    // constructor method
    public CheckSevenPairs(Mtype mtype[] ) {
        boolean con[] = {false, false, false, false, false, false, false, false, false};
        config(mtype, con);
    }

    // the method resets object's configuration
    public void config(Mtype mtype[], boolean boollist[]){

        for (int i = 0; i < 7; i++) {
            pairs[i] = mtype[i];
        }

        for (int i = 0; i < 7; i++){
            for (int j = i+1; j < 7; j++){
                if (pairs[j].getTile() <pairs[i].getTile()){
                    Mtype temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }

        lichi = boollist[0];
        menqing = boollist[1];
        jimo = boollist[2];
        fish = boollist[3];
        moon = boollist[4];
        ls = boollist[5];
        qiangkang = boollist[6];
        yifa = boollist[7];
        doublelz = boollist[8];
    }


    @Override
    public boolean duanYaoJiu() {
        for (int i = 0; i < 7; i++) {
            if (pairs[i].getTile() > 30) {
                return false;
            } else if (pairs[i].getTile() % 10 == 1 || pairs[i].getTile() % 10 == 9) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean yiBeiKou() {

        for (int i = 0; i<5; i++) {
            if ((pairs[i].getTile() < 30) &&(pairs[i+1].getTile() == (pairs[i].getTile() + 1)) && (pairs[i+2].getTile() == (pairs[i+1].getTile() + 1))) {
                yibeikou1 = pairs[i].getTile();
                yibeikou2 = pairs[i+1].getTile();
                yibeikou3 = pairs[i+2].getTile();
                return true && menqing;
            }
        }

        return false;
    }


    @Override
    public boolean hunYiSe() {
        int count = pairs[0].getTile() - (pairs[0].getTile() % 10);
        for(int i = 1; i < 7; i++) {
            if ((pairs[i].getTile() - (pairs[i].getTile() % 10)) != count  && (pairs[i].getTile() < 30)) {
                return false;
            } else if (pairs[i].getTile() - (pairs[i].getTile() % 10) > 30) {
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean qingYiSe() {
        int count = pairs[0].getTile() - (pairs[0].getTile() % 10);
        for(int i = 1; i < 7; i++) {
            if ((pairs[i].getTile() > 30) || ((pairs[i].getTile() - (pairs[i].getTile() % 10)) != count)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean ziYiSe() {
        return (pairs[0].getTile() > 30 ? true : false);
    }

    @Override
    public boolean lvYiSe() {
        for (int i = 0; i < 7; i++) {
            int count = pairs[i].getTile();
            if (count != 12 && count != 13 && count !=14 && count != 16 && count != 18 && count != 36) {
                return false;
            }
        }
        return true;
    }

    public boolean erBeiKou(){
        for (int i = 0; i<5; i++) {
            boolean not_prev = (pairs[i].getTile() != yibeikou1) && (pairs[i].getTile() != yibeikou2) &&(pairs[i].getTile() != yibeikou3);

            if ((pairs[i].getTile() < 30) && (pairs[i+1].getTile() == (pairs[i].getTile() + 1)) && (pairs[i+2].getTile() == (pairs[i+1].getTile() + 1)) && not_prev) {
                return true;
            }
        }

        return false;

    }

    @Override
    public int scoreCheck() {
        int sum = 0;
        sum = sum + (lichi ? 1 : 0) + ((menqing && jimo) ? 1 : 0) + ((fish || moon) ? 1:0) + ((ls || qiangkang)? 1 : 0) + 2;
        System.out.println(sum);
        if (hunYiSe()){
            if (qingYiSe()){
                sum += 5;
            }else if(ziYiSe() || lvYiSe()){
                return 13;
            }else{
                sum += 2;
            }
        }
        System.out.println(sum);
        if (yiBeiKou()){
            if (erBeiKou()){
                sum += 2;
            }
            sum += 1;
        }
        System.out.println(sum);
        sum += (duanYaoJiu()? 1 :0);
        sum = sum + (doublelz == true ? 1 : 0) + (yifa == true ? 1 : 0);

        return sum > 13 ? 13 : sum;
    }

}
