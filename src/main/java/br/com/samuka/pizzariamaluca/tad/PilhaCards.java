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

import java.util.ArrayList;
import java.util.List;
import br.com.samuka.pizzariamaluca.model.Card;

/**
 *
 * @author 'Samuel José Eugênio - https://github.com/samuelgenio'
 */
public class PilhaCards {

    private int tamanho;
    private Card topo;
    private List<Card> cardsList = new ArrayList<>();

    public PilhaCards() {

//        cardsList.add(new Card("Perder um ingrediente", false, "Você acaba de perder um ingrediente :("));
        cardsList.add(new Card("Ganhar dois ingredientes", true, "Você acabou de ganhar dois ingredientes :)"));
//        cardsList.add(new Card("Retirar um ingrediente de um outro jogador", true, "Vá lá, roube um ingrediente de seu adversário ;)"));
//        cardsList.add(new Card("Queimar a pizza", false, "Pareçe que sua pizza passou do ponto e queimou... :|"));

        for (int i = 0; i < 20; i++) {

            this.stack(cardsList.get((int) ((Math.random()) * 1)));

        }

    }

    /**
     * @return the tamanho
     */
    private int getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    private void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the topo
     */
    public Card getTopo() {
        return topo;
    }

    /**
     * @param topo the topo to set
     */
    public void setTopo(Card topo) {
        this.topo = topo;
    }

    public int length() {

        return this.getTamanho();
    }

    public boolean empty() {

        return this.getTamanho() == 0;
    }

    public void stack(Card card) {

        if (this.empty()) {

            this.setTopo(card);

        } else {
            this.getTopo().setProximo(card);

            this.setTopo(card);
        }

        setTamanho(tamanho + 1);

    }

    public Card unstack() {

        //pega o topo atual para retorna-lo no fnal do metodo
        Card cartaoRetorno = this.getTopo();

        //seta o proximo do cartaoetorno como novo topo
        this.setTopo(cartaoRetorno.getProximo());

        //retorna o cartao retorno (antigo topo)
        return cartaoRetorno;
    }

}
