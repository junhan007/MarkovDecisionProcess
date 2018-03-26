/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import Mdp.MdpValueIteration;
import Mdp.RuleSet;

/**
 *
 * @author junha
 */
public class Main {
    
    public static void main(String[] args) {
    
        Assignment1 qn1 = new Assignment1(); 
        qn1.instantiateAllStates(); 
        qn1.addStatesIntoCategory();
        MdpValueIteration MdpValue = new MdpValueIteration(3, 0.98 , 1 , qn1 );
        MdpValue.runMdp();
        System.out.println("test");
        
}  
}
