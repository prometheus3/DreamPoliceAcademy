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
     * Add an item to the backpack.
     */
    public void addToBackpack(Item item)
    {   
        if((item.getWeight() + currentWeight) <= maxWeight)  // if backpack is not full
        {
            Item removed = currentRoom.removeItem(item.getName());
            // pick up item
            backpack.add(removed);
            currentWeight += item.getWeight();
        }
        else            // backpack is (would be) full
        {
            System.out.println("\tCannot carry any more! You must first drop " +
                               "an item.\n");
        }
    }
    
    /**
     * Remove an item from the backpack.
     */
    public void removeFromBackpack(Item item)
    {
        backpack.remove(item);
        currentWeight -= item.getWeight();
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
        else  // if item exists in this room
        {
            addToBackpack(removed);
        }
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
                removeFromBackpack(currentItem);
                
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
    public String items()
    {
        String items = "";
        items += "\tCurrent weight: " + currentWeight + ", Maximum weight: " + maxWeight + "\n";
        items += "\tYou are carrying:" + "\n";
        for(Item item : backpack)
        {
            items += "\t\t- " + item.getDescription() + ", wt: " + item.getWeight() + "\n";
        }
        return items;
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