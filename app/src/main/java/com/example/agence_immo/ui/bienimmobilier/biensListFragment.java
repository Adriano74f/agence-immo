package com.example.agence_immo.ui.bienimmobilier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.agence_immo.R;
import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.viewmodel.BienViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class biensListFragment extends Fragment {

    private BienViewModel viewModel;
    private LinearLayout containerBiens;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_biens_list, container, false);
        containerBiens = root.findViewById(R.id.containerBiens);
        FloatingActionButton fab = root.findViewById(R.id.fabAddBien);

        fab.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_bienListFragment_to_bienCreateFragment)
        );

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(BienViewModel.class);

        viewModel.getBiens().observe(getViewLifecycleOwner(), biens -> updateUI(biens));
    }

    private void updateUI(List<BienImmobilier> biens) {
        containerBiens.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (BienImmobilier bien : biens) {
            TextView tv = new TextView(getContext());
            tv.setText(bien.toString());
            tv.setPadding(16,16,16,16);
            tv.setOnClickListener(v -> {
                bienListFragmentDirections.ActionBienListFragmentToBienDetailFragment action =
                        bienListFragmentDirections.actionBienListFragmentToBienDetailFragment(bien.getId());
                Navigation.findNavController(v).navigate(action);
            });
            containerBiens.addView(tv);
        }
    }
}
