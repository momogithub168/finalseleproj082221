package test.com;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ms.finalseleproj.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jmowa
 */
public class StudentTest {

    Student stu;

    public StudentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stu = new Student("Smith", "White");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testFirst() {
        assertEquals("Smith", stu.getfName());
    }

    @Test
    public void testSecond() {
        assertEquals("White", stu.getlName());
    }

    @Test
    public void testThird() {
        stu.setfName("Anna");
        assertEquals("Anna", stu.getfName());
    }

    @Test
    public void testForth() {
        stu.setlName("Li");
        assertEquals("Li", stu.getlName());
    }
    
        @Test
    public void testFifth() {
        Assert.assertNotEquals("Li", stu.toString());
    }
}
