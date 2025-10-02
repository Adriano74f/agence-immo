package com.example.agence_immo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agence_immo.data.model.BienImmobilier;
import com.example.agence_immo.data.model.Piece;
import com.example.agence_immo.data.repository.BienRepository;

import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BienViewModel extends ViewModel {
    private final BienRepository repository;
    private final MutableLiveData<List<BienImmobilier>> biensLiveData = new MutableLiveData<>();

    @Inject
    public BienViewModel(BienRepository repository) {
        this.repository = repository;
        biensLiveData.setValue(repository.getBiens());
    }

    public LiveData<List<BienImmobilier>> getBiens() {
        return biensLiveData;
    }

    public void addBien(BienImmobilier bien) {
        repository.addBien(bien);
        biensLiveData.setValue(repository.getBiens());
    }

    public void addPiece(String bienId, Piece piece) {
        BienImmobilier bien = repository.getBienById(bienId);
        if (bien != null) {
            bien.ajouterPiece(piece);
            biensLiveData.setValue(repository.getBiens());
        }
    }

    public BienImmobilier getBien(String id) {
        return repository.getBienById(id);
    }
}
