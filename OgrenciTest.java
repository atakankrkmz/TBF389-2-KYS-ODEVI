

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OgrenciTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class OgrenciTest
{
    /**
     * Default constructor for test class OgrenciTest
     */
    public OgrenciTest()
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
    public void ogrenciOlustur()
    {
        Ogrenci ogrenci1 = new Ogrenci("og1111", "test", "test");
    }
}

