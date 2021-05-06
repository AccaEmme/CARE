package com.ranauro.persistence;

import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;

import java.util.List;

public interface DataManager {
    void createDB();
    void dropDB();
    void addSacca(Sacca s);
    List<Sacca> getSacche(GruppoSanguigno g);

    void addItemsToSacche(int i);
}
