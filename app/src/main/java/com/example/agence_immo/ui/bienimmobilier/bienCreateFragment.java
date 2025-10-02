package com.example.agence_immo.ui.bienimmobilier;

import android.os.Bundle;
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
import androidx.navigation.fragment.NavHostFragment;


import com.example.agence_immo.R;
import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.viewmodel.BienViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class bienCreateFragment extends Fragment {

    private BienViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bien_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);

        vm = new ViewModelProvider(requireActivity()).get(BienViewModel.class);

        EditText etRue = v.findViewById(R.id.etRue);
        EditText etVille = v.findViewById(R.id.etVille);
        EditText etCode = v.findViewById(R.id.etCodePostal);
        Spinner spinnerType = v.findViewById(R.id.spinnerType);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                new String[]{BienImmobilier.TYPE_APPARTEMENT, BienImmobilier.TYPE_MAISON});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        Button btnAdd = v.findViewById(R.id.btnAddBien);
        btnAdd.setOnClickListener(view -> {
            String rue = etRue.getText().toString().trim();
            String ville = etVille.getText().toString().trim();
            String code = etCode.getText().toString().trim();
            String type = spinnerType.getSelectedItem().toString();

            if (rue.isEmpty()) { etRue.setError("Requis"); return; }
            if (ville.isEmpty()) { etVille.setError("Requis"); return; }
            if (code.isEmpty()) { etCode.setError("Requis"); return; }

            BienImmobilier bien = new BienImmobilier(type, rue, ville, code);
            vm.addBien(bien);

            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}
