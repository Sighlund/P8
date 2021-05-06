package customException;

/**
 * This exception class is supposed to be used to check if the % of the ingredient added is less or equal
 * than 0 or greater than 100
 */
public class IngredientPercentageException extends Exception{

    //Used to write a exception message in the console
    public IngredientPercentageException(String message){
        super(message);
    }
}
