package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * النشاط الثاني: ماسح المفارقة الزمنية.
 * يعرض ملخص المسافر على شكل جواز سفر، ويسمح بالموافقة أو الرفض.
 */
public class VerificationActivity extends AppCompatActivity {

    private TextView tvNomYh;
    private TextView tvAgeYh;
    private TextView tvMotifYh;
    private TextView tvAlerteYh;
    private Button btnApprouverYh;
    private Button btnRefuserYh;

    // اسم المسافر (نحتفظ به لإرساله إلى النشاط الثالث)
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // ربط العناصر بالواجهة
        tvNomYh = findViewById(R.id.tv_nom_yh);
        tvAgeYh = findViewById(R.id.tv_age_yh);
        tvMotifYh = findViewById(R.id.tv_motif_yh);
        tvAlerteYh = findViewById(R.id.tv_alerte_yh);
        btnApprouverYh = findViewById(R.id.btn_approuver_yh);
        btnRefuserYh = findViewById(R.id.btn_refuser_yh);

        // استقبال البيانات المرسلة من النشاط الأول
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intent.getIntExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), 0);
        int destination = intent.getIntExtra(Cles.cleAnneeDestination(Cles.MATRICULE), 0);
        String motif = intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));

        // حساب "العمر الزمني" = الفرق المطلق بين سنة الوجهة وسنة الميلاد
        int difference = Math.abs(destination - naissance);

        // عرض الملخص
        tvNomYh.setText(getString(R.string.fmt_nom, nom));
        tvAgeYh.setText(getString(R.string.fmt_age, difference));
        tvMotifYh.setText(getString(R.string.fmt_motif, motif));

        // الشرط: سبب السفر "تعديل الماضي" + الفرق عدد أولي => تعطيل زر الموافقة فور تحميل النشاط
        boolean modificationPasse = getString(R.string.motif_modification).equals(motif);
        if (modificationPasse && estPremier(difference)) {
            btnApprouverYh.setEnabled(false);
            tvAlerteYh.setVisibility(View.VISIBLE);
        }

        // زر الموافقة => الحكم = true
        btnApprouverYh.setOnClickListener(v -> lancerVerdict(true));
        // زر الرفض => الحكم = false
        btnRefuserYh.setOnClickListener(v -> lancerVerdict(false));
    }

    /** الانتقال إلى النشاط الثالث مع الحكم (منطقي) واسم المسافر */
    private void lancerVerdict(boolean approuve) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), approuve);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }

    /** دالة اختبار العدد الأولي: العدد الأولي هو عدد أكبر من 1 لا يقبل القسمة إلا على 1 ونفسه */
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