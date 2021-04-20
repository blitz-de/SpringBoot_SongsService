package htwb.ai;

import java.lang.reflect.InvocationTargetException;

public abstract class ParentClass {
  
    abstract void methodBOOM();
    
    abstract int methodInt();
    
    /**
     * Creates an instance of a Foo class
     * 
     * @param className - the name of class to be instantiated, class must extend Foo
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
	public static Object create (String className) 
	        throws ClassNotFoundException,
	        InstantiationException, 
	        IllegalAccessException, 
	        IllegalArgumentException, 
	        InvocationTargetException, 
	        NoSuchMethodException, 
	        SecurityException {
		
		System.out.println("Foo.create: " + className);
		Class<?> c = Class.forName(className);
		return  c.getDeclaredConstructor().newInstance();
	}

	/**
	 * Creates an instance of a Foo class, the name of the class will be read from
	 * the system property foo.impl
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static ParentClass createFromSystemProperty() 
	        throws ClassNotFoundException, 
	        InstantiationException, 
	        IllegalAccessException, 
	        IllegalArgumentException, 
	        InvocationTargetException, 
	        NoSuchMethodException,
	        SecurityException {

        // Gib mir den Wert der System-Property "foo.impl",
        // Falls diese Property nicht existiert,
        // nimm den Default-Wert "examples.oop.FooKind"
        String className = System.getProperty("foo.impl", 
                "htwb.ai.RunMeMethods");
        
        System.out.println("ParentClass.createFromSystemProperty: " + className);
        Class<?> c = Class.forName(className);
        return (ParentClass) c.getDeclaredConstructor().newInstance();
    }
}
