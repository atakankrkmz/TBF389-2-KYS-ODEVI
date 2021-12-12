package Utils;


/**
 * Write a description of class ErrorResult here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class ErrorResult extends Result
{
    public ErrorResult(){
        super(false);
    }
    
    public ErrorResult(String message){
        super(false, message);
    }
}

