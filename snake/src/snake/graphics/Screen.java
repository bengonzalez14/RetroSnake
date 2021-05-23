/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.graphics;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import entities.BodyPart;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entities.Apple;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Screen extends JPanel implements Runnable {
    
    private static final long serialVersionVID = 1L;
    
    public static final int WIDTH = 800, HEIGHT = 800;
    private Thread thread;
    private boolean running = false;
    
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    
    private Apple apple;
    private ArrayList<Apple> apples;
    
    private Random r;
    
    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    
    private boolean right = true, left = false, up = false, down = false;
    
    private int ticks = 0; 
    
    private key Key;
    
    public Screen(){
        setFocusable(true);
        Key = new key();
        addKeyListener(Key);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        r = new Random();
        
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
              
               
        start();
    }
    public void tick() {
          if(snake.size() == 0) {
          b = new BodyPart(xCoor, yCoor, 10);  
          snake.add(b);
        }
        
          if(apples.size() == 0){
              int xCoor = r.nextInt(79);
              int yCoor = r.nextInt(79);
              
              apple = new Apple(xCoor, yCoor, 10);
              apples.add(apple);
              
                     }
          
          for(int i = 0; i < apples.size(); i++) {
              if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
                  size++;
                  apples.remove(i);
                  i--;
              }
          }
          
          for(int i=0; i < snake.size(); i++){
              if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor ()){
                 if(i !=snake.size() - 1){
                     stop();
                 } 
              }
          }
          
          if(xCoor < 0 || xCoor > 79 || yCoor < 0 || yCoor > 79){
              stop();
          }
          ticks++;
          
          if(ticks > 250000){
              
              if(right) xCoor++;
              if(left) xCoor--;
              if(up) yCoor--;
              if(down) yCoor++;
              
              ticks = 0;
              
              b = new BodyPart(xCoor, yCoor, 10);
              snake.add(b);
              
              if(snake.size() > size){
                snake.remove(0);  
              }
              
          }
          
        
    }
    public void paint(Graphics g){
        
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(new Color(10, 50, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.BLACK);
        for(int i = 0; i < WIDTH / 10; i++){
            g.drawLine(i * 10, 0, i*10, HEIGHT);
        }
        
        for(int i=0; i < HEIGHT / 10; i++){
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        
        for(int i = 0; i < snake.size(); i++) {
        snake.get(i).draw(g);
                }
                  for(int i = 0; i < apples.size(); i++) {
             apples.get(i).draw(g);
              
          }
        
    }
            
    public void start(){
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
        
    }
    public void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }
    
    private class key implements KeyListener{

       
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
        
            if(key == KeyEvent.VK_RIGHT && !left) {
                up = false;
                down = false;
                right = true;
            }
                        if(key == KeyEvent.VK_LEFT && !right) {
                up = false;
                down = false;
                left = true;
            }
                        
            if(key == KeyEvent.VK_UP & !down) {
             left = false;
             right = false;
             up = true;
            }
            
             if(key == KeyEvent.VK_DOWN & !up) {
             left = false;
             right = false;
             down = true;
            }
            
        
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
