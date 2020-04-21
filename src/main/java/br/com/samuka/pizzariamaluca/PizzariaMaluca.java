
package br.com.samuka.pizzariamaluca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.samuka.pizzariamaluca.engine.Game;
import br.com.samuka.pizzariamaluca.model.Ingredient;
import br.com.samuka.pizzariamaluca.model.Pizza;
import br.com.samuka.pizzariamaluca.model.Player;

/**
 *
 * @author Lab.Academico
 */
public class PizzariaMaluca {

    /**
     * Pizzas padrões do game
     */
    private static List<Pizza> pizzas = new ArrayList<>();
    
    public static List<Player> players = new ArrayList<>();
    
    public static void main(String[] args) {

        criaPizzas();
        
        adicionaJogadores();
        
        
    }
    
    /**
     * Adiciona os jogadoresao game
     */
    
    private static void adicionaJogadores(){
        
        StringBuilder pizzasPosiveis = new StringBuilder();
        pizzasPosiveis.append("Qual pizza você escolhe? \r\n");
        pizzasPosiveis.append("1 - " + pizzas.get(0) + "\r\n");
        pizzasPosiveis.append("2 - " + pizzas.get(1) + "\r\n");
        pizzasPosiveis.append("3 - " + pizzas.get(2) + "\r\n");
        pizzasPosiveis.append("4 - " + pizzas.get(3) + "\r\n");
        pizzasPosiveis.append("5 - " + pizzas.get(4));
        
        String restricao = "s";
        
        int i = 5, j = 0;
        
        while(j < 5){
            
            Player player = new Player(JOptionPane.showInputDialog("Nome do jogador?"));
            
            //Clone necessário pois caso 2 players selecionem a mesma pizza não ocorra problema.
            player.setPizza( pizzas.get( selecaoPizza( pizzasPosiveis.toString() ) -1 ).clone()  );
            
            String restricaoUser = JOptionPane.showInputDialog("Deseja inserir outro jogador? ");
            
            players.add(player);
            
            if(restricaoUser.equalsIgnoreCase("n") && players.size() >= 2)
                break;
            
            j++;
            
        }
        
        //O jogo é iniciado
        Game game = new Game();
        
        //Jogo é instartado.
        game.start();
        
    }
    
    private static int selecaoPizza(String possibilidades){
        
        int pizza = -1;
        
        try{
            pizza = Integer.parseInt(JOptionPane.showInputDialog(possibilidades));
        } catch(Throwable e){
            selecaoPizza(possibilidades);
        }
        return pizza;
    }
        
        
    /**
     * Cria as pizzas.
     */
    private static void criaPizzas(){
        
    	Ingredient tomate = new Ingredient("Tomate");
    	Ingredient queijo = new Ingredient("Queijo");
    	Ingredient azeitona = new Ingredient("Azeitona");
    	Ingredient carne = new Ingredient("Carne");
    	Ingredient presunto = new Ingredient("Presunto");
    	Ingredient peperone = new Ingredient("Peperone");
    	Ingredient cheddar = new Ingredient("Cheddar");
    	Ingredient queijoSuico = new Ingredient("Queijo Suiço");
    	Ingredient lomboDePorco = new Ingredient("Lombo de Porco");
    	Ingredient oregano = new Ingredient("Orégano");
    	Ingredient lomboCanadense = new Ingredient("Lombo Canadense");
    	Ingredient chocolate = new Ingredient("Chocolate");
    	Ingredient morango = new Ingredient("Morango");
    	Ingredient pistache = new Ingredient("Pistache");
    	Ingredient chocolateBranco = new Ingredient("Chocolate Branco");
    	Ingredient chocolateGranulado = new Ingredient("Chocolate Granulado");
    	Ingredient leiteCondensado = new Ingredient("Leite Condensado");
    	Ingredient limao = new Ingredient("Limão");
    	Ingredient uva = new Ingredient("Uva");
    	
    	
        Pizza pizza = new Pizza("Portuguesa");
        pizza.addIngredienteNecessario(tomate);
        pizza.addIngredienteNecessario(queijo);
        pizza.addIngredienteNecessario(azeitona);
        pizza.addIngredienteNecessario(carne);
        pizza.addIngredienteNecessario(presunto);
        
        pizzas.add(pizza);
        
        pizza = new Pizza("4 Queijo");
        pizza.addIngredienteNecessario(tomate);
        pizza.addIngredienteNecessario(queijo);
        pizza.addIngredienteNecessario(peperone);
        pizza.addIngredienteNecessario(cheddar);
        pizza.addIngredienteNecessario(queijoSuico);
        
        pizzas.add(pizza);
        
        pizza = new Pizza("Lombinho");
        pizza.addIngredienteNecessario(lomboCanadense);
        pizza.addIngredienteNecessario(azeitona);
        pizza.addIngredienteNecessario(tomate);
        pizza.addIngredienteNecessario(lomboDePorco);
        pizza.addIngredienteNecessario(oregano);
        
        pizzas.add(pizza);
        
        pizza = new Pizza("Chocolate");
        pizza.addIngredienteNecessario(chocolate);
        pizza.addIngredienteNecessario(morango);
        pizza.addIngredienteNecessario(chocolateBranco);
        pizza.addIngredienteNecessario(chocolateGranulado);
        pizza.addIngredienteNecessario(leiteCondensado);
        
        pizzas.add(pizza);
        
        pizza = new Pizza("Sorvete");
        pizza.addIngredienteNecessario(morango);
        pizza.addIngredienteNecessario(chocolate);
        pizza.addIngredienteNecessario(pistache);
        pizza.addIngredienteNecessario(limao);
        pizza.addIngredienteNecessario(uva);
        
        pizzas.add(pizza);
    }
    
}
