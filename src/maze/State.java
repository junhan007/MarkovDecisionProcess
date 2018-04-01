/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author junha
 */
public class State {
    int row;
    int col;
    double reward;
    double curr_UValue;
    double updated_UValue;
    Action curr_policy;
    Action updated_policy;

    public Action getNew_policy() {
        return updated_policy;
    }

    public void setNew_policy(Action new_policy) {
        this.updated_policy = new_policy;
    }

    public Action getPolicy() {
        return curr_policy;
    }

    public void setPolicy(Action policy) {
        this.curr_policy = policy;
    }



    public double getUpdated_UValue() {
        return updated_UValue;
    }

    public void setUpdated_UValue(double updated_UValue) {
        this.updated_UValue = updated_UValue;
    }
    
    public State (int col , int row) {
       this.row = row;
       this.col = col;
       this.reward = 0;
       this.curr_UValue = 0;
       this.updated_UValue = 0;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getCurrUtility_value() {
        return curr_UValue;
    }

    public void setCurrUtility_value(double utility_value) {
        this.curr_UValue = utility_value;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
