

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class YaziliKaynakTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class YaziliKaynakTest
{
    /**
     * Default constructor for test class YaziliKaynakTest
     */
    public YaziliKaynakTest()
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
    public void yaziliKaynakOlustur()
    {
        Yazar yazar1 = new Yazar("yaz1111");
        YaziliKaynak yaziliKa1 = new YaziliKaynak(1111, "testkitap", yazar1);
    }
}

