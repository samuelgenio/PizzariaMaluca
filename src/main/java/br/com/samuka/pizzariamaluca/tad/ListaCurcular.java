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
package br.com.samuka.pizzariamaluca.tad;

import br.com.samuka.pizzariamaluca.model.Cedula;
import br.com.samuka.pizzariamaluca.model.Player;

/**
 *
 * @author 'Samuel José Eugênio - https://github.com/samuelgenio'
 */
public class ListaCurcular {

    /**
     * Cedulas para controle interno da lista.
     */
    private Cedula primeiro, ultimo, atual;

    /**
     * Tamanho da lista.
     */
    private int length;

    /**
     * Construtor padrão da lista.
     */
    public ListaCurcular() {
        primeiro = ultimo = atual = null;
    }

    /**
     * @return Se a vista está vazia
     */
    public boolean isEmpty() {
        return (length == 0);
    }

    /**
     * @return Tamanho atual da lista.
     */
    public int getLength() {
        return length;
    }

    /**
     * Insere uma cedula a lista.
     *
     * @param cedula Cedula a ser inserida
     */
    public void insert(Cedula cedula) {

        if (isEmpty()) {
            insertFirst(cedula);
        } else {
            insereLast(cedula);
        }

    }

    /**
     * Insere o primeiro elemento da lista.
     *
     * @param cedula Cedula a ser inserida na lista.
     */
    public void insertFirst(Cedula cedula) {

        Cedula novo = cedula;

        if (isEmpty()) {
            novo.setProximo(novo);
            primeiro = novo;
            ultimo = novo;
            atual = novo;
        } else {
            novo.setProximo(primeiro);
            primeiro = novo;
        }
        length++;
    }

    /**
     * Insere o último elemento na lista.
     *
     * @param cedula Cedula a ser inserida na lista.
     */
    public void insereLast(Cedula cedula) {

        Cedula novo = cedula;

        if (isEmpty()) {
            insertFirst(cedula);
        } else {
            ultimo.setProximo(novo);
            novo.setProximo(primeiro);
            ultimo = novo;
        }

        length++;
    }

    /**
     * Move para a posição indicada.
     *
     * @param pos Posição a qual se quer encontrar
     */
    public void moveParaPosicao(int pos) {

        atual = primeiro;

        for (int i = 1; i < pos; i++) {
            atual = atual.getProximo();
        }
    }

    /**
     * Insere na posição indicada.
     *
     * @param cedula Cedula a ser inserida
     * @param pos index a que a cedula será incerida
     */
    public void insereNaPosicao(Cedula cedula, int pos) {
        Cedula novo = cedula;
        moveParaPosicao(pos);
        novo.setProximo(atual.getProximo());
        atual.setProximo(novo);

        length++;
    }

    /**
     * Movimenta o player no tabuleiro.
     *
     * @param player Player a ser movimentado
     * @param moviment valor de movimentação do player.
     * @return
     */
    public Cedula movePlayer(Player player, int moviment) {

        setPlayerCurrentPosition(player.getCedulaAtual());

        while (moviment-- > 0) {
            atual = atual.getProximo();
        }

        player.setCedulaAtual(atual);

        return atual;

    }

    /**
     * Percorre a lista atual e seta a Cedula atual com a posição atual do
     * player da rodada.
     *
     * @param cedula Cedula de posição atual do player
     */
    public void setPlayerCurrentPosition(Cedula cedula) {

        atual = primeiro;

        if (cedula == null) {
            return;
        }

        atual = cedula;

    }

}
