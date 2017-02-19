import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Command
{
    private ArrayList<String> commands;         // list of commands
    private String message;         // something to print after/before command is complete
    private Player user;
    private byte todo;          // bit code of which methods to perform in the command

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     */
    public Command(String message, byte todo, Player user)
    {
        commands = new ArrayList<String>();
        this.message = message;
        this.todo = todo;
        this.user = user;
    }

    /**
     * Allows for a list to replace the current list of words.
     */
    public void addAlternatives(ArrayList<String> alternatives)
    {
        commands = alternatives;
    }
    
    /**
     * Allows for the addition of one similar word.
     */
    public void addAlternative(String alternative)
    {
        commands.add(alternative);
    }
    
    /**
     * Checks if a given string is a possible command.
     */
    public boolean isCommand(String newWord)
    {
        for(String command : commands)  // search for the new input word in the list of possible commands
        {
            if(command.equals(newWord)) {
                return true;        // command was found
            }
        }
        return false;       // command was not found
    }
    
    /**
     * 
     */
    public void action(String[] words)
    {
        //what to do
    }
    
    /**
     * Display any message required for the command.
     */
    public void response()
    {
        System.out.println(message);
    }
    
    /**
     * Return the player of the game.
     */
    public Player getUser()
    {
        return user;
    }
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord()
    {
        return command;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

