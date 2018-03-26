/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mdp;

/**
 *
 * @author junha
 */
public class Tuple<T, K> {
    public final T state; 

    public T getState() {
        return state;
    }

    public K getAttribute() {
        return attribute;
    }
    public final K attribute;
    
   
    public Tuple(T state, K attribute){
    
        this.state = state ;
        this.attribute = attribute ;

        
    }
    
}
