/*
 * Copyright (C) 2020 samue
 *
 * This file is part of PizzariaMaluca.
 * PizzariaMaluca is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * PizzariaMaluca is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>
 */
package br.com.samuka.pizzariamaluca.model;

/**
 *
 * @author 'Samuel José Eugênio - https://github.com/samuelgenio'
 */
public class Cedula {

    /**
     * Define se é uma posição para retirar uma carta de sorte ou azar. Caso
     * verdadeiro o elemento Ingredient deverá ser nulo.
     */
    private boolean flgSorteAzar;

    /**
     * Elemento da posição atual.
     */
    private Ingredient ingredient;

    /**
     * Proxima posição da Lista.
     */
    private Cedula proximo;

    public Cedula(boolean sorteAzar, Ingredient ingredient) {
        this.flgSorteAzar = sorteAzar;
        this.ingredient = ingredient;
    }

    public boolean isFlgSorteAzar() {
        return flgSorteAzar;
    }

    public void setFlgSorteAzar(boolean flgSorteAzar) {
        this.flgSorteAzar = flgSorteAzar;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Cedula getProximo() {
        return proximo;
    }

    public void setProximo(Cedula proximo) {
        this.proximo = proximo;
    }

}
