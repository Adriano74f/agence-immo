package com.example.agence_immo.data.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Piece {
    private TypePiece typePiece;
    private String niveau;

    public Piece(TypePiece typePiece, String niveau) {
        this.typePiece = typePiece;
        this.niveau = niveau;
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    public String getNiveau() {
        return niveau;
    }
    // Appeler la méthode fournie par TypePiece pour savoir si c'est habitable.
    public boolean isSurfaceHabitable() {
// Exemple : si TypePiece fournit isSurfaceHabitable()
// return typePiece.isSurfaceHabitable();
// Si la méthode s'appelle getSurfaceHabitable(), adaptez.
        try {
            return typePiece.isSurfaceHabitable();
        } catch (NoSuchMethodError e) {
// Si TypePiece n'a pas isSurfaceHabitable(), l'étudiant doit remplacer par la bonne méthode
            throw new RuntimeException("Adaptez isSurfaceHabitable() selon TypePiece fourni.");
        }
    }
    public abstract double surface();
    @Override
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.FRANCE));
        return "- " + typePiece.toString() + " surface : " + numberFormat.format(surface()) + " m2\n";
    }
}
