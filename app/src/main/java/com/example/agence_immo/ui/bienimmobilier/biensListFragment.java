package com.example.agence_immo.ui.bienimmobilier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agence_immo.R;
import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.viewmodel.BienViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;

@AndroidEntryPoint
public class BienListFragment extends Fragment {

    private final BienViewModel viewModel by viewModels(); // Hilt fournit automatiquement

    private RecyclerView recyclerView;
    private BienAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bien_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewBiens);
        FloatingActionButton fab = root.findViewById(R.id.fabAddBien);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BienAdapter();
        recyclerView.setAdapter(adapter);

        // Observe les biens
        viewModel.getBiens().observe(getViewLifecycleOwner(), new Observer<List<BienImmobilier>>() {
            @Override
            public void onChanged(List<BienImmobilier> biens) {
                adapter.setBiens(biens);
            }
        });

        // Click sur FAB -> créer bien
        fab.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_bienListFragment_to_bienCreateFragment));

        return root;
    }
}
