package Utils;
/**
 * Write a description of class Result here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class Result
{
   private boolean success;
   private String message;
   
   public Result(boolean success) {
	   this.success = success;
   }
   
   public Result(boolean success,String message) {
	   this(success);
	   this.message = message;
   }
   
   public boolean isSuccess() {
	   return this.success;
   }
   
   public String getMessage() {
	   return this.message;
   }
}
