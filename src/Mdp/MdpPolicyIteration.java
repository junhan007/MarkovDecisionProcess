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
public class MdpPolicyIteration {
    private double discount;
    private double convergence;
    private Assignment1 enviroment;
    //loopcount decides how many inner iterations before replacing policy
    private int UV_loopcount;
    private int Policy_loopcount = 0;
    
    public MdpPolicyIteration(int loopcount, double discount,  Assignment1 enviroment){
        
        this.discount = discount;
        this.convergence = enviroment.maxReward() * 1e-3; 
        this.enviroment = enviroment; 
        this.UV_loopcount = loopcount;
       
        
        
    }
    
    public void instantiateFixedPolicy(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
        for(int i = 0 ; i < allpossibleStates.size(); i++){
            allpossibleStates.get(i).setPolicy(Action.DOWN);
        }
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
    
    public void BestAction(State s){
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
        s.setNew_policy(actions.get(j));
    }
    
    
    public void updateUV(State s){
        s.setUpdated_UValue(s.getReward() + discount*SumFutureUV(s,s.getPolicy()));
    }

    
    public void updatePolicyValue(State s){
         BestAction(s);
    }
    
    
    
    public void runPolicyIterationMdp(){

        boolean repeat;
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();     
    do{     
        for(int i = 0 ; i < UV_loopcount ; i++ ){
            for(int j = 0 ; j < allpossibleStates.size(); j++){
                updateUV(allpossibleStates.get(j));
            }
            refreshUitilityValue();
            printUitlityValue();  
        }
        for(int i = 0 ; i< allpossibleStates.size();i++){
           BestAction(allpossibleStates.get(i));
        }     
        repeat = PolicyDifference();
        refreshPolicy(); 
        System.out.println("loopcount for Policy Iteration Mdp = " + Policy_loopcount);
    } while(repeat);
                
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
    
    public void refreshPolicy(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
              allpossibleStates.get(i).setPolicy(allpossibleStates.get(i).getNew_policy());        
    }
    }
    
    public boolean PolicyDifference(){
        boolean ret;

        Policy_loopcount++;
         ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
            if( allpossibleStates.get(i).getNew_policy() != allpossibleStates.get(i).getPolicy())
                return true;
                      
          }
          return false;
    }
    
    
    
}
