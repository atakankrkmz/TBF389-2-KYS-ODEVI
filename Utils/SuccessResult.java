package Utils;


/**
 * Write a description of class SuccessResult here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class SuccessResult extends Result
{
    public SuccessResult(){
        super(true);
    }
    
    public SuccessResult(String message){
        super(true, message);
    }
}
