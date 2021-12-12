package Utils;


/**
 * Write a description of class SuccessDataResult here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class SuccessDataResult<Type> extends DataResult<Type>
{
    public SuccessDataResult(Type data, String msg){
        super(data, true, msg);
    }
    
    public SuccessDataResult(Type data){
        super(data, true);
    }
}
