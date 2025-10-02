package com.example.agence_immo.ui.bienimmobilier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.agence_immo.R;
import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.viewmodel.BienViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class bienDetailFragment extends Fragment {

    private BienViewModel viewModel;
    private BienImmobilier bien;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bien_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(BienViewModel.class);

        String bienId = BienDetailFragmentArgs.fromBundle(getArguments()).getBienId();
        bien = viewModel.getBien(bienId);

        TextView tvDetail = view.findViewById(R.id.tvBienDetail);
        tvDetail.setText(bien.toString());

        Button btnAddPiece = view.findViewById(R.id.btnAddPiece);
        btnAddPiece.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(BienDetailFragmentDirections.actionBienDetailFragmentToBienAddPiece(bien.getId()))
        );
    }
}
