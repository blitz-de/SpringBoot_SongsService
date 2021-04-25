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
    public void classNotFoundTestWithEmptyString() {
        assertThrows(ClassNotFoundException.class, () -> {
            ParentClass.create("");
        });
    }

    @Test
    public void classNotFoundTest() {
        assertThrows(ClassNotFoundException.class, () -> {
            ParentClass.create("blub");
        });
    }

    @Test
    public void noSuchMethodExTest() {
        assertThrows(NoSuchMethodException.class, () -> {
            ParentClass.create("java.io.Closeable");
        });
    }

    @Test
    public void abtractClassTest() {
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
    public void illegalAccessExceptionTest() {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodPackagePrivate");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

    }

    @Test
    public void boomTestInvoke() {
        assertThrows(InvocationTargetException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodBOOM");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });
        System.out.println("not invocable exception test succeded...");

    } //IllegalArgumentException

    @Test
    public void illegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodWithArg", String.class);
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

        System.out.println("illegal argument exception test succeded...");

    }

    @Test
    public void illegalAccessExceptionTest2() {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");

            System.out.println(c);
            try {
                Method method = c.getDeclaredMethod("findMe3");
                System.out.println(method);
                method.invoke(RunMeMethods.class, "");
            } catch (IllegalAccessException ex) {
                throw ex;
            }
        });
        System.out.println("illegal access exception test for method findMe3 succeded...");

    }

    @Test
    public void invocationExTest() {
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");

            System.out.println(c);
            try {
                Method method = c.getDeclaredMethod("methodInt");
                method.invoke(RunMeMethods.class, "");
            } catch (IllegalAccessException ex) {
                throw ex;
            }
        });

        System.out.println("illigal access exception test succeded...");
    }

    @Test
    public void noSuchClassName()  {
        boolean result = App.searchForClass("");
        assertFalse(result);
        System.out.println("no such className succeded...");
    }
    @Test
    public void normalUseTest() {
        boolean result = App.searchForClass("htwb.ai.RunMeMethods");
        assertTrue(result);
        System.out.println("searchForClass success test succeeded...");
    }

    @Test
    public void noInputTest() {
        App a = new App();
        String[] arr = {};
        assertThrows(NoInputException.class, () -> {
            a.main(arr);
        });
        System.out.println("no input test succeded...");
    }


}
