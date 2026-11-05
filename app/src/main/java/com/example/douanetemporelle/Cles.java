package com.example.douanetemporelle;

public final class Cles {

    // un exemple ta3 matricule 
    public static final String MATRICULE= "1234";

    // منع إنشاء كائنات من هذه الفئة
    private Cles() {
    }

    // tekhou matricule ka input besh tasn3 cles
    private static String construire(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // tasna3 el cles
    public static String cleNom(String matricule) {
        return construire(matricule, "NOM_VOYAGEUR");
    }

    // cles mta3 anne naissance
    public static String cleAnneeNaissance(String matricule) {
        return construire(matricule, "ANNEE_NAISSANCE");
    }

    // cles ta3 desti
    public static String cleAnneeDestination(String matricule) {
        return construire(matricule, "ANNEE_DESTINATION");
    }

    // cles motif
    public static String cleMotif(String matricule) {
        return construire(matricule, "MOTIF");
    }

    // cles final
    public static String cleVerdict(String matricule) {
        return construire(matricule, "VERDICT");
    }
}