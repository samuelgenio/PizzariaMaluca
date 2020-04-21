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
package br.com.samuka.pizzariamaluca.engine;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.samuka.pizzariamaluca.PizzariaMaluca;
import br.com.samuka.pizzariamaluca.model.Card;
import br.com.samuka.pizzariamaluca.model.Cedula;
import br.com.samuka.pizzariamaluca.model.Ingredient;
import br.com.samuka.pizzariamaluca.model.Player;
import br.com.samuka.pizzariamaluca.tad.ListaCurcular;
import br.com.samuka.pizzariamaluca.tad.PilhaCards;
import java.awt.HeadlessException;

/**
 *
 * Classe que simulará o jogo
 *
 * Instruções:
 *
 * 1º -> é necesário existir 2 ou mais jogadores já com suas pizzas
 * selecionadas.
 *
 * @author 'Samuel José Eugênio - https://github.com/samuelgenio'
 */
public class Game implements Runnable {

    /**
     * Cedulas que construiram a lista-tabuleiro do jogo
     */
    List<Cedula> cedulas = new ArrayList<>();

    /**
     * Lista-tabuleiro que o jogo usará para mover os personagens.
     */
    ListaCurcular listaTabuleiro = new ListaCurcular();

    /**
     * Define o Player vencedor.
     */
    private Player playerVictorious;

    private PilhaCards pilhaCardSorteAzar;

    /**
     * Construtor padrão do game. Ele iniciará a lista-tabuleiro do jogo com os
     * ingredientes das pizzas e as cartas de sorte/azar.
     */
    public Game() {

        inicializaGame();

    }

    /*
	 * A lista-tabuleiro é feito de diversos objetos do tipo <b>Cedula<b>.
	 * É necessário então transformar os itens do tabuleiro que são:
	 * <b>Ingredient<b> e os <b>Card<b> em tipo <b>Cedula<b>
	 * 
     */
    private void inicializaGame() {

        //quantidade de fixas de sorte ou azar
        int qtdSorteAzar = (PizzariaMaluca.players.size() * 5) / 10;

        int control = -1;
        //percorre os players que foram cadastrados para jogar.
        while (++control < PizzariaMaluca.players.size()) {

            for (Ingredient ingredient : PizzariaMaluca.players.get(control).getPizza().getIngredientesNecessarios()) {
                //Só pode incluir se não existir ainda esse ingrediente na lista-tabuleiro.
                if (verificaIngredientCedula(ingredient.getNome())) {
                    cedulas.add(new Cedula(false, ingredient));
                }
            }

        }

        //adicionando os campos de sorte ou azar.
        for (int i = 0; i < qtdSorteAzar; i++) {
            cedulas.add(new Cedula(true, null));
        }

        for (Cedula cedula : cedulas) {
            if (cedula.getIngredient() != null) {
                System.err.println(cedula.getIngredient().getNome());
            } else {
                System.err.println("sorte/azar");
            }
        }

        //inicializa o tabuleiro para o jogo
        adicionarCedulaListaTabuleiro();

        pilhaCardSorteAzar = new PilhaCards();
    }

    /**
     * Verifica se o ingredient já está inserido na lista-tabuleiro. Verificação
     * necessária para não existir valores repetidos no jogo.
     *
     * @param ingredientName Nome do ingrediente
     * @return true para não encontrado, false para encontrado
     */
    private boolean verificaIngredientCedula(String ingredientName) {

        for (Cedula cedula : cedulas) {
            if (cedula.getIngredient() != null && cedula.getIngredient().getNome().equals(ingredientName)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Percorre as cedulas com os ingredientes e com as fixas de sorte/azar e
     * inclui
     */
    private void adicionarCedulaListaTabuleiro() {

        int index = (int) (Math.random() * cedulas.size());

        listaTabuleiro.insert(cedulas.get(index));//adiciono na lista-tabuleiro
        cedulas.remove(index);//removo o elemento da lista.

        if (cedulas.size() >= 1) {
            adicionarCedulaListaTabuleiro();
        }
    }

    /**
     * Inicia o jogo.
     */
    public void start() {

        this.run();

    }

    @Override
    public void run() {

        /*
         * Player da rodada
         */
        Player playerPlay = null;

        //Contador de players
        int i = 0;

        //Valor do dado que é jogado em todas as rodadas
        int valorDado = -1;

        /*
         * Jogo é executado até um player ser vencedor.
         */
        while (playerVictorious == null) {

            try {

                if (playerPlay == null) {
                    playerPlay = PizzariaMaluca.players.get(i = 0);
                } else {
                    playerPlay = PizzariaMaluca.players.get(i);
                }

                JOptionPane.showMessageDialog(null, "Rodada de: " + playerPlay.toString());

                //Player Joga o Dado.
                valorDado = Dado.rolar();

                //move player no tabuleiro e retorna a posição atual
                Cedula currentPosition = listaTabuleiro.movePlayer(playerPlay, valorDado);

                if (currentPosition.isFlgSorteAzar()) {
                    sorteAzar(playerPlay);
                } else {
                    verifyCurrentPosition(playerPlay, currentPosition);
                }

                //Caso o player tenha completado a pizza ele é o vencedor.
                if (playerPlay.getPizza().getIngredientes().size() == 5) {
                    playerVictorious = playerPlay;
                }

                i++;//incrementa contador de players.
            } catch (HeadlessException e) {
                playerPlay = null;
            }
        }

        JOptionPane.showMessageDialog(null, "Parabéns " + playerVictorious.getNome() + " Você venceu!!");

    }

    /**
     * Adiciona caso o ingrediente ao player caso o mesmo não o possua.
     *
     * @param player Player a ser incluido o ingrediente.
     * @param currentPosition Cedula atual do player.
     */
    private void verifyCurrentPosition(Player player, Cedula currentPosition) {

        JOptionPane.showMessageDialog(null, "Ingrediente da posição: \r\n <" + currentPosition.getIngredient() + ">");

        //Verifica se o player já possui o ingredente
        switch (player.getPizza().isIngredient(currentPosition.getIngredient())) {
            case 0:// Não possui
                player.getPizza().addIngrediente(currentPosition.getIngredient());
                JOptionPane.showMessageDialog(null, currentPosition.getIngredient() + " adicionado para " + player.toString());
                break;
            case 1:// Já possui
                JOptionPane.showMessageDialog(null, player.getNome() + " já possui o ingrediente " + currentPosition.getIngredient());
                break;
            default:// Ingrediente não pertence a pizza do mesmo.
                JOptionPane.showMessageDialog(null, "Que pena a pizza <" + player.getPizza() + "> não necesita do ingrediente <" + currentPosition.getIngredient() + ">");
                break;
        }

    }

    /**
     * Retira uma ficha de sorte ou azar.
     *
     * @param player Player que tirara a carta
     */
    private void sorteAzar(Player player) {

        if (pilhaCardSorteAzar.empty()) {
            pilhaCardSorteAzar = new PilhaCards();
        }

        Card carta = pilhaCardSorteAzar.unstack();

        String msg = carta.getNome() + "\r\n" + carta.getDescricao();

        JOptionPane.showMessageDialog(null, msg);

        switch (carta.getType()) {
            case 0://remove um ingredient do player

                removeIngredient(player);

                break;
            case 1://adiciona dois ingredientes ao player

                adicionaIngredienteJogador(player);
                adicionaIngredienteJogador(player);

                break;
            case 2://Remove um ingrediente de um jogador

                removeIngredientePlayer(player);

                break;
            case 3:// Perde todos os ingredientes

                removeTodosIngredientes(player);

                break;
        }

    }

    /**
     * Remove um ingrediente do player
     *
     * @param player Player a ter um ingrediente removido
     */
    private void removeIngredient(Player player) {

        String msg = player.getNome() + "\r\n" + player.getPizza().removeIngredient();

        JOptionPane.showMessageDialog(null, msg);

    }

    /**
     * Adiciona um ingrediente ao player
     *
     * @param player Player a ter um ingrediente adicionado
     */
    private void adicionaIngredienteJogador(Player player) {

        List<Ingredient> ingredientesRestantes = new ArrayList<>(player.getPizza().getIngredientesNecessarios());

        //Percorre a lista e remove os ingredientes que o player já possui
        for (Ingredient ingredient : ingredientesRestantes) {

            for (Ingredient ingredientJaPossuinte : player.getPizza().getIngredientes()) {
                if (ingredientJaPossuinte == ingredient) {
                    ingredientesRestantes.remove(ingredient);
                }
            }

        }

        StringBuilder selectIngredient = new StringBuilder("Qual ingrediente você deseja incluir? \r\n");

        int i = 1;
        for (Ingredient ingredient : ingredientesRestantes) {
            selectIngredient.append(i).append(" - ").append(ingredient).append("\r\n");
            i++;
        }

        int index = Integer.parseInt(JOptionPane.showInputDialog(selectIngredient));

        player.getPizza().addIngrediente(ingredientesRestantes.get(index - 1));

    }

    /**
     * Remove ingredient de um player reval, primeiro o jogador deve selecionar
     * um dos players que estão competindo com o mesmo, após escolher um o mesmo
     * tem um ingredient aleatório removido.
     *
     * @param player Player que selecionará um outro para ter um ingrediente
     * removido.
     */
    private void removeIngredientePlayer(Player player) {

        try {

            StringBuilder selectPlayer = new StringBuilder("Qual jogador você deseja remover um ingredient?\r\n");

            ArrayList<Player> playersSelecionar = new ArrayList<>();

            for (Player play : PizzariaMaluca.players) {
                if (play != player) {
                    playersSelecionar.add(play);
                }
            }

            int i = 1;

            for (Player playe : playersSelecionar) {
                selectPlayer.append(i).append(" - ").append(playe.getNome()).append("\r\n");
                i++;
            }

            int index = Integer.parseInt(JOptionPane.showInputDialog(selectPlayer));

            //chama o método que remove ingredient da player passando o player selecionado
            removeIngredient(PizzariaMaluca.players.get(index));

        } catch (HeadlessException | NumberFormatException e) {
        }
    }

    /**
     * Remove todos ingredientes do player.
     *
     * @param player Player a ter todos ingredientes removidos.
     */
    private void removeTodosIngredientes(Player player) {

        while (!player.getPizza().getIngredientes().isEmpty()) {
            player.getPizza().removeIngredient();
        }

    }
}
