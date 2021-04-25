package htwb.ai;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SomeTest {
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
    public void test(){
        assertThrows(IllegalAccessException.class, () -> {

            Class<?> c = Class.forName("htwb.ai.RunMeMethods");
            Method method = c.getDeclaredMethod("methodPackagePrivate");
            method.invoke(c.getDeclaredConstructor().newInstance());
        });
        
    }
}
