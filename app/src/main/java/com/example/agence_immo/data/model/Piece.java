package com.example.agence_immo.data.model;

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
// Format demandé : <typePiece> surface : <surface()> m2 \n
        return typePiece.toString() + " surface : " + surface() + " m2\n";
    }
}
