package com.example.agence_immo.ui.bienimmobilier;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.agence_immo.R;
import com.example.agence_immo.data.model.*;
import com.example.agence_immo.viewmodel.BienViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class bienAddPieceFragment extends Fragment {

    private BienViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bien_add_piece, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(BienViewModel.class);

        Spinner spinnerForme = view.findViewById(R.id.spinnerForme);
        Spinner spinnerTypePiece = view.findViewById(R.id.spinnerTypePiece);
        EditText etNiveau = view.findViewById(R.id.etNiveau);
        EditText etLongueur = view.findViewById(R.id.etLongueur);
        EditText etLargeur = view.findViewById(R.id.etLargeur);
        EditText etRayon = view.findViewById(R.id.etRayon);
        EditText etBase = view.findViewById(R.id.etBase);
        EditText etHauteur = view.findViewById(R.id.etHauteur);
        Button btnAdd = view.findViewById(R.id.btnAddPiece);

        // Spinner Forme
        ArrayAdapter<String> formesAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Quadrilatere", "Circulaire", "Triangulaire"});
        formesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForme.setAdapter(formesAdapter);

        // Création de la liste des TypePiece
        List<TypePiece> types = new ArrayList<>();
        types.add(new TypePiece(TypePiece.CUISINE, true, true));
        types.add(new TypePiece(TypePiece.CHAMBRE, true, true));
        types.add(new TypePiece(TypePiece.SALON, true, true));
        types.add(new TypePiece(TypePiece.SALLE_DE_BAIN, false, true));
        types.add(new TypePiece(TypePiece.WC, false, true));
        types.add(new TypePiece(TypePiece.GARAGE, false, true));
        types.add(new TypePiece(TypePiece.CAVE, false, true));

        ArrayAdapter<TypePiece> typeAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypePiece.setAdapter(typeAdapter);

        // Affichage dynamique des champs selon la forme
        spinnerForme.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String forme = (String) parent.getItemAtPosition(position);
                etLongueur.setVisibility(View.GONE);
                etLargeur.setVisibility(View.GONE);
                etRayon.setVisibility(View.GONE);
                etBase.setVisibility(View.GONE);
                etHauteur.setVisibility(View.GONE);

                switch (forme) {
                    case "Quadrilatere":
                        etLongueur.setVisibility(View.VISIBLE);
                        etLargeur.setVisibility(View.VISIBLE);
                        break;
                    case "Circulaire":
                        etRayon.setVisibility(View.VISIBLE);
                        break;
                    case "Triangulaire":
                        etBase.setVisibility(View.VISIBLE);
                        etHauteur.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        /*  String bienId.fromBundle(getArguments()).getBienId();

        btnAdd.setOnClickListener(v -> {
            String forme = spinnerForme.getSelectedItem().toString();
            TypePiece typePiece = (TypePiece) spinnerTypePiece.getItemAtPosition(spinnerTypePiece.getSelectedItemPosition());
            String niveau = etNiveau.getText().toString().trim();

            Piece piece = null;

            try {
                switch (forme) {
                    case "Quadrilatere":
                        if (TextUtils.isEmpty(etLongueur.getText()) || TextUtils.isEmpty(etLargeur.getText())) return;
                        double longueur = Double.parseDouble(etLongueur.getText().toString());
                        double largeur = Double.parseDouble(etLargeur.getText().toString());
                        piece = new PieceQuadrilatere(typePiece, niveau, longueur, largeur);
                        break;
                    case "Circulaire":
                        if (TextUtils.isEmpty(etRayon.getText())) return;
                        double rayon = Double.parseDouble(etRayon.getText().toString());
                        piece = new PieceCirculaire(typePiece, niveau, rayon);
                        break;
                    case "Triangulaire":
                        if (TextUtils.isEmpty(etBase.getText()) || TextUtils.isEmpty(etHauteur.getText())) return;
                        double base = Double.parseDouble(etBase.getText().toString());
                        double hauteur = Double.parseDouble(etHauteur.getText().toString());
                        piece = new PieceTriangulaire(typePiece, niveau, base, hauteur);
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            if (piece != null) {
                viewModel.addPiece(bienId, piece);
                Navigation.findNavController(v).navigateUp();
            }
        });*/
    }
}
