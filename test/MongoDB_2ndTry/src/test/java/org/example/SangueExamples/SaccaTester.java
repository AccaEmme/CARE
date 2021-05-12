package org.example.SangueExamples;

import com.ranauro.blood.BloodGroup;
import com.ranauro.blood.SaccaOLD;

public class SaccaTester {
    public static void main(String[] args) {
        BloodGroup[] gruppiSanguigni = BloodGroup.values();

        SaccaOLD saccaOLD = new SaccaOLD(gruppiSanguigni[0]);
        System.out.println(saccaOLD);
    }
}
