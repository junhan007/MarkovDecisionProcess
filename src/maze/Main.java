/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import Mdp.MdpPolicyIteration;
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
        qn1.setImmediateReward();
        MdpValueIteration MdpValue = new MdpValueIteration( 0.99 , qn1 );
        System.out.println("Start Value Iteration Mdp");
        MdpValue.runValueIterationMdp();
        System.out.println("Start Policy Iteration Mdp");
        MdpPolicyIteration MdpPolicy = new MdpPolicyIteration(100,0.99, qn1);
        MdpPolicy.instantiateFixedPolicy();
        MdpPolicy.runPolicyIterationMdp();
}  
}
