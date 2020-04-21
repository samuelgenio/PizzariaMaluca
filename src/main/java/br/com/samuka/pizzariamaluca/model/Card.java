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
public final class Card {

    //posibilidade de fichas de sorte ou azar.
    public static final String AC_PERDER_INGREDIENTE = "Perder um ingrediente";
    public static final String AC_GANHAR_INGREDIENTES = "Ganhar dois ingredientes";
    public static final String AC_RETIRAR_INGREDIENTE_JOGADOR = "Retirar um ingrediente de um outro jogador";
    public static final String AC_QUEIMAR_PIZZA = "Queimar a pizza";

    /**
     * Tipo da carta
     */
    private byte type;

    /**
     * Nome da Carta
     */
    private String nome;

    /*
     * Tipo da carta se é de sorte ou azar.
     */
    private boolean tipo;
    private String descricao;

    /**
     * Variável de controle para a pilha
     */
    private Card proximo;

    public Card(String name, boolean type, String description) {

        this.setNome(name);
        this.setTipo(type);
        this.setDescricao(description);
        setType();

    }

    public String getNome() {
        return nome;
    }

    public boolean isTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Card getProximo() {
        return proximo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setProximo(Card proximo) {
        this.proximo = proximo;
    }

    public byte getType() {
        return type;
    }

    public void setType() {

        switch (this.nome) {
            case AC_PERDER_INGREDIENTE:
                this.type = 0;
                break;

            case AC_GANHAR_INGREDIENTES:
                this.type = 1;
                break;

            case AC_RETIRAR_INGREDIENTE_JOGADOR:
                this.type = 2;
                break;

            case AC_QUEIMAR_PIZZA:
                this.type = 3;
                break;
        }

    }

}
