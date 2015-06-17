package com.petercai.mahjongcal;

public class RegSet implements Check{

    private int setID[] = new int[4];
    private int pair;
    private boolean kaiMen[] = {false, false, false, false};
    private boolean kang[] = {false, false, false, false};

    private int ziFeng;
    private int changFeng;
    private boolean lichi;
    private boolean menqing;
    private boolean jimo;
    private boolean fish;
    private boolean moon;
    private boolean ls;// 岭上开花
    private boolean qiangkang;
    private boolean yifa;
    private boolean doublelz;
    private int ronSet;
    private int ronTile;

    public RegSet(int a[],int rS, int rT, boolean boollist[], int z, int c, boolean con1[], boolean con2[]) {
        for (int i = 0; i < 4; i++) {
            setID[i] = a[i];
            kaiMen[i] = con1[i];
            kang[i] = con2[i];
        }

        //Sort the sets
        for (int i = 0; i < 4; i++){
            for (int j = i+1; j < 4; j++){
                if (setID[j] < setID[i]){
                    int temp = setID[i];
                    setID[i] = setID[j];
                    setID[j] = temp;
                }
            }
        }

        pair = a[4];
        lichi = boollist[0];
        menqing = boollist[1];
        jimo = boollist[2];
        fish = boollist[3];
        moon = boollist[4];
        ls = boollist[5];
        qiangkang = boollist[6];
        yifa = boollist[7];
        doublelz = boollist[8];
        ronSet = rS;
        ronTile = rT;
        ziFeng = z;
        changFeng = c;
    }

    @Override
    public boolean duanYaoJiu() {
        if (pair > 30 || pair % 10 == 1 || pair % 10 == 9)
            return false;
        for (int i = 0; i < 4; i++) {
            if (setID[i] < 30 && (setID[i] % 10 == 1 || setID[i] % 10 == 7)) {
                return false;
            } else if ((setID[i] > 30 && (setID[i] % 10 == 1 || setID[i] % 10 == 9)) || setID[i] > 60) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean yiBeiKou() {
        if ((setID[0] == setID[1]) || (setID[1] == setID[2])||(setID[2] == setID[3])){
            return true;
        }
        return false;
    }

    @Override
    public boolean hunYiSe() {
        int sei = setID[0] - setID[0]%10;
        if (sei > 30 && sei < 60){
            sei -= 30;
        }

        if (pair - pair%10 != sei || pair > 30){
            return false;
        }

        for (int i = 1; i < 4; i++){
            int range = setID[i] - setID[i]%10;
            range = range - ((range >= 30)&&(range < 60) ? 30 : 0);
            if (sei != range && range < 60){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean qingYiSe() {
        int sei = setID[0] - setID[0]%10;
        if (sei > 30 && sei < 60){
            sei -= 30;
        }

        if (pair - pair%10 != sei){
            return false;
        }

        for (int i = 1; i < 4; i++){
            int range = setID[i] - setID[i]%10;
            range = range - ((range >= 30)&&(range < 60) ? 30 : 0);
            if (sei != range){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean ziYiSe() {
        return pair > 30 && setID[0] > 60;
    }

    @Override
    public boolean lvYiSe() {
        if (pair != 12 && pair != 13 && pair !=14 && pair != 16 && pair != 18 && pair != 36){
            return false;
        }

        for (int i = 0; i < 4; i++){
            if (setID[i] != 42 && setID[i] != 43 && setID[i] !=44 && setID[i] != 46 && setID[i] != 48 && setID[i] != 66 && setID[i] != 12) {
                return false;
            }
        }

        return true;
    }

    public boolean pingHu(){
        if (pair > 30) return false;

        for (int i = 0; i < 4; i++){
            if (setID[i] > 30) return false;
        }

        if (ronTile == ronSet+1) return false;
        if (((ronSet%10 == 1)&&(ronTile%10 == 3)) || ((ronSet%10 == 7)&&(ronTile%10 == 7))) return false;

        return true;

    }

    public int yi(){
        int re = 0;

        for (int i = 0; i < 4; i ++){
            if (setID[i] >= 65)
                re++;
        }

        return re;
    }

    public int tongke_shun(){
        int first = setID[0] - (setID[0] > 30 ? 30 : 0);
        int second = setID[1] - (setID[1] > 30 ? 30 : 0);
        int third = setID[2] - (setID[2] > 30 ? 30 : 0);
        int fourth = setID[3] - (setID[3] > 30 ? 30 : 0);

        if (first > 10)
            return 0;

        if ((second - first == 10 && third - second == 10) || (third - second == 10 && fourth - third == 10)
                || (second - first == 10 && fourth - second == 10) || (third - first == 10 && fourth - third == 10)){
            return 2 - ((!menqing && (setID[0]<30||setID[1]<30))?1:0);
        }

        return 0;
    }

    public boolean yiQiGuanTong() {
        if ((setID[0] % 10 == 1 && setID[1] % 10 == 4 && setID[2] % 10 == 7 && setID[2] < 30) ||
                (setID[1] % 10 == 1 && setID[2] % 10 == 4 && setID[3] % 10 == 7 && setID[3] < 30)) {
            return true;
        }
        return false;
    }

    public boolean hunQuanDaiYaoJiu() {
        if (pair > 30 || (pair % 10 != 1 && pair % 10 != 9)) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (setID[i] < 30) {
                if (setID[i] % 10 != 1 && setID[i] % 10 != 7) {
                    return false;
                }
            } else if (setID[i] > 30 && setID[i] < 60){
                if (setID[i] % 10 != 1 && setID[i] % 10 != 9) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean chunQuan(){
        for (int i = 0; i < 4; i++){
            if (setID[i]>60) return false;
        }
        return true;
    }

    public boolean duiDuiHe() {
        if (setID[0] > 30) {
            return true;
        } else {
            return false;
        }
    }

    public int anKe() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (setID[i] > 30 && kaiMen[i] == false) {
                count++;
            }
        }
        if (count == 4) {
            return 13;
        } else if (count == 3) {
            return 2;
        } else {
            return 0;
        }
    }

    public int kang() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (kang[i] == true) {
                count++;
            }
        }
        if (count == 4) {
            return 13;
        } else if (count == 3) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean hunLaoTou() {
        if (pair < 30 && pair % 10 != 1 && pair % 10 != 9)
            return false;
        for (int i = 0; i < 4; i++) {
            if (setID[i] < 30) {
                return false;
            } else if (setID[i] > 30 && setID[i] < 60 && setID[i] % 10 != 1 && setID[i] % 10 != 9) {
                return false;
            }
        }
        return true;
    }

    public boolean xiaoSanYuan() {
        return pair >= 35 && yi() == 2;
    }

    public boolean daSanYuan() {
        return yi() == 3;
    }

    public boolean daSiXi() {
        return setID[0] > 60 && setID[3] < 65;
    }

    public boolean xiaoSiXi() {
        if (pair < 30 || pair > 34) {
            return false;
        }
        if ((setID[0] >= 61 && setID[2] <= 64) || (setID[1] >= 61 && setID[3] <= 64)) {
            return true;
        }
        return false;
    }

    public boolean qingLaoTou() {
        return hunLaoTou() && setID[3] < 60 && pair < 30;
    }

    public int feng(){
        int count = 0;
        for (int i = 0; i < 4; i++){
            if (setID[i]==ziFeng) count += 1;
            if (setID[i]==changFeng) count += 1;
        }

        return count;
    }

    public boolean yiMan(){
        if (anKe() == 13) return true;
        if (daSanYuan()) return true;
        if (xiaoSiXi() || daSiXi()) return true;
        if (lvYiSe() || ziYiSe()) return true;
        if (qingLaoTou()) return true;
        if (kang() == 13) return true;

        return false;
    }

    @Override
    public int scoreCheck() {

        if(yiMan()) return 13;

        int s=(lichi?1:0)+(yifa?1:0)+(doublelz?1:0)+(jimo&&menqing?1:0)+(fish||moon||ls||qiangkang?1:0);

        s += duanYaoJiu()?1:0;
        s += feng();
        s += yi();

        s += tongke_shun();
        s += anKe();
        s += kang();
        s += xiaoSanYuan()?2:0;

        if (hunYiSe()){
            s += menqing?1:0;
            if (qingYiSe()) s += 5;
            else s += 2;
        }

        if (duiDuiHe()){
            s += 2;

            s += hunLaoTou()?2:0;
        }else{
            s += pingHu()?1:0;
            s += yiBeiKou()?1:0;

            if (hunQuanDaiYaoJiu()){
                if (chunQuan()) s+= menqing?3:2;
                else s+=menqing?2:1;
            }

            if (yiQiGuanTong()){
                s+=menqing?2:1;
            }
        }

        return s>13?13:s;
    }

}
