package com.petercai.mahjongcal;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.graphics.drawable.Drawable;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {



    //calculate DORA
    private Spinner spinner;
    private int dr = 0;
    private int changfeng;
    private int zifeng;
    private boolean lizhi = false;
    private boolean zimo = false;
    private boolean lingshangkaihua = false;
    private boolean qianggang = false;
    private boolean haidimoyue = false;
    private boolean hedilaoyu = false;
    private boolean yifa = false;
    private boolean menqing = false;
    private boolean a[] = new boolean[9];
    private boolean kaimen[] = new boolean[4];
    private boolean kang[] = new boolean[4];
    private Mtype tiles[] = new Mtype[3];
    private CheckBox check1;
    private CheckBox check2;
    private CheckBox check3;
    private CheckBox check4;
    private CheckBox check5;
    private CheckBox check6;
    private CheckBox check7;
    private CheckBox check8;
    private RadioGroup rg1, rg2;
    private RadioButton rb1,rb2,rb3,rb4,rb5, rb6,rb7,rb8,rbs1, rbs2;
    private Button start;
    private ImageButton tileb[] = new ImageButton[34];
    private ImageView images[] = new ImageView[14];
    private int cur_display = 0;
    private Button clear;
    private Mset set[] = new Mset[4];
    private int setID[] = new int[5];

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number, android.R.layout.simple_spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        for (int i = 0; i < 4; i++) {
            kaimen[i] = false;
            kang[i] = false;
        }

        display = (TextView) findViewById(R.id.textView);

        tiles[0] = new Mtype(0);
        tiles[1] = new Mtype(0);
        tiles[2] = new Mtype(0);

        tileb[0] = (ImageButton) findViewById(R.id.mm1);
        tileb[1] = (ImageButton) findViewById(R.id.mm2);
        tileb[2] = (ImageButton) findViewById(R.id.mm3);
        tileb[3] = (ImageButton) findViewById(R.id.mm4);
        tileb[4] = (ImageButton) findViewById(R.id.mm5);
        tileb[5] = (ImageButton) findViewById(R.id.mm6);
        tileb[6] = (ImageButton) findViewById(R.id.mm7);
        tileb[7] = (ImageButton) findViewById(R.id.mm8);
        tileb[8] = (ImageButton) findViewById(R.id.mm9);
        tileb[9] = (ImageButton) findViewById(R.id.mm11);
        tileb[10] = (ImageButton) findViewById(R.id.mm12);
        tileb[11] = (ImageButton) findViewById(R.id.mm13);
        tileb[12] = (ImageButton) findViewById(R.id.mm14);
        tileb[13] = (ImageButton) findViewById(R.id.mm15);
        tileb[14] = (ImageButton) findViewById(R.id.mm16);
        tileb[15] = (ImageButton) findViewById(R.id.mm17);
        tileb[16] = (ImageButton) findViewById(R.id.mm18);
        tileb[17] = (ImageButton) findViewById(R.id.mm19);
        tileb[18] = (ImageButton) findViewById(R.id.mm21);
        tileb[19] = (ImageButton) findViewById(R.id.mm22);
        tileb[20] = (ImageButton) findViewById(R.id.mm23);
        tileb[21] = (ImageButton) findViewById(R.id.mm24);
        tileb[22] = (ImageButton) findViewById(R.id.mm25);
        tileb[23] = (ImageButton) findViewById(R.id.mm26);
        tileb[24] = (ImageButton) findViewById(R.id.mm27);
        tileb[25] = (ImageButton) findViewById(R.id.mm28);
        tileb[26] = (ImageButton) findViewById(R.id.mm29);
        tileb[27] = (ImageButton) findViewById(R.id.mm31);
        tileb[28] = (ImageButton) findViewById(R.id.mm32);
        tileb[29] = (ImageButton) findViewById(R.id.mm33);
        tileb[30] = (ImageButton) findViewById(R.id.mm34);
        tileb[31] = (ImageButton) findViewById(R.id.mm35);
        tileb[32] = (ImageButton) findViewById(R.id.mm36);
        tileb[33] = (ImageButton) findViewById(R.id.mm37);

        images[0] = (ImageView) findViewById(R.id.imageView1);
        images[1] = (ImageView) findViewById(R.id.imageView2);
        images[2] = (ImageView) findViewById(R.id.imageView3);
        images[3] = (ImageView) findViewById(R.id.imageView4);
        images[4] = (ImageView) findViewById(R.id.imageView5);
        images[5] = (ImageView) findViewById(R.id.imageView6);
        images[6] = (ImageView) findViewById(R.id.imageView7);
        images[7] = (ImageView) findViewById(R.id.imageView8);
        images[8] = (ImageView) findViewById(R.id.imageView9);
        images[9] = (ImageView) findViewById(R.id.imageView10);
        images[10] = (ImageView) findViewById(R.id.imageView11);
        images[11] = (ImageView) findViewById(R.id.imageView12);
        images[12] = (ImageView) findViewById(R.id.imageView13);
        images[13] = (ImageView) findViewById(R.id.imageView14);

        check1 = (CheckBox) findViewById(R.id.checkBox1);
        check2 = (CheckBox) findViewById(R.id.checkBox2);
        check3 = (CheckBox) findViewById(R.id.checkBox3);
        check4 = (CheckBox) findViewById(R.id.checkBox4);
        check5 = (CheckBox) findViewById(R.id.checkBox5);
        check6 = (CheckBox) findViewById(R.id.checkBox6);
        check7 = (CheckBox) findViewById(R.id.checkBox7);
        check8 = (CheckBox) findViewById(R.id.checkBox8);
        rg1 = (RadioGroup) findViewById(R.id.r_zifeng);
        rg2 = (RadioGroup) findViewById(R.id.r_changfeng);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        rb5 = (RadioButton) findViewById(R.id.radioButton5);
        rb6 = (RadioButton) findViewById(R.id.radioButton6);
        rb7 = (RadioButton) findViewById(R.id.radioButton7);
        rb8 = (RadioButton) findViewById(R.id.radioButton8);
        start = (Button) findViewById(R.id.button1);

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                kaishi(v);
            }
        });

        for (int i = 0; i < 4; i++){
            int max = i == 3 ? 7:9;
            for (int j = 0; j < max; j++){
                tileb[i*9+j].setOnClickListener(new tileButtonListener(i*10+(j+1)));
            }
        }

        clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private class tileButtonListener implements OnClickListener{

        int tileNum;

        public tileButtonListener(int num){
            super();
            tileNum = num;
        }

        @Override
        public void onClick(View v){
            tileClick(tileNum);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        TextView myview = (TextView) view;
        dr = Integer.parseInt(myview.getText().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adpaterView){}

    public void tileClick(int num){

       if (cur_display < 14) {
           Drawable image;
           if (num == 1) image = getResources().getDrawable(R.drawable.mm1);
           else if (num == 2) image = getResources().getDrawable(R.drawable.mm2);
           else if (num == 3) image = getResources().getDrawable(R.drawable.mm3);
           else if (num == 4) image = getResources().getDrawable(R.drawable.mm4);
           else if (num == 5) image = getResources().getDrawable(R.drawable.mm5);
           else if (num == 6) image = getResources().getDrawable(R.drawable.mm6);
           else if (num == 7) image = getResources().getDrawable(R.drawable.mm7);
           else if (num == 8) image = getResources().getDrawable(R.drawable.mm8);
           else if (num == 9) image = getResources().getDrawable(R.drawable.mm9);
           else if (num == 11) image = getResources().getDrawable(R.drawable.mm11);
           else if (num == 12) image = getResources().getDrawable(R.drawable.mm12);
           else if (num == 13) image = getResources().getDrawable(R.drawable.mm13);
           else if (num == 14) image = getResources().getDrawable(R.drawable.mm14);
           else if (num == 15) image = getResources().getDrawable(R.drawable.mm15);
           else if (num == 16) image = getResources().getDrawable(R.drawable.mm16);
           else if (num == 17) image = getResources().getDrawable(R.drawable.mm17);
           else if (num == 18) image = getResources().getDrawable(R.drawable.mm18);
           else if (num == 19) image = getResources().getDrawable(R.drawable.mm19);
           else if (num == 21) image = getResources().getDrawable(R.drawable.mm21);
           else if (num == 22) image = getResources().getDrawable(R.drawable.mm22);
           else if (num == 23) image = getResources().getDrawable(R.drawable.mm23);
           else if (num == 24) image = getResources().getDrawable(R.drawable.mm24);
           else if (num == 25) image = getResources().getDrawable(R.drawable.mm25);
           else if (num == 26) image = getResources().getDrawable(R.drawable.mm26);
           else if (num == 27) image = getResources().getDrawable(R.drawable.mm27);
           else if (num == 28) image = getResources().getDrawable(R.drawable.mm28);
           else if (num == 29) image = getResources().getDrawable(R.drawable.mm29);
           else if (num == 31) image = getResources().getDrawable(R.drawable.mm31);
           else if (num == 32) image = getResources().getDrawable(R.drawable.mm32);
           else if (num == 33) image = getResources().getDrawable(R.drawable.mm33);
           else if (num == 34) image = getResources().getDrawable(R.drawable.mm34);
           else if (num == 35) image = getResources().getDrawable(R.drawable.mm35);
           else if (num == 36) image = getResources().getDrawable(R.drawable.mm36);
           else image = getResources().getDrawable(R.drawable.mm37);


           if (cur_display < 12) {
               tiles[cur_display % 3].setTile(num);
               if (cur_display % 3 == 2) {
                   set[cur_display / 3] = new Mset(tiles);
                   setID[cur_display / 3] = set[cur_display / 3].getSetID();
                   //System.out.println(set[cur_display/3].getSetID());
           }
           }else{
               setID[4] = num;
           }

           images[cur_display].setImageDrawable(image);
           cur_display++;
       }else{
           Toast.makeText(MainActivity.this, "数量を過ぎています",Toast.LENGTH_SHORT).show();
       }
    }

    public void kaishi(View v) {
        if (check1.isChecked()) qianggang = true;
        if (check2.isChecked()) lizhi = true;
        if (check3.isChecked()) hedilaoyu = true;
        if (check4.isChecked()) menqing = true;
        if (check5.isChecked()) zimo = true;
        if (check6.isChecked()) lingshangkaihua = true;
        if (check7.isChecked()) haidimoyue = true;
        if (check8.isChecked()) yifa = true;
        a[0] = lizhi;
        a[1] = menqing;
        a[2] = zimo;
        a[3] = hedilaoyu;
        a[4] = haidimoyue;
        a[5] = lingshangkaihua;
        a[6] = qianggang;
        a[7] = yifa;
        a[8] = false;

        int selected1 = rg1.getCheckedRadioButtonId();
        rbs1 = (RadioButton) findViewById(selected1);
        if (rbs1.getText().toString().compareTo("东") == 0)
            zifeng = 31;
        if (rbs1.getText().toString().compareTo("南") == 0)
            zifeng = 32;
        if (rbs1.getText().toString().compareTo("西") == 0)
            zifeng = 33;
        if (rbs1.getText().toString().compareTo("北") == 0)
            zifeng = 34;

        int selected2 = rg2.getCheckedRadioButtonId();
        rbs2 = (RadioButton) findViewById(selected2);
        if (rbs2.getText().toString().compareTo("东") == 0)
            changfeng = 31;
        if (rbs2.getText().toString().compareTo("南") == 0)
            changfeng = 32;
        if (rbs2.getText().toString().compareTo("西") == 0)
            changfeng = 33;
        if (rbs2.getText().toString().compareTo("北") == 0)
            changfeng = 34;

        RegSet newset = new RegSet(setID, setID[1], setID[1],a, zifeng,changfeng,kaimen,kang);
        display.setText(newset.scoreCheck()+"番");
        System.out.println(setID[0]);
        System.out.println(setID[1]);
        System.out.println(setID[2]);
        System.out.println(setID[3]);
        System.out.println(setID[4]);

    }

    public void reset() {
        qianggang = false;
        lizhi = false;
        hedilaoyu = false;
        menqing = false;
        zimo = false;
        lingshangkaihua = false;
        haidimoyue = false;
        yifa = false;

        rg1.check(R.id.radioButton5);
        rg2.check(R.id.radioButton1);

        cur_display = 0;

        if (check1.isChecked()) check1.toggle();
        if (check2.isChecked()) check2.toggle();
        if (check3.isChecked()) check3.toggle();
        if (check4.isChecked()) check4.toggle();
        if (check5.isChecked()) check5.toggle();
        if (check6.isChecked()) check6.toggle();
        if (check7.isChecked()) check7.toggle();
        if (check8.isChecked()) check8.toggle();

        rb1.setChecked(true);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        rb5.setChecked(true);
        rb6.setChecked(false);
        rb7.setChecked(false);
        rb8.setChecked(false);

        spinner.setSelection(0,true);

        //reset all image to default
        for (int i = 0; i < 14; i++){
            images[i].setImageDrawable(getResources().getDrawable(R.drawable.idefault));
        }

        display.setText("0番");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
