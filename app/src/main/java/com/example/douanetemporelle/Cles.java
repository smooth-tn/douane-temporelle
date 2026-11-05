package com.example.douanetemporelle;

public final class Cles {

    // آخر 4 أرقام من بطاقة الطالب لأحد أعضاء المجموعة (غيّرها برقمك الحقيقي)
    public static final String MATRICULE= "1234";

    // منع إنشاء كائنات من هذه الفئة
    private Cles() {
    }

    // الدالة الأساسية: تبني المفتاح من رقم المطابقة + اللاحقة
    private static String construire(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // مفتاح اسم المسافر  ->  مثال: 1234_NOM_VOYAGEUR
    public static String cleNom(String matricule) {
        return construire(matricule, "NOM_VOYAGEUR");
    }

    // مفتاح سنة الميلاد
    public static String cleAnneeNaissance(String matricule) {
        return construire(matricule, "ANNEE_NAISSANCE");
    }

    // مفتاح سنة الوجهة
    public static String cleAnneeDestination(String matricule) {
        return construire(matricule, "ANNEE_DESTINATION");
    }

    // مفتاح سبب السفر
    public static String cleMotif(String matricule) {
        return construire(matricule, "MOTIF");
    }

    // مفتاح الحكم النهائي (قيمة منطقية)
    public static String cleVerdict(String matricule) {
        return construire(matricule, "VERDICT");
    }
}