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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 'Samuel José Eugênio - https://github.com/samuelgenio'
 */
public class Pizza {

    /**
     * Nome da pizza.
     */
    private String nome;

    /**
     * Ingredientes já obtidos.
     */
    private List<Ingredient> ingredientes = new LinkedList<>();

    /**
     * Ingredientes necessários.
     */
    private List<Ingredient> ingredientesNecessarios = new ArrayList<>();

    public Pizza() {
    }

    public Pizza(String nome) {
        this.nome = nome;
    }

    /**
     * Usado na clonagem da pizza
     *
     * @param nome Nome da pizza
     * @param ingredientesNecessarios Ingredientes necessário para produzir a
     * pizza
     */
    private Pizza(String nome, List<Ingredient> ingredientesNecessarios) {
        this.nome = nome;
        this.ingredientesNecessarios = ingredientesNecessarios;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the ingredientes
     */
    public List<Ingredient> getIngredientes() {
        return ingredientes;
    }

    /**
     * @param ingredientes the ingredientes to set
     */
    public void setIngredientes(List<Ingredient> ingredientes) {
        this.ingredientes = ingredientes;
    }

    /**
     * @return the ingredientesNecessarios
     */
    public List<Ingredient> getIngredientesNecessarios() {
        return ingredientesNecessarios;
    }

    /**
     * @param ingredientesNecessarios the ingredientesNecessarios to set
     */
    public void setIngredientesNecessarios(List<Ingredient> ingredientesNecessarios) {
        this.ingredientesNecessarios = ingredientesNecessarios;
    }

    /**
     * Adiciona ingrediente necessario
     *
     * @param ingrediente Ingrediente necessário para construção da pizza
     */
    public void addIngredienteNecessario(Ingredient ingrediente) {
        this.ingredientesNecessarios.add(ingrediente);
    }

    /**
     * Adiciona ingrediente faltante à pizza
     *
     * @param ingrediente Ingrediente faltante à pizza
     */
    public void addIngrediente(Ingredient ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public Pizza clone() {
        return new Pizza(this.nome, this.ingredientesNecessarios);
    }

    /**
     * Remove ingrediente aleatório da pizza do representante
     *
     * @return ingredient.toString()
     */
    public String removeIngredient() {

        String retorno = "";

        if (ingredientes.size() != 0) {

            byte index = (byte) (Math.random() * ingredientes.size());

            retorno = "Ingrediente " + ingredientes.get(index).toString() + " removido";

            ingredientes.remove(index);

        } else {
            retorno = "sem ingredientes";
        }

        return retorno;

    }

    /**
     * Verifica se o player já possui o ingrediente
     *
     * @param ingredient Ingrediente a ser verificado
     * @return True caso possua, false caso ainda não tenha o ingrediente
     */
    public byte isIngredient(Ingredient ingredient) {

        byte retorno = -1;

        for (Ingredient ingre : ingredientesNecessarios) {
            if (ingre == ingredient) {
                retorno = 0;
                break;
            }
        }

        //veririca se o player já possui o ingrediente
        for (Ingredient ingred : ingredientes) {
            if (ingred == ingredient) {
                retorno = 1;
                break;
            }
        }

        return retorno;

    }

    public String getObtainedIngredients() {

        StringBuilder retorno = new StringBuilder();
        boolean existe;

        for (Ingredient ingrediente : this.getIngredientesNecessarios()) {
            existe = false;

            for (Ingredient ingre : this.getIngredientes()) {
                if (ingrediente.equals(ingre)) {
                    retorno.append(ingre.getNome() + " - X \n");
                    existe = true;
                    break;
                }

            }
            if (!existe) {
                retorno.append(ingrediente.getNome() + " \n");
            }

        }
        return retorno.toString();
    }

}
