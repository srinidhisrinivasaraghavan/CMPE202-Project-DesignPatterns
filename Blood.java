import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Blood extends Actor
{
    int delayCount = 0;
    public Blood()
    {
        GreenfootImage image1 = new GreenfootImage("Blood Splatter.png");
        setImage(image1);
    }
    
    public void act() 
    {
        // By default, this act method gets executed again and again and increments delaycount,
        // when delaycount reaches 100, blood actor removes itself.
        delayCount++;
        if(delayCount > 100)
        {
            getWorld().removeObject(this);
        }
    }   
}
