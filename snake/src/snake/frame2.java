/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.GridLayout;

import javax.swing.JFrame;

import snake.graphics.Screen;

/**
 *
 * @author ben-g
 */
public class frame2 extends JFrame{
    
    public frame2(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        init();
           
    }
        
    
    
    public void init(){
        setLayout(new GridLayout(1, 1, 0, 0));
        
        Screen s = new Screen();
        add(s);
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
    
    new frame2();
    }
        
        
        
    }
    

