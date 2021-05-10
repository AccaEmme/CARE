package org.example.SangueExamples;

import com.ranauro.sangue.GruppoSanguigno;
import com.ranauro.sangue.Sacca;

public class SaccaTester {
    public static void main(String[] args) {
        GruppoSanguigno[] gruppiSanguigni = GruppoSanguigno.values();

        Sacca sacca = new Sacca(gruppiSanguigni[0]);
        System.out.println(sacca);
    }
}
