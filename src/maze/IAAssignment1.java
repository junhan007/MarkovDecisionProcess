/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import Mdp.RuleSet;
import Mdp.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import maze.State;

/**
 *
 * @author junha
 */
public class IAAssignment1 implements RuleSet{
    
    
    public int WIDTH = 6 ;
    public int HEIGHT = 6 ;
    
    public final List<State> map = new ArrayList<State>(); 
    public final List<State> Wall_states  = Arrays.asList(new State(1,1), new State(2,1), new State(3,1), new State(1,5), new State(4,4) );
    public final List<State> Neg_states = Arrays.asList(new State(4,1), new State(3,2), new State(2,3), new State(1,4), new State(5,4));
    public final List<State> Pos_states = Arrays.asList(new State(0,5), new State(2,5), new State(3,4), new State(4,3), new State(5,2));
    
    
    
    public double maxReward(){
        return 1;
    }
    public List <State> getAllPossibleStates(){
        for( int i = 0 ; i < 6 ; i++ ){
            for (int j = 0 ; j < 6 ; j++ ){
             State  state =  new State(i,j);
               if (!Wall_states.contains(state)){
                   map.add(state);
               }
            }   
        }
        return map;
    }
    

 public List <Tuple<State,Double>> getProbabilityNextAction(){
     
     
 }
//    public double getImmediateReward(State state); 
//    public List <Action> Actionsets(); 
//    

}
