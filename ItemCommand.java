
/**
 * Write a description of class ItemRoomCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemCommand extends Command
{
    private String item;

    /**
     * Constructor for objects of class ItemRoomCommand
     */
    public ItemCommand(String response, byte todo, Player user, String item)
    {
        super(response, todo, user);
        this.item = item;
    }
    
    /**
     * Drop item into current room.
     */
    public void dropItem()
    {
        getUser().drop(item);
    }
    
    /**
     * Pick up an item in the current room.
     */
    public void pickUpItem()
    {
        getUser().pickUp(item);
    }
    
    /**
     * Attempt using item.
     * @return True if successful.
     */
    public boolean expendItem()
    {
        return getUser().use(item);
    }
}
