package com.ranauro.blood;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
public enum BloodGroup {
    Ap, Am, Bp, Bm, ZEROp, ZEROm, ABp, ABm;

    @SuppressWarnings("serial")
    private final static HashMap<BloodGroup, List<BloodGroup>> puoDonareA = new HashMap<BloodGroup, List<BloodGroup>>() {
        {
            put (Ap, new ArrayList<BloodGroup>(Arrays.asList(Ap,ABp)));
            put (Am, new ArrayList<BloodGroup>(Arrays.asList(Ap,Am,ABp,ABm)));
            put (Bp, new ArrayList<BloodGroup>(Arrays.asList(Bp,ABp)));
            put (Bm, new ArrayList<BloodGroup>(Arrays.asList(Bp,Bm,ABp,ABm)));
            put (ZEROp, new ArrayList<BloodGroup>(Arrays.asList(ZEROp,Ap,Bp,ABp)));
            put (ZEROm, new ArrayList<BloodGroup>(Arrays.asList(Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm)));
            put (ABp, new ArrayList<BloodGroup>(Arrays.asList(ABp)));
            put (ABm, new ArrayList<BloodGroup>(Arrays.asList(ABp,ABm)));
        }
    };

    @SuppressWarnings("serial")
    private final static HashMap<BloodGroup, List<BloodGroup>> puoRicevereDa = new HashMap<BloodGroup, List<BloodGroup>>() {
        {
            put (Ap, new ArrayList<BloodGroup>(Arrays.asList(Ap,Am,ZEROp,ZEROm)));
            put (Am, new ArrayList<BloodGroup>(Arrays.asList(Am,ZEROm)));
            put (Bp, new ArrayList<BloodGroup>(Arrays.asList(Bp,Bm,ZEROp,ZEROm)));
            put (Bm, new ArrayList<BloodGroup>(Arrays.asList(Bm,ZEROm)));
            put (ZEROp, new ArrayList<BloodGroup>(Arrays.asList(ZEROp,ZEROm)));
            put (ZEROm, new ArrayList<BloodGroup>(Arrays.asList(ZEROm)));
            put (ABp, new ArrayList<BloodGroup>(Arrays.asList(Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm)));
            put (ABm, new ArrayList<BloodGroup>(Arrays.asList(ABm,Am,Bm,ZEROm)));
        }
    };

    /*
     * @pre
     * gs not null
     */
    public static Iterator<BloodGroup> puoDonareA(BloodGroup gs) {
        assert gs !=null;
        return puoDonareA.get(gs).iterator();
    }

    /*
     * @pre
     * gs not null
     */
    public static Iterator<BloodGroup> puoRicevereDa(BloodGroup gs) {
        assert gs !=null;
        return puoRicevereDa.get(gs).iterator();
    }

}
