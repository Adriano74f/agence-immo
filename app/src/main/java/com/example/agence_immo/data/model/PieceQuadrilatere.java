package com.example.agence_immo.data.model;

public class PieceQuadrilatere extends Piece {
    private double longueur;
    private double largeur;

    public PieceQuadrilatere(TypePiece typePiece, String niveau, double longueur, double largeur) {
        super(typePiece, niveau);
        this.longueur = longueur;
        this.largeur = largeur;
    }
    public double getLargeur() {
        return largeur;
    }
    public double getLongueur() {
        return longueur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    @Override
    public double surface() {
        return longueur * largeur;
    }
}