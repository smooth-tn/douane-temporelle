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
 * النشاط الأول: نموذج التسجيل.
 * يجمع بيانات المسافر ثم يرسلها إلى النشاط الثاني عبر Intent صريح.
 */
public class MainActivity extends AppCompatActivity {

    // عناصر الواجهة
    private EditText edtNomYh;
    private EditText edtNaissanceYh;
    private EditText edtDestinationYh;
    private RadioGroup rgMotifYh;
    private Button btnInspecterYh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر بالواجهة
        edtNomYh=findViewById(R.id.edt_nom_yh);
        edtNaissanceYh=findViewById(R.id.edt_naissance_yh);
        edtDestinationYh=findViewById(R.id.edt_destination_yh);
        rgMotifYh=findViewById(R.id.rg_motif_yh);
        btnInspecterYh = findViewById(R.id.btn_inspecter_yh);

        // عند الضغط على زر "فحص الملف"
        btnInspecterYh.setOnClickListener(v -> inspecterProfil());
    }

    /** التحقق من البيانات المدخلة ثم الانتقال إلى النشاط الثاني */
    private void inspecterProfil() {
        String nom=edtNomYh.getText().toString().trim();
        String txtNaissance=edtNaissanceYh.getText().toString().trim();
        String txtDestination=edtDestinationYh.getText().toString().trim();

        // التحقق من الاسم
        if (nom.isEmpty()) {
            edtNomYh.setError(getString(R.string.err_nom));
            return;
        }

        // التحقق من السنوات (يجب أن تكون أرقاما صحيحة)
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

        // التحقق من اختيار سبب السفر
        int idChoisi=rgMotifYh.getCheckedRadioButtonId();
        if (idChoisi==-1) {
            Toast.makeText(this, R.string.err_motif, Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbChoisi=findViewById(idChoisi);
        String motif=rbChoisi.getText().toString();

        // إنشاء Intent صريح نحو النشاط الثاني وإرفاق البيانات بمفاتيح مبنية بالدوال الثابتة
        Intent intent=new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleAnneeDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);
        startActivity(intent);
    }
}