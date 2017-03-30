import java.util.ArrayList;

/**
 * A class for any generic commands to be made.
 * Likely to become a super class later.
 * 
 * @author Phillip Rossetti
 * @version 30/03/2017
 */
public class AllCommands
{
    private byte todo;      // controls what happens in the action method
    private String keyWord;     // the command word
    private ArrayList<String> alternatives;     // alternatives to the command word
    private boolean expectSecondWord;       // whether a second word is needed or not
    private String goodResponse;        // Strings to be printed if command successful
    private String badResponse;     // Strings to be printed if command failed

    /**
     * Constructor for objects of class AllCommands
     */
    public AllCommands(byte todo, String keyWord, boolean expectSecondWord, String goodResponse, String badResponse)
    {
        // initialise instance variables
        this.todo = todo;
        this.keyWord = keyWord;
        this.expectSecondWord = expectSecondWord;
        this.goodResponse = goodResponse;
        this.badResponse = badResponse;
        alternatives = new ArrayList<String>();
    }
}
