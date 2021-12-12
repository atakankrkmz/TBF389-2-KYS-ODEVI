package FakeDB;
import Utils.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Write a description of class FileOperations here.
 *
 * @author Atakan Korkmaz
 * @version 1.0.0
 */
public class FileOperations
{
    private File file;
    private String fileName;
    
    public FileOperations(String fileName){
        this.fileName = fileName;
    }

    public Result openFile(){
        try {
            File myObj = new File("db_files/" + fileName);
            if (myObj.createNewFile()) {
                file = myObj;
                return new SuccessResult("File is newly created!");
            } else {
                file = myObj;
                return new SuccessResult("File is opened!");
            }
        } catch (IOException e) {
            return new ErrorResult(e.getMessage());
        }
    }

    public Result writeToFile(String content){
        try {
          FileWriter myWriter = new FileWriter("db_files/" + fileName, true);
          BufferedWriter bw = new BufferedWriter(myWriter);
          bw.write(content);
          bw.newLine();
          bw.close();
          myWriter.close();
          return new SuccessResult("Successfully wrote to the file.");
        } catch (IOException e) {
          return new ErrorResult(e.getMessage());
        }
    }
    
    public Result deleteFromFile(String content){
        try {
          File tempFile = new File("db_files/myTempFile.txt");
          BufferedReader reader = new BufferedReader(new FileReader(file));
          BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

          String currentLine;
          while((currentLine = reader.readLine()) != null){
              if(currentLine.equals(content)){
                continue;
              } else{
                  writer.write(currentLine + System.getProperty("line.separator"));
              }
                
          }
          writer.close(); 
          reader.close();
            if (!file.delete()) {
                return new ErrorResult("Error existed while writing to the file.");
            }
          
          boolean successful = tempFile.renameTo(file);
          if(successful){
              openFile();
              return new SuccessResult("Successfully wrote to the file.");
          } else {
              return new ErrorResult("Error existed while writing to the file.");
          }
        } catch (IOException e) {
          return new ErrorResult(e.getMessage());
        }
    }
    
    public DataResult<List> readFile(){
        ArrayList<Object> contentList = new ArrayList<Object>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                contentList.add(next);
            }
            scanner.close();
        } catch (Exception e) {
            return new ErrorDataResult<>(e.getMessage());
        }
        return new SuccessDataResult<List>(contentList,"File was successfully read!");
    }
    
    public Result deleteFile(){
        if (file.delete()) {
            return new SuccessResult("File was successfully deleted!");
        } else {
            return new ErrorResult("File couldn't be deleted!");
        }
    }
}
