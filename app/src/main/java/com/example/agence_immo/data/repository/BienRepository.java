package com.example.agence_immo.data.repository;

import com.example.agence_immo.data.model.BienImmobilier;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BienRepository {
    private final List<BienImmobilier> biens = new ArrayList<>();

    @Inject
    public BienRepository() {} // Hilt injecte automatiquement

    public List<BienImmobilier> getBiens() {
        return biens;
    }

    public void addBien(BienImmobilier bien) {
        biens.add(bien);
    }

    public BienImmobilier getBienById(String id) {
        for (BienImmobilier b : biens) {
            if (b.getId().equals(id)) return b;
        }
        return null;
    }
}
