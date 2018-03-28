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
    private int loopvalue = 0;
    private double discount;
    private double convergence;
    private Assignment1 enviroment;
    private int loopcount;
    
    public MdpValueIteration( double discount,  Assignment1 enviroment){
        
        this.discount = discount;
        this.convergence = enviroment.maxReward() * 1e-3; 
        this.enviroment = enviroment; 
        
        
    }
    
    //Summation of all possible states and its utility value
    //Sum(P(S'|s,a)U(S')
    public double SumFutureUV(State state, Action action){
        
        double fut_UValue = 0.00;
        List<Tuple<State,Double>> next_state;
        next_state = enviroment.getNextState(state,action);
        for(int i = 0 ; i < next_state.size() ; i ++){
            if(next_state.get(i).state != null){
                fut_UValue += next_state.get(i).attribute * next_state.get(i).state.getCurrUtility_value();
            }
        }
        
        return fut_UValue;
        
    }
    
    public double BestAction(State s){
        List<Action> actions;
        double max_fut_UV = 0.00;
        int j = 0;
        double fut_UV = 0.00;
        actions =  enviroment.getAvaliableAction(s);
        for(int i = 0 ; i < actions.size() ;i++){
             fut_UV = SumFutureUV(s, actions.get(i));
             
             if ( fut_UV > max_fut_UV){
                 max_fut_UV = fut_UV;
                 j = i;
             }
             
             
             
 
             
             
        }
        s.setPolicy(actions.get(j));
        return max_fut_UV;
    }
    
    
    
    
    
    public void updateUtilityValue(State s){
         s.setUpdated_UValue(s.getReward() + discount*BestAction(s));
    }
    
    
    
    public void runValueIterationMdp(){
        boolean repeat;
 
        do{
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
        for(int i = 0 ; i< allpossibleStates.size();i++){
            updateUtilityValue(allpossibleStates.get(i));
            
        }
  

        
        repeat = maxDifference();
        refreshUitilityValue();

        
  
    } while(repeat);
              System.out.println("loopcount for Value Iteration Mdp = " + loopcount);
                 printUitlityValue();     
                
    }
    
    public void printUitlityValue(){
                
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i< allpossibleStates.size();i++){
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + allpossibleStates.get(i).getCurrUtility_value());
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + " Action :" + allpossibleStates.get(i).getPolicy());            
        }
    }
    public void printNewUitlityValue(){
                
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i< allpossibleStates.size();i++){
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + allpossibleStates.get(i).getUpdated_UValue());
            
        }
    }    
    
        public void printRewards(){
                
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i< allpossibleStates.size();i++){
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + allpossibleStates.get(i).getReward());
            
        }
    }
        
    
    public void refreshUitilityValue(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
              allpossibleStates.get(i).setCurrUtility_value(allpossibleStates.get(i).getUpdated_UValue());
          }
    }

    
    public boolean maxDifference(){
        
        double maxDiff = 0.00;
        double diff = 0.00;
         ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
              
              diff = (Math.abs(allpossibleStates.get(i).getUpdated_UValue())- Math.abs(allpossibleStates.get(i).getCurrUtility_value())  );
              maxDiff = Math.max(maxDiff, diff);
  
                      
          }
          
          loopcount++;
          
          if(maxDiff>convergence)
              return true;
          else
              return false;
          
        
    }
    
    
    
}
