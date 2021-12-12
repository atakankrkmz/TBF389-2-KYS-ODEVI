package Utils;


/**
 * Write a description of class ErrorDataResult here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class ErrorDataResult<Type> extends DataResult<Type>
{
    public ErrorDataResult(Type data, String msg){
        super(data, false, msg);
    }
    
    public ErrorDataResult(Type data){
        super(data, false);
    }
    
    public ErrorDataResult(String errorMessage){
        super(null, false, errorMessage);
    }
}
