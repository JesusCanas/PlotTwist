// OrdenPorFecha.java
package org.paloma.plottwist.model;

import java.util.Comparator;

/**
 * Clase que implementa un comparador para ordenar metrajes por año de estreno.
 * Permite ordenar películas y series de forma ascendente según el año en que se estrenaron.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
public class OrdenPorFecha implements Comparator<Metraje> {
    
    /**
     * Compara dos metrajes por su año de estreno en orden ascendente.
     * 
     * @param m1 Primer metraje a comparar
     * @param m2 Segundo metraje a comparar
     * @return Valor negativo si m1 es más antiguo, positivo si es más reciente, 0 si son del mismo año
     */
    @Override
    public int compare(Metraje m1, Metraje m2) {
        return Integer.compare(m1.getAnyo(), m2.getAnyo());
    }
}