import java.util.ArrayList;
/**
 * The user playing the game. Can
 * walk around, and carry items.
 * 
 * @author Phillip Rossetti 
 * @version 09/02/2017
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Item> backpack;
    private int maxWeight, currentWeight;
    private boolean dead, vampire, flying;

    
    /**
     * Constructor for objects of class Player
     */
    public Player(int maxWeight)
    {
        currentRoom = null;
        backpack = new ArrayList<Item>();
        this.maxWeight = maxWeight;
        currentWeight = 0;
        dead = false;
        vampire = false;
    }

    /**
     * Set the current location of the player.
     */
    public void setRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Return the current location of the player.
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Pick up an item in a room.
     */
    public void pickUp(String item)
    {
        Item removed = currentRoom.getItem(item);
        if(removed == null)         // item does not exist in this room
        {
            System.out.println("\tThere is no " + item + " here.\n");
        }
        else if((removed.getWeight() + currentWeight) <= maxWeight)      // if backpack is not full
        {
            removed = currentRoom.removeItem(item);
            // pick up item
            backpack.add(removed);
            currentWeight += removed.getWeight();
            
            System.out.println("\tOk.\n");
        }
        else            // backpack is (would be) full
        {
            System.out.println("\tCannot carry any more! You must first drop " +
                               "an item.\n");
        }
    }
    
    /**
     * Add an item to the backpack.
     */
    public void addItem(Item item)
    {
        backpack.add(item);
        currentWeight += item.getWeight();
    }
    
    /**
     * Drop an item into the current room.
     */
    public void drop(String item)
    {
        if(!backpack.isEmpty()) {      // backpack is not empty
            boolean found = false;
            int index = 0;
            Item currentItem = null;
            
            while(!found && index < backpack.size())
            {
                currentItem = backpack.get(index);
                if(currentItem.getName().equals(item)) {    // item is in backpack
                    found = true;
                }
                else
                {
                    index++;
                }
            }
            
            if(found) {
                // drop item
                currentRoom.addItem(currentItem);
                backpack.remove(currentItem);
                currentWeight -= currentItem.getWeight();
                
                System.out.println("\tOk.\n");
            }
            else
            {
                System.out.println("\tYou are not carrying that.\n");
            }
        }
        else        // backpack is empty
        {
            System.out.println("\tYou are not carrying anything!\n");
        }
    }
    
    /**
     * Drink blood from packet of blood.
     */
    public void drinkBlood()
    {
        boolean found = false;
        int index = 0;
        Item blood = null;
        while(!found && index < backpack.size())
        {
            blood = backpack.get(index);
            if(blood.getName().equals("blood"))
            {
                found = true;
            }
            else
            {
                index++;
            }
        }
        
        if(found)
        {
            backpack.remove(blood);
            System.out.println("\tCongratulations, you became a vampire!\n\tYou can now fly.\n");
            vampire = true;
        }
        else
        {
            System.out.println("\tWhat blood? Your own? Don't be ridiculous!\n");
        }
    }
    
    /**
     * Lists all items in the backpack and
     * their respective weights.
     */
    public void items()
    {
        System.out.println("\tCurrent weight: " + currentWeight + ", Maximum weight: " + maxWeight);
        System.out.println("\tYou are carrying:");
        for(Item item : backpack)
        {
            System.out.println("\t\t- " + item.getDescription() + ", wt: " + item.getWeight());
        }
        System.out.println();
    }
    
    /**
     * Checks if a player is in the abyss. If true
     * then the player is dead.
     */
    public void inAbyss()
    {
        if(currentRoom.getName().equals("abyss")) {
            dead = true;
        }
    }
    
    /**
     * Checks if a player is dead.
     */
    public boolean areDead()
    {
        return dead;
    }
}