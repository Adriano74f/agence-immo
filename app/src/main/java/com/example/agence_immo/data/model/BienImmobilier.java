package com.example.agence_immo.data.model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class BienImmobilier {

    public static final String TYPE_APPARTEMENT = "Appartement";
    public static final String TYPE_MAISON = "Maison";
    private String id;
    private String type;
    private String rue;
    private String ville;
    private String codePostal;
    private List<Piece> pieces;

    public BienImmobilier(String type, String rue, String ville, String codePostal) {
        this.type = type;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void ajouterPiece(Piece piece) {
        pieces.add(piece);
    }
    public double surfaceHabitable() {
        double sum = 0.0;
        for (Piece p : pieces) {
            if (p.isSurfaceHabitable()) {
                sum += p.surface();
            }
        }
        return sum;
    }


    public double surfaceNonHabitable() {
        double sum = 0.0;
        for (Piece p : pieces) {
            if (!p.isSurfaceHabitable()) {
                sum += p.surface();
            }
        }
        return sum;
    }


    private String toStringPieces() {
        StringBuilder sb = new StringBuilder();
        for (Piece p : pieces) sb.append(p.toString());
        return sb.toString();
    }


    @Override
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return type + " " + id +
                "\nLocalisation : " + rue + " " + codePostal + " " + ville +
                "\n \n Description du bien : \n" +
                toStringPieces() +
                "\nPour une surface habitable de : " + numberFormat.format(surfaceHabitable())
                + " m2 et une surface non habitable de : " + numberFormat.format(surfaceNonHabitable()) + " m2.";
    }
}
}
