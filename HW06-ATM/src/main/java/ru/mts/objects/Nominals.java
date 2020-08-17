package ru.mts.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nominals {
    private static List<Integer> nominals = new ArrayList<Integer>();

    public Nominals() {
        for (Nominal nominal: Nominal.values()) {
            if (!nominals.contains(nominal.index) && nominal.index != 0) {
                nominals.add(nominal.index);
            }
        }
        Collections.sort(nominals);
    }

    public static List<Integer> getNominals() {
        return nominals;
    }

    private enum Nominal {
        n100 (100), n200 (200), n500(500), n1000(1000), n2000(2000), n5000(5000);
        private final int index;
        Nominal(int index) {  this.index = index;  }
        public int index() {  return index;  }
    }
}
