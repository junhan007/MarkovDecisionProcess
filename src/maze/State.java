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
    int reward;
    double utility_value;
    
    public State (int row , int col) {
       this.row = row;
       this.col = col;
       this.reward = 0;
       this.utility_value = 0;
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

    public double getUtility_value() {
        return utility_value;
    }

    public void setUtility_value(double utility_value) {
        this.utility_value = utility_value;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
