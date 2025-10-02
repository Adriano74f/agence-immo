package com.example.agence_immo.data.model;

public class PieceTriangulaire extends Piece {
    private double base;
    private double hauteur;

    public PieceTriangulaire(TypePiece typePiece, String niveau, double base, double hauteur) {
        super(typePiece, niveau);
        this.base = base;
        this.hauteur = hauteur;
    }
    public double getBase() {
        return base;
    }
    public double getHauteur() {
        return hauteur;
    }
    public void setBase(double base) {
        this.base = base;
    }
    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }
    @Override
    public double surface() {
        return (base * hauteur) / 2;
    }
}
