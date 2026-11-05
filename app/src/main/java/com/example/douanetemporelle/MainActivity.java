package com.example.douanetemporelle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * quest louwel l'activite main
 * tekhou donnee ta3 el utilisateur toub3athehom lel activite 2
 */
public class MainActivity extends AppCompatActivity {

    // les elements ta3 l'interface
    private EditText edtNomYh;
    private EditText edtNaissanceYh;
    private EditText edtDestinationYh;
    private RadioGroup rgMotifYh;
    private Button btnInspecterYh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ids mta3 les elements
        edtNomYh=findViewById(R.id.edt_nom_yh);
        edtNaissanceYh=findViewById(R.id.edt_naissance_yh);
        edtDestinationYh=findViewById(R.id.edt_destination_yh);
        rgMotifYh=findViewById(R.id.rg_motif_yh);
        btnInspecterYh = findViewById(R.id.btn_inspecter_yh);

        
        btnInspecterYh.setOnClickListener(v -> inspecterProfil());
    }

    /** verification ta3 les donnes ou ba3ed yetba3thou le activity 2*/
    private void inspecterProfil() {
        String nom=edtNomYh.getText().toString().trim();
        String txtNaissance=edtNaissanceYh.getText().toString().trim();
        String txtDestination=edtDestinationYh.getText().toString().trim();

        // verification mta3 el esm
        if (nom.isEmpty()) {
            edtNomYh.setError(getString(R.string.err_nom));
            return;
        }

        
        int naissance;
        int destination;
        try {
            naissance=Integer.parseInt(txtNaissance);
        } catch (NumberFormatException e) {
            edtNaissanceYh.setError(getString(R.string.err_annee));
            return;
        }
        try {
            destination=Integer.parseInt(txtDestination);
        } catch (NumberFormatException e) {
            edtDestinationYh.setError(getString(R.string.err_annee));
            return;
        }

        // verification mta3 desti
        int idChoisi=rgMotifYh.getCheckedRadioButtonId();
        if (idChoisi==-1) {
            Toast.makeText(this, R.string.err_motif, Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbChoisi=findViewById(idChoisi);
        String motif=rbChoisi.getText().toString();

        // declary intent jdida ou b3athet m3aha les donnes
        Intent intent=new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleAnneeDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);
        startActivity(intent);
    }
}