package com.example.douanetemporelle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * activity 3 :resulat
 * background khadhera ken te9bel ou 7amra ken te9belsh.
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        View racine=findViewById(R.id.root_resultat_yh);
        TextView tvMessageYh=findViewById(R.id.tv_message_yh);
        Button btnNouveauYh=findViewById(R.id.btn_nouveau_yh);

        // tekhou le resultat be intent
        Intent intent=getIntent();
        boolean approuve=intent.getBooleanExtra(Cles.cleVerdict(Cles.MATRICULE), false);
        String nom=intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));

        // houni win tetbadel el background 7amra wala khadhra
        if (approuve) {
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvMessageYh.setText(getString(R.string.msg_approuve, nom));
        } else {
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvMessageYh.setText(getString(R.string.msg_refuse, nom));
        }

        // tokhrej mel interface hethy ou tarja3 lel loula
        btnNouveauYh.setOnClickListener(v -> {
            Intent retour=new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(retour);
            finish();
        });
    }
}