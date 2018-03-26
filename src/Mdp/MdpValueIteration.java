/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mdp;

import java.util.ArrayList;
import java.util.List;
import maze.Action;
import maze.Assignment1;
import maze.State;

/**
 *
 * @author junha
 */
public class MdpValueIteration {
    private int loopvalue;
    private double discount;
    private double convergence;
    private RuleSet enviroment;
    private int loopcount;
    
    public MdpValueIteration(int loopvalue, double discount, double convergence, Assignment1 enviroment){
        this.loopcount = loopvalue;
        this.discount = discount;
        this.convergence = convergence; 
        this.enviroment = enviroment; 
        
        
    }
    
    public double getFutureUVperAction(State state, Action action){
        
        double fut_UValue = 0.00;
        List<Tuple<State,Double>> possible_states;
        possible_states = enviroment.getNextState(state,action);
        for(int i = 0 ; i < possible_states.size() ; i ++){
            if(possible_states.get(i).state != null){
                fut_UValue += possible_states.get(i).attribute * possible_states.get(i).state.getUtility_value();
            }
        }
        
        return fut_UValue;
        
    }
    
    public double BestAction(State s){
        List<Action> actions;
        double max_fut_UV = 0.00;
        double fut_UV = 0.00;
        actions =  enviroment.getAvaliableAction(s);
        for(int i = 0 ; i < actions.size() ;i++){
             fut_UV = getFutureUVperAction(s, actions.get(i));
             
             if (fut_UV > max_fut_UV){
                 max_fut_UV = fut_UV;
             }
             
             
        }
        return max_fut_UV;
    }
    
    
    
    
    
    public void updateUtilityValue(State s){
         s.setUtility_value(enviroment.getImmediateReward(s) + discount*BestAction(s));
    }
    
    
    
    public void runMdp(){
        int j = 0;
        while( j < loopcount){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
        for(int i = 0 ; i< allpossibleStates.size();i++){
            updateUtilityValue(allpossibleStates.get(i));
            
        }
        printUitlityValue();
        j++;
    }
    }
    
    public void printUitlityValue(){
                
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i< allpossibleStates.size();i++){
              System.out.println("(" + allpossibleStates.get(i).getRow()+ "," + allpossibleStates.get(i).getCol() + ") : " + allpossibleStates.get(i).getUtility_value());
            
        }
    }
        
    

    
}
