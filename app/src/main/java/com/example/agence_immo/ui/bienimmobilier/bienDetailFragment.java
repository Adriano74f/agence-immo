package com.example.agence_immo.ui.bienimmobilier;

import android.app.BroadcastOptions;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.fragment.navArgs;


import com.example.agence_immo.R;
import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.viewmodel.BienViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BienDetailFragment extends Fragment {

    private BienViewModel vm;
    private BienImmobilier bien;

    private final BienDetailFragmentArgs args = BienDetailFragmentArgs.fromBundle(new Bundle());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bien_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);

        vm = new ViewModelProvider(requireActivity()).get(BienViewModel.class);

        String bienId = BienDetailFragmentArgs.fromBundle(getArguments()).getBienId();
        bien = vm.getBien(bienId);

        TextView tvDetail = v.findViewById(R.id.tvBienDetail);
        tvDetail.setText(bien.toString());

        Button btnAddPiece = v.findViewById(R.id.btnAddPiece);
        btnAddPiece.setOnClickListener(button ->
                NavHostFragment.findNavController(this)
                        .navigate(BienDetailFragmentDirections
                                .actionBienDetailToBienAddPiece(bien.getId())));
    }
}
