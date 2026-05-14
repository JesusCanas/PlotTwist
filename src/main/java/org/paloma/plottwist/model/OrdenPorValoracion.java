package org.paloma.plottwist.model;

import java.util.Comparator;

/**
 * Clase que implementa un comparador para ordenar metrajes por valoración.
 * Permite ordenar películas y series de forma ascendente según su puntuación.
 * 
 * @author MiguelSg77
 * @version 1.0
 */
public class OrdenPorValoracion implements Comparator<Metraje> {
    
    /**
     * Compara dos metrajes por su valoración en orden ascendente.
     * 
     * @param m1 Primer metraje a comparar
     * @param m2 Segundo metraje a comparar
     * @return Valor negativo si m1 tiene menor valoración, positivo si tiene mayor, 0 si tienen igual valoración
     */
    @Override
    public int compare(Metraje m1, Metraje m2) {
        return Double.compare(m1.getValoracion(), m2.getValoracion());
    }
}