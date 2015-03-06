import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;

public class Message extends Actor
{
    GreenfootImage gi;
    
    public Message(){
        gi = new GreenfootImage(350, 120); // create a new greenfoot image of size width = 350, height = 120
        gi.clear();
        gi.setColor(java.awt.Color.YELLOW);
        gi.setFont(new java.awt.Font("default", Font.BOLD, 16));
        gi.drawString("KIT : Not Available\nFood: 0\nKNIFE: Not Available\nZombies Remaining = 3", 10, 30); // Initialize the message with zero food, kits etc.
        setImage(gi);
    }
    
    public void act() 
    {
    }
    
    public void setText(String msg){ // updates the message passed into it in the score box
        gi.clear();
        gi.setColor(java.awt.Color.YELLOW);
        gi.setFont(new java.awt.Font("default", Font.BOLD, 16));
        gi.drawString(msg, 10, 30);
    }
}
