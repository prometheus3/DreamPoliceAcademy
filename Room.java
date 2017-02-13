import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> objects;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        objects = new HashMap<String, Item>();
    }

    /**
     * Define an exit from this room.
     * @param description The direction of the exit.
     * @param neighbour The room in the given direction.
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Add an object that is present in the room.
     */
    public void addItem(Item object)
    {
        objects.put(object.getName(), object);
    }
    
    /**
     * Remove an object from a room.
     */
    public Item removeItem(String name)
    {
        return objects.remove(name);
    }
    
    /**
     * Return the item with the name "name".
     * If there is no item of that name, return null.
     */
    public Item getItem(String name)
    {
        return objects.get(name);
    }
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return the room that is reached if we go from this
     * room in direction "direction".
     * If there is no room in that direction, return null.
     * @return The connecting room.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits.
     * For example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String exitString = "\tExits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            exitString += " " + exit;
        }
        return exitString;
    }
    
    /**
     * Return a long description of this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String longDescription = "\tYou are " + description + "\n" + getExitString() + "\n";
        Collection<Item> values = objects.values();
        if(!objects.isEmpty())
        {
            longDescription += "\tThere is:\n";
            for(Item object : values)
            {
                longDescription += "\t\t- " + object.getDescription() + "\n";
            }
        }
        return longDescription;
    }
}