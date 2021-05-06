package customException;

/**
 * This exception class is supposed to be used to check if the total % of ingredients does not equal 100
 */
public class DescriptorPercentageException extends Exception{

    //Used to write a exception message in the console
    public DescriptorPercentageException(String message) {
        super(message);
    }
}
