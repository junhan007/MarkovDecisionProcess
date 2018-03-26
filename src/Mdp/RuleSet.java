/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mdp;

import java.util.ArrayList;
import java.util.List;
import maze.Action;
import maze.State;

/**
 *
 * @author junha
 */
public interface RuleSet {
    
    public double maxReward();
    public ArrayList <State> getAllPossibleStates();
    public List <Tuple<State,Double>> getNextState(State state, Action action);
    public double getImmediateReward(State state); 
    public List<Action> getAvaliableAction(State state);
    
    
    
    }
    
    
