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

/**
 *
 * @author junha
 */
    
    
   
public class Assignment1 implements RuleSet{
   
    
    
    public int WIDTH = 6 ;
    public int HEIGHT = 6 ;
    public State[][] map = new State[HEIGHT][WIDTH];
    public final ArrayList Wall_states = new ArrayList();
    public final ArrayList Neg_states = new ArrayList();
    public final ArrayList Pos_states = new ArrayList();
 //   public final List<State> Wall_states  = Arrays.asList(new State(1,1), new State(2,1), new State(3,1), new State(1,5), new State(4,4) );
  //  public final List<State> Neg_states = Arrays.asList(new State(4,1), new State(3,2), new State(2,3), new State(1,4), new State(5,4));
  //  public final List<State> Pos_states = Arrays.asList(new State(0,5), new State(2,5), new State(3,4), new State(4,3), new State(5,2));
    
    
    @Override
    public double maxReward(){
        return 1;
    }
 
 
    
      
    public void instantiateAllStates(){
        for( int i = 0 ; i < 6 ; i++ ){
            for (int j = 0 ; j < 6 ; j++ ){
                map[i][j] = new State(i,j);
                
               }
    }}
            
    public void addStatesIntoCategory(){
    Wall_states.add(map[1][0]);
    Wall_states.add(map[4][1]);
    Wall_states.add(map[1][4]);
    Wall_states.add(map[2][4]);
    Wall_states.add(map[3][4]);    
    Neg_states.add(map[1][1]);
    Neg_states.add(map[2][2]);
    Neg_states.add(map[3][3]);
    Neg_states.add(map[4][4]);
    Neg_states.add(map[5][1]);
    Pos_states.add(map[0][0]);
    Pos_states.add(map[2][0]);
    Pos_states.add(map[5][0]);    
    Pos_states.add(map[3][1]);
    Pos_states.add(map[4][2]);
    Pos_states.add(map[5][3]);    
    
    
    
}

    
    
 
    @Override               
    public ArrayList<State> getAllPossibleStates(){
        
        ArrayList<State> possiblestates = new ArrayList<State>();
        
        for( int i = 0 ; i < 6 ; i++ ){
            for (int j = 0 ; j < 6 ; j++ ){
                State current_state = map[i][j];
                if (!Wall_states.contains(current_state)){
                  possiblestates.add(current_state);
                }
               }
              }

        return possiblestates;
    }
    
    public State validateStates(int col, int row, State s ){
         if(col >= 0 && col < 6 && row >= 0 && row <6){
             if(!Wall_states.contains(map[col][row]))
             return map[col][row];
         }
        return s; 
    }
    
    @Override
    public List<Action> getAvaliableAction(State state){
        return Arrays.asList(Action.values());
    }
    
    
    //Possible Next States with corresponding probability depending on action.
    @Override  
 public List <Tuple<State,Double>> getNextState(State state, Action action){
     List<Tuple<State,Double>> possibleStates = new ArrayList(); 
     switch(action){
         case UP: {
             possibleStates.add(new Tuple<>(validateStates(state.getCol(), state.getRow() - 1, state),0.8));
             possibleStates.add(new Tuple<>(validateStates(state.getCol()+ 1,state.getRow(), state), 0.1));
             possibleStates.add(new Tuple<> (validateStates(state.getCol() - 1,state.getRow(), state), 0.1));
             break;
         }
         case LEFT: {
             possibleStates.add(new Tuple<>(validateStates(state.getCol(),state.getRow()-1, state), 0.1));
             possibleStates.add(new Tuple<>(validateStates(state.getCol(),state.getRow()+1, state), 0.1));
             possibleStates.add(new Tuple<>(validateStates(state.getCol()-1,state.getRow(), state), 0.8));             
             break;
         }
         case RIGHT:{
             possibleStates.add(new Tuple<>(validateStates(state.getCol(),state.getRow()+1, state), 0.1));
             possibleStates.add(new Tuple<>(validateStates(state.getCol(),state.getRow()-1, state), 0.1));
             possibleStates.add(new Tuple<>(validateStates(state.getCol()+1,state.getRow(), state), 0.8));           
             break;
                     }
         case DOWN:{
             possibleStates.add(new Tuple<>(validateStates(state.getCol(),state.getRow()+1, state),0.8));
             possibleStates.add(new Tuple<>(validateStates(state.getCol()-1,state.getRow(), state), 0.1));
             possibleStates.add(new Tuple<> (validateStates(state.getCol()+1,state.getRow(), state), 0.1));
             break;
                     }         
         
             
    }
     return possibleStates;
     
 }
 
 
    @Override
	public double getImmediateReward(State state) {
		if(Neg_states.contains(state))
			return -1;
		else if(Pos_states.contains(state))
			return 1;
		else
			return -0.04;
	}
        
        public void setImmediateReward(){
        for( int i = 0 ; i < 6 ; i++ ){
            for (int j = 0 ; j < 6 ; j++ ){
                State current_state = map[i][j];
                if (Neg_states.contains(current_state)){
                    current_state.setReward(-1);
                }
                else if (Pos_states.contains(current_state)){
                    current_state.setReward(1);
                }
                else if (!Wall_states.contains(current_state)){
                    current_state.setReward(-0.04);
               
                }
               }
              }
    }	

}
