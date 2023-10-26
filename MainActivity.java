package com.denizsinaci.ydyortalamahesaplama;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText cumulative1Text;
    EditText cumulative2Text;
    EditText cumulative3Text;
    EditText cumulative4Text;
    EditText cumulative5Text;
    EditText cumulative6Text;
    EditText midYearText;
    EditText endYearText;
    EditText speaking1Text;
    EditText speaking2Text;
    EditText inClass1Text;
    EditText inClass2Text;
    Button hesapla;
    RadioButton a1;
    RadioButton a2;
    RadioButton b1;
    RadioButton b1plus;
    CheckBox yeterlilik;

    public double ortalama;
    public String kur;
    public boolean hata = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public double hesapla1(){
        double sonuc1 = Double.parseDouble(cumulative1Text.getText().toString())*7/100 + Double.parseDouble(cumulative2Text.getText().toString())*7/100 +
                Double.parseDouble(cumulative3Text.getText().toString())*7/100 + Double.parseDouble(cumulative4Text.getText().toString())*7/100 + Double.parseDouble(cumulative5Text.getText().toString())*7/100 +
                Double.parseDouble(cumulative6Text.getText().toString())*7/100 + Double.parseDouble(midYearText.getText().toString())*15/100 + Double.parseDouble(endYearText.getText().toString())*15/100 +
                Double.parseDouble(speaking1Text.getText().toString())*7/100 + Double.parseDouble(speaking2Text.getText().toString())*7/100 + Double.parseDouble(inClass1Text.getText().toString())*7/100 +
                Double.parseDouble(inClass2Text.getText().toString())*7/100;
        return sonuc1;
    }

    public double hesapla2(){
        double sonuc2 = (Double.parseDouble(cumulative1Text.getText().toString())*7/100 + Double.parseDouble(cumulative2Text.getText().toString())*7/100 +
                Double.parseDouble(cumulative3Text.getText().toString())*7/100 + Double.parseDouble(midYearText.getText().toString())*15/100 +
                Double.parseDouble(speaking1Text.getText().toString())*7/100 + Double.parseDouble(inClass1Text.getText().toString())*7/100)*2;
        return sonuc2;
    }

    public void kaydet(){
        sharedPreferences = this.getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        try {
            editor.putString("kur", kur);
            editor.putFloat("cumulative1Text",Float.parseFloat(cumulative1Text.getText().toString()));
            editor.putFloat("cumulative2Text",Float.parseFloat(cumulative2Text.getText().toString()));
            editor.putFloat("cumulative3Text",Float.parseFloat(cumulative3Text.getText().toString()));
            editor.putFloat("cumulative4Text",Float.parseFloat(cumulative4Text.getText().toString()));
            editor.putFloat("cumulative5Text",Float.parseFloat(cumulative5Text.getText().toString()));
            editor.putFloat("cumulative6Text",Float.parseFloat(cumulative6Text.getText().toString()));
            editor.putFloat("midYearText",Float.parseFloat(midYearText.getText().toString()));
            editor.putFloat("endYearText",Float.parseFloat(endYearText.getText().toString()));
            editor.putFloat("speaking1Text",Float.parseFloat(speaking1Text.getText().toString()));
            editor.putFloat("speaking2Text",Float.parseFloat(speaking2Text.getText().toString()));
            editor.putFloat("inClass1Text",Float.parseFloat(inClass1Text.getText().toString()));
            editor.putFloat("inClass2Text",Float.parseFloat(inClass2Text.getText().toString()));
            editor.putFloat("ortalama",(float)ortalama);
            editor.commit();
        }catch (Exception e){
            e.printStackTrace();
            AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
            builder2.setTitle("Hata!");
            builder2.setMessage("Lütfen tabloyu uygun şekilde doldurun");
            builder2.show();
            hata = true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cumulative1Text = (EditText) findViewById(R.id.cumulative1Text);
        cumulative2Text = (EditText) findViewById(R.id.cumulative2Text);
        cumulative3Text = (EditText) findViewById(R.id.cumulative3Text);
        cumulative4Text = (EditText) findViewById(R.id.cumulative4Text);
        cumulative5Text = (EditText) findViewById(R.id.cumulative5Text);
        cumulative6Text = (EditText) findViewById(R.id.cumulative6Text);
        midYearText = (EditText) findViewById(R.id.midYearText);
        endYearText = (EditText) findViewById(R.id.endYearText);
        speaking1Text = (EditText) findViewById(R.id.speaking1Text);
        speaking2Text = (EditText) findViewById(R.id.speaking2Text);
        inClass1Text = (EditText) findViewById(R.id.inClassText);
        inClass2Text = (EditText) findViewById(R.id.inClass2Text);
        hesapla = (Button) findViewById(R.id.hesapla);
        a1 = (RadioButton) findViewById(R.id.a1RadioButton);
        a2 = (RadioButton) findViewById(R.id.a2RadioButton);
        b1 = (RadioButton) findViewById(R.id.b1RadioButton);
        b1plus = (RadioButton) findViewById(R.id.b1plusRadioButton);
        yeterlilik = (CheckBox) findViewById(R.id.yeterlilik);

        yeterlilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yeterlilik.isChecked()){
                    cumulative4Text.setEnabled(false);
                    cumulative5Text.setEnabled(false);
                    cumulative6Text.setEnabled(false);
                    speaking2Text.setEnabled(false);
                    inClass2Text.setEnabled(false);
                    endYearText.setEnabled(false);
                }else{
                    cumulative4Text.setEnabled(true);
                    cumulative5Text.setEnabled(true);
                    cumulative6Text.setEnabled(true);
                    speaking2Text.setEnabled(true);
                    inClass2Text.setEnabled(true);
                    endYearText.setEnabled(true);
                }
            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a1.isChecked()){
                    a2.setChecked(false);
                    b1.setChecked(false);
                    b1plus.setChecked(false);
                    yeterlilik.setVisibility(View.INVISIBLE);
                    kur = a1.getText().toString();
                }
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2.isChecked()){
                    a1.setChecked(false);
                    b1.setChecked(false);
                    b1plus.setChecked(false);
                    yeterlilik.setVisibility(View.VISIBLE);
                    kur = a2.getText().toString();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1.isChecked()){
                    a1.setChecked(false);
                    a2.setChecked(false);
                    b1plus.setChecked(false);
                    yeterlilik.setVisibility(View.VISIBLE);
                    kur = b1.getText().toString();
                }
            }
        });

        b1plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1plus.isChecked()){
                    a1.setChecked(false);
                    a2.setChecked(false);
                    b1.setChecked(false);
                    yeterlilik.setVisibility(View.VISIBLE);
                    kur = b1plus.getText().toString();
                }
            }
        });

        sharedPreferences = this.getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();


        try {
            if(sharedPreferences.getFloat("cumulative1Text",0)%1 == 0){
                cumulative1Text.setText("" + (int)sharedPreferences.getFloat("cumulative1Text",0));
            }else{
                cumulative1Text.setText("" + sharedPreferences.getFloat("cumulative1Text",0));
            }
            if(sharedPreferences.getFloat("cumulative2Text",0)%1 == 0){
                cumulative2Text.setText("" + (int)sharedPreferences.getFloat("cumulative2Text",0));
            }else{
                cumulative2Text.setText("" + sharedPreferences.getFloat("cumulative2Text",0));
            }
            if(sharedPreferences.getFloat("cumulative3Text",0)%1 == 0){
                cumulative3Text.setText("" + (int)sharedPreferences.getFloat("cumulative3Text",0));
            }else{
                cumulative3Text.setText("" + sharedPreferences.getFloat("cumulative3Text",0));
            }
            if(sharedPreferences.getFloat("cumulative4Text",0)%1 == 0){
                cumulative4Text.setText("" + (int)sharedPreferences.getFloat("cumulative4Text",0));
            }else{
                cumulative4Text.setText("" + sharedPreferences.getFloat("cumulative4Text",0));
            }
            if(sharedPreferences.getFloat("cumulative5Text",0)%1 == 0){
                cumulative5Text.setText("" + (int)sharedPreferences.getFloat("cumulative5Text",0));
            }else{
                cumulative5Text.setText("" + sharedPreferences.getFloat("cumulative5Text",0));
            }
            if(sharedPreferences.getFloat("cumulative6Text",0)%1 == 0){
                cumulative6Text.setText("" + (int)sharedPreferences.getFloat("cumulative6Text",0));
            }else{
                cumulative6Text.setText("" + sharedPreferences.getFloat("cumulative6Text",0));
            }
            if(sharedPreferences.getFloat("midYearText",0)%1 == 0){
                midYearText.setText("" + (int)sharedPreferences.getFloat("midYearText",0));
            }else{
                midYearText.setText("" + sharedPreferences.getFloat("midYearText",0));
            }
            if(sharedPreferences.getFloat("endYearText",0)%1 == 0){
                endYearText.setText("" + (int)sharedPreferences.getFloat("endYearText",0));
            }else{
                endYearText.setText("" + sharedPreferences.getFloat("endYearText",0));
            }
            if(sharedPreferences.getFloat("speaking1Text",0)%1 == 0){
                speaking1Text.setText("" + (int)sharedPreferences.getFloat("speaking1Text",0));
            }else{
                speaking1Text.setText("" + sharedPreferences.getFloat("speaking1Text",0));
            }
            if(sharedPreferences.getFloat("speaking2Text",0)%1 == 0){
                speaking2Text.setText("" + (int)sharedPreferences.getFloat("speaking2Text",0));
            }else{
                speaking2Text.setText("" + sharedPreferences.getFloat("speaking2Text",0));
            }
            if(sharedPreferences.getFloat("inClass1Text",0)%1 == 0){
                inClass1Text.setText("" + (int)sharedPreferences.getFloat("inClass1Text",0));
            }else{
                inClass1Text.setText("" + sharedPreferences.getFloat("inClass1Text",0));
            }
            if(sharedPreferences.getFloat("inClass2Text",0)%1 == 0){
                inClass2Text.setText("" + (int)sharedPreferences.getFloat("inClass2Text",0));
            }else{
                inClass2Text.setText("" + sharedPreferences.getFloat("inClass2Text",0));
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (a1.isChecked() || a2.isChecked() || b1.isChecked() || b1plus.isChecked()) {
                        if (yeterlilik.isChecked()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Sonuç");
                            if (a2.isChecked()) {
                                if (hesapla2() < 80) {
                                    builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla2())) + "\n" + "Yeterlilik sınavına giremezsiniz");
                                    builder.show();
                                    hata = false;
                                    ortalama = hesapla2();
                                    kaydet();
                                } else {
                                    builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla2())) + "\n" + "Yeterlilik sınavına girebilirsiniz");
                                    builder.show();
                                    hata = false;
                                    ortalama = hesapla2();
                                    kaydet();
                                }
                            } else {
                                if (hesapla2() < 60) {
                                    builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla2())) + "\n" + "Yeterlilik sınavına giremezsiniz");
                                    builder.show();
                                    hata = false;
                                    ortalama = hesapla2();
                                    kaydet();
                                } else {
                                    builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla2())) + "\n" + "Yeterlilik sınavına girebilirsiniz");
                                    builder.show();
                                    hata = false;
                                    ortalama = hesapla2();
                                    kaydet();
                                }
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Sonuç");
                            if (hesapla1() < 60) {
                                builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla1())) + "\n" + "Yeterlilik sınavına giremezsiniz");
                                builder.show();
                                hata = false;
                                ortalama = hesapla1();
                                kaydet();
                            } else {
                                builder.setMessage("Ortalamanız: " + (new DecimalFormat("##.##").format(hesapla1())) + "\n" + "Yeterlilik sınavına girebilirsiniz");
                                builder.show();
                                hata = false;
                                ortalama = hesapla1();
                                kaydet();
                            }
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Hata!");
                        builder.setMessage("Lütfen bir kur seçin");
                        builder.show();
                        hata = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Hata!");
                    builder.setMessage("Lütfen uygun not değerleri girin");
                    builder.show();
                    hata = true;
                }
            }

        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("YDY Ortalama Hesaplama v1.0");
                builder.setMessage("Deniz Sınacı \nKaynak kodlar için:\ngithub.com/denizsinaci/ydy-ortalama-hesaplama");
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


        }
        return true;
    }
}
