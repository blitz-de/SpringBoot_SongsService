package htwb.ai.app;

import htwb.ai.ParentClass;
import htwb.ai.RunMeMethods;
import htwb.ai.ex.NoInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private String classWithMethods;
    private String classNotFound;
    private String extraClass;
	private AnnoVsNoAnnoMethods allMethods;

    Class clazz;
    Method[] methods;
    Object obj;
    private List[] results;

    private final String path = "htwb.ai.";

    @BeforeEach
    public void setUp() throws Exception {
        classWithMethods = path + "RunMeMethods";
        extraClass = path + "ExtraClass";
        classNotFound = path + "N/A";
        results = new ArrayList[2];
    }

    @Test
    public void noInputTest() {
        App a = new App();
        String[] emptyArr = {};
        assertThrows(NoInputException.class, () -> {
            a.main(emptyArr);
        });
        System.out.println("no input test succeded...");
    }

    @Test
    public void classNotFoundTest() {
        assertThrows(ClassNotFoundException.class, () -> {
            ParentClass.create("blub");
        });
        System.out.println("classNotFoundException test succeded...");
    }

    @Test
    public void noSuchMethodExTest() {
        assertThrows(NoSuchMethodException.class, () -> {
            ParentClass.create("java.io.Closeable");
        });
    }

    @Test
    public void abtractClassInstantiationExceptionTest() {
        assertThrows(InstantiationException.class, () -> {
            ParentClass.create("htwb.ai.ParentClass");
        });
    }

    @Test
    public void nullTest() {
        assertThrows(NullPointerException.class, () -> {
            ParentClass.create(null);
        });
    }

    @Test
    public void privateIllegalAccessExceptionTest() {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("findMe4");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });
        System.out.println("Private-Method IllegalAccessException test succeded...");
    }

    @Test
    public void InvocationTargetExceptionTest() {
        assertThrows(InvocationTargetException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodBOOM");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });
        System.out.println("BOOM-Method- InvocationTargetException test succeded...");

    } //IllegalArgumentException

    @Test
    public void illegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodWithArg", String.class);
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

        System.out.println("IllegalArgumentException test succeded...");
    }

    @Test
    public void protectedIllegalAccessExceptionTest() {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");

            System.out.println(c);
            try {
                Method method = c.getDeclaredMethod("methodProtected");
                System.out.println(method);
                method.invoke(RunMeMethods.class, "");
            } catch (IllegalAccessException ex) {
                throw ex;
            }
        });
        System.out.println("Protected-Method IllegalAccessException test succeded...");

    }

    @Test
    public void packagePrivateIllegalAccessException () {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");

            System.out.println(c);
            try {
                Method method = c.getDeclaredMethod("findMe5");
                method.invoke(RunMeMethods.class, "");
            } catch (IllegalAccessException ex) {
                throw ex;
            }
        });

        System.out.println("Package-pirvate-Method IllegalAccessException test succeded...");
    }

    @Test
    public void noSuchClassName()  {
        boolean result = allMethods.searchForClass("");
        assertFalse(result);
        System.out.println("no such className succeded...");
    }
    
    @Test
    public void classFoundReturnTrue() {
        boolean result = allMethods.searchForClass("htwb.ai.RunMeMethods");
        assertTrue(result);
        System.out.println("searchForClass success test succeeded...");
    }


}
