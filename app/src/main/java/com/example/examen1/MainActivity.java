package com.example.examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String distance,performance,prix;
    double distanceFait,performanceAuto,prixGaz, coût;
    RadioGroup radioGroup;
    String input = "";
    Integer typeEssence = 0;
    Button button;
    TextView textView, dateText;
    EditText editTextDistance, editTextPerformance, editTextPrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDistance = (EditText) findViewById(R.id.editTextDistance);
        editTextPerformance = (EditText) findViewById(R.id.editTextPerformance);
        editTextPrix = (EditText) findViewById(R.id.editTextPrix);
        textView = (TextView) findViewById(R.id.textView);
        dateText = (TextView) findViewById(R.id.date);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        button = (Button) findViewById(R.id.button);
        setDate(dateText);

        Context context = getApplicationContext();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.dieselBtn:
                        typeEssence = 1;
                        break;
                    case R.id.gazBtn:
                        typeEssence = 2;
                        break;
                    default:
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                distance = editTextDistance.getText().toString();
                performance = editTextPerformance.getText().toString();
                prix = editTextPrix.getText().toString();

                if(typeEssence == 0) {
                    Toast.makeText(context, "Le type d'essence", Toast.LENGTH_LONG).show(); }
                else if(distance.isEmpty() || performance.isEmpty() || prix.isEmpty()){
                    Toast.makeText(context, "Champs manquant!", Toast.LENGTH_LONG).show(); }
                else {
                    distanceFait = Double.parseDouble(distance);
                    performanceAuto = Double.parseDouble(performance);
                    prixGaz = Double.parseDouble(prix);

                    if(typeEssence == 1){
                        prixGaz = prixGaz + 0.25; }

                    else if(typeEssence == 2) {
                        prixGaz = prixGaz + 0.15; }
                    coût = distanceFait * performanceAuto / 100 * prixGaz;

                    textView.setText(String.format ("%.2f",coût) + "$");
                }
            }
        });


    }

    public void setDate(TextView view) {
        Date today = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
        String date = formatter.format(today);
        view.setText(date);
    }
}