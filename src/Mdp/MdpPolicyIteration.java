/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mdp;


import java.io.FileWriter;
import java.io.IOException;
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
    //loopcount decides how many inner iterations before re-evaluating policy
    private int UV_loopcount;
    private int Policy_loopcount = 0;
    
    public MdpPolicyIteration(int loopcount, double discount,  Assignment1 enviroment){
        
        this.discount = discount;
        this.convergence = enviroment.maxReward() * 1e-3; 
        this.enviroment = enviroment; 
        this.UV_loopcount = loopcount;
       
        
        
    }
    
    //set default policy for all possible states
    public void instantiateFixedPolicy(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
        for(int i = 0 ; i < allpossibleStates.size(); i++){
            allpossibleStates.get(i).setPolicy(Action.DOWN);
        }
    }
    
    //Summation of all next states and its utility value
    //Sum(P(S'|s,a)U(S')
    public double SumFutureUV(State state, Action action){
        
        double fut_UValue = 0.00;
        List<Tuple<State,Double>> next_state;
        //get possible next states
        next_state = enviroment.getNextState(state,action);
        //iterates thru possible next states
        for(int i = 0 ; i < next_state.size() ; i ++){
            if(next_state.get(i).state != null){
                fut_UValue += next_state.get(i).attribute * next_state.get(i).state.getCurrUtility_value();
            }
        }
        return fut_UValue;
    }
    
    //Re-evulation of policy 
    public void BestAction(State s){
        List<Action> actions;
        double max_fut_UV = 0.00;
        int j = 0;
        double fut_UV = 0.00;
        actions =  enviroment.getAvaliableAction(s);
        //iterates through all possible action
        for(int i = 0 ; i < actions.size() ;i++){
             fut_UV = SumFutureUV(s, actions.get(i));        
             if ( fut_UV > max_fut_UV){
                 max_fut_UV = fut_UV;
                 j = i;
             }             
        }
        s.setNew_policy(actions.get(j));
    }
    
    //get Utility Value based on bellmans equation 
    public void findUtilityValue(State s){
        s.setUpdated_UValue(s.getReward() + discount*SumFutureUV(s,s.getPolicy()));
    }

    

    
    
    
    public void runPolicyIterationMdp() throws IOException{
        //check for terminating condition 
        boolean repeat;
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();     
    do{   

        //internal loop before reevaluating policy
        for(int i = 0 ; i < UV_loopcount ; i++ ){
            for(int j = 0 ; j < allpossibleStates.size(); j++){
                findUtilityValue(allpossibleStates.get(j));
            }
            //updates all states with new Utility
            refreshUtilityValue();

        }
        //Re-Evaluation of Policy for all states
        for(int i = 0 ; i< allpossibleStates.size();i++){
           BestAction(allpossibleStates.get(i));
        } 
        
        //check if there is a change in policy for any states. 
        // returns true if there is change
        repeat = PolicyDifference();
        
        //updates all states with new Policy
        refreshPolicy(); 
        
        //Write to Csv File
        try(FileWriter writer = new FileWriter("policy.csv" , true )) {
                writer.write(converToCSV());
		}

    } while(repeat);
    
    
                System.out.println("loopcount for Policy Iteration Mdp = " + Policy_loopcount);        
                
               printUtilityValueAndPolicy();   
    
    }
    
    public void printUtilityValueAndPolicy(){
                
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i< allpossibleStates.size();i++){
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + allpossibleStates.get(i).getCurrUtility_value());
              System.out.println("(" + allpossibleStates.get(i).getCol()+ "," + allpossibleStates.get(i).getRow() + ") : " + " Action :" + allpossibleStates.get(i).getPolicy());            
        }
    }
    public void printNewUtilityValue(){
                
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
        
    
     //updates new Utility
    public void refreshUtilityValue(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
              allpossibleStates.get(i).setCurrUtility_value(allpossibleStates.get(i).getUpdated_UValue());
          }
    }
    
    //updates new Policy
    public void refreshPolicy(){
        ArrayList<State> allpossibleStates = enviroment.getAllPossibleStates();
          for(int i = 0 ; i < allpossibleStates.size(); i++){
              allpossibleStates.get(i).setPolicy(allpossibleStates.get(i).getNew_policy());        
    }
    }
    
    //Checks for change in policy for any states
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
    
     
    public String converToCSV(){
        StringBuilder strbuilder = new StringBuilder();
 
        
        for( int i = 0 ; i < enviroment.getAllPossibleStates().size() ; i++){
            strbuilder.append(Policy_loopcount);
          //  strbuilder.append(enviroment.getAllPossibleStates().get(i));
          strbuilder.append(",");
            strbuilder.append(enviroment.getAllPossibleStates().get(i).getUpdated_UValue());
                 strbuilder.append(",");
                 strbuilder.append("C");
                 strbuilder.append(enviroment.getAllPossibleStates().get(i).getCol());
                  strbuilder.append("R");          
                 strbuilder.append(enviroment.getAllPossibleStates().get(i).getRow());                   
                 
            strbuilder.append("\n");
            
        }
        
        return strbuilder.toString();
    }
    
    
}
