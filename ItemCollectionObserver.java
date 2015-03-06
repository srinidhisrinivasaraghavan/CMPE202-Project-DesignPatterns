import greenfoot.*;


public class ItemCollectionObserver implements Observer
{
    
    static int foodCount = 0, swordParts = 0, zombieCount = 3;
    String foodString= "Food : 0", kitString = "Kit : Not Available", zombieString = "Zombies Remaining: 3", swordString = "Sword : Not Available";
    public static boolean hasKit = false;
    public static boolean hasSword = false;
    Creator creator;
    
    
    public ItemCollectionObserver()
    {
    }
    
    public void update(String item, Message m)
    {
        Human h1 = (Human) m.getWorld().getObjects(Human.class).get(0);
        if(item=="kit")
        {
            hasKit = !hasKit;
            if(hasKit)
            kitString = "Kit : Avaialble";
            else
            kitString = "Kit : Not Available";
            if(hasKit==false)
            {
                creator = new KitFactory();
                m.getWorld().addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500)); // Add kit to the world after it has been used.
            }
        }
        else if(item=="food"){
            foodCount++;
            foodString = "Food : "+foodCount;
            if(foodCount%3 == 0){
                creator = new FoodFactory();
                m.getWorld().addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500)); // Add food at random places.
                m.getWorld().addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
                m.getWorld().addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
            }
        }
        else if(item=="sword"){
            swordParts++;
            swordString = "Sword parts : "+swordParts+" out of 2";
            if(h1.swordState.hasFull()){ // Human has collected two parts of sword.
                swordString = "sword : Available";
                swordParts = 0;
                hasSword = true;
                h1.setImage("Human with sword.png");
            }
        }
        else if(item=="zombie"){
            zombieCount--;
            zombieString = "Zombies Remaining: "+zombieCount;
            
            swordParts = 0; // Reset sword as it is used to kill the zombie
            hasSword = false;
            swordString = "Sword : "+swordParts+" out of 2";
            h1.setImage("Human.png");
            
            creator = new SwordFactory(); // Add new Sword parts for the human to collect and kill another zombie
            Actor s1 = creator.getActor();
            m.getWorld().addObject(s1, 80, 229);
            s1.setImage("handle.png");

            Actor s2 = creator.getActor();
            m.getWorld().addObject(s2, 899, 291);
            s2.setImage("blade.png");
    
            
        }
        
        m.setText(kitString +"\n"+ foodString +"\n"+ swordString + "\n" + zombieString);
    }
        
        
        
}
    
    
    
