/**
 * Subclass for any command that is only room-related, e.g the command "exit"
 * may only work when in the "elevator" room.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoomCommand extends Command
{
    private String room;
    
    /**
     * Constructor for objects of class RoomRelated
     */
    public RoomCommand(String response, byte todo, String room)
    {
        super(response, todo);
        this.room = room;
    }
    
    /**
     * 
     */
    public void leaveRoom(){}
}
