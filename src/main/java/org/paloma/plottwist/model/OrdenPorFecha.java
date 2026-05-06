// OrdenPorFecha.java
package org.paloma.plottwist.model;

import java.util.Comparator;

public class OrdenPorFecha implements Comparator<Metraje> {
    @Override
    public int compare(Metraje m1, Metraje m2) {
        return m1.getAnyo().compareTo(m2.getAnyo());
    }
}