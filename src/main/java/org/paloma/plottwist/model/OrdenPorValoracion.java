package org.paloma.plottwist.model;

import java.util.Comparator;

public class OrdenPorValoracion implements Comparator<Metraje> {
    @Override
    public int compare(Metraje m1, Metraje m2) {
        return Double.compare(m1.getValoracion(), m2.getValoracion());
    }
}