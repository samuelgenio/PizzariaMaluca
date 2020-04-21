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
public class Player {
    
    /**
     * Nome do jogador.
     */
    private String nome;
    
    /**
     * Pizza selecionada pelo jogador.
     */
    private Pizza pizza;
    
    /**
     * Posição atual na lista-tabuleiro
     */
    private Cedula cedulaAtual;

    
    public Player() {
    }

    /**
     * Construtor com nome do jogador.
     * @param nome Nome do jogador
     */
    public Player(String nome) {
        this.nome = nome;
    }
    
    /**
     * Construtor com jogador completo.
     * @param nome Nome do jogador
     * @param pizza Pizza selecionada pelo jogador.
     */
    public Player(String nome, Pizza pizza) {
        this.nome = nome;
        this.pizza = pizza;
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
     * @return the pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * @param pizza the pizza to set
     */
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    
    
    @Override
    public String toString() {
        return "Jogador: " + getNome() + " - Pizza: " + getPizza() + ". \n"
        		+ "Ingredientes : \n"
        		+ this.getPizza().getObtainedIngredients();
    }

	public Cedula getCedulaAtual() {
		return cedulaAtual;
	}

	public void setCedulaAtual(Cedula cedulaAtual) {
		this.cedulaAtual = cedulaAtual;
	}
    
    
    
    
}
