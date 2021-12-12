package FakeDB;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FileOperationsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FileOperationsTest
{
    /**
     * Default constructor for test class FileOperationsTest
     */
    public FileOperationsTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void OpenFileTest()
    {
        FakeDB.FileOperations fileOper1 = new FakeDB.FileOperations("db_yazili_kaynak.txt");
        fileOper1.openFile();
    }


    @Test
    public void WriteToFileTest()
    {
        FakeDB.FileOperations fileOper1 = new FakeDB.FileOperations("db_yazili_kaynak.txt");
        fileOper1.writeToFile("2~test kitap~yaz0001~2021-12-12~og0002~REZERVE");
    }
}



