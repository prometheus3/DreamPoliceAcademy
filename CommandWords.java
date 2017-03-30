import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // an ArrayList that holds all valid command words
    private ArrayList<String> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new ArrayList<String>();
        validCommands.add("go");
        validCommands.add("quit");
        validCommands.add("help");
        validCommands.add("look");
        validCommands.add("eat");
        validCommands.add("drink");
        validCommands.add("back");
        validCommands.add("take");
        validCommands.add("drop");
        validCommands.add("inventory");
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(String command : validCommands)
        {
            if(command.equals(aString))
            {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Return all valid commands as a string.
     * @return The list of command words available.
     */
    public String getCommandList()
    {
        String commands = "";
        for(String command : validCommands)
        {
            commands += command + " ";
        }
        return (commands += "\n");
    }
}
