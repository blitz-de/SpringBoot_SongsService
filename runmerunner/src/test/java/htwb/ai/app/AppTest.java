package htwb.ai.app;

import htwb.ai.ParentClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
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
    public void notInitableClassTest() {
        assertThrows(NoSuchMethodException.class, () -> {
            ParentClass.create("java.io.Closeable");
        });
    }
    @Test
    public void nullTest() {
        assertThrows(NullPointerException.class, () -> {
            ParentClass.create(null);
        });
    }
    @Test
    public void illegalAccessExceptionTest(){
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodPackagePrivate");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

    }
    @Test
    public void invocationTargetExceptionTest(){
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodBOOM");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

    } //IllegalArgumentException
    @Test
    public void illegalArgumentExceptionTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodWithArg", String.class);
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

    }
    /*@Test
    public void illegalAccessExceptionTest2(){
        assertThrows(IllegalAccessException.class, () -> {
            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("findMe3", String.class);
            method.invoke(c.getDeclaredConstructor().newInstance());
        });

    }*/


}
