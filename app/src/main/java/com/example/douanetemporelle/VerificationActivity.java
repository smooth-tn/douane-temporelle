package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * act 2 confirmation ta3 el voyage
 * 
 */
public class VerificationActivity extends AppCompatActivity {

    private TextView tvNomYh;
    private TextView tvAgeYh;
    private TextView tvMotifYh;
    private TextView tvAlerteYh;
    private Button btnApprouverYh;
    private Button btnRefuserYh;

    // ism el mousefer
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        
        tvNomYh=findViewById(R.id.tv_nom_yh);
        tvAgeYh=findViewById(R.id.tv_age_yh);
        tvMotifYh=findViewById(R.id.tv_motif_yh);
        tvAlerteYh=findViewById(R.id.tv_alerte_yh);
        btnApprouverYh=findViewById(R.id.btn_approuver_yh);
        btnRefuserYh=findViewById(R.id.btn_refuser_yh);

        // houni nekhou les donnes elly jew mel act 1
        Intent intent=getIntent();
        nom =intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance= intent.getIntExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), 0);
        int destination=intent.getIntExtra(Cles.cleAnneeDestination(Cles.MATRICULE), 0);
        String motif =intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));

        
        int difference = Math.abs(destination - naissance);

        // output ta3 resultat
        tvNomYh.setText(getString(R.string.fmt_nom, nom));
        tvAgeYh.setText(getString(R.string.fmt_age, difference));
        tvMotifYh.setText(getString(R.string.fmt_motif, motif));

        // modification ta3 wa9et el voyage
        boolean modificationPasse = getString(R.string.motif_modification).equals(motif);
        if (modificationPasse && estPremier(difference)) {
            btnApprouverYh.setEnabled(false);
            tvAlerteYh.setVisibility(View.VISIBLE);
        }

        // true ya3ni accepta
        btnApprouverYh.setOnClickListener(v -> lancerVerdict(true));
        // false => refuser
        btnRefuserYh.setOnClickListener(v -> lancerVerdict(false));
    }

    /** intent jedida besh nemshiw lel act 3*/
    private void lancerVerdict(boolean approuve) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), approuve);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }

    /** func besh na3refou el nbr premier wala lee */
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}