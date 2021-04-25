package htwb.ai.app;

import htwb.ai.ParentClass;
import htwb.ai.RunMe;
import htwb.ai.ex.NoInputException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws NoInputException {

        if (args.length == 0) {

            NoInputException ex = new NoInputException("No Parameter [className] was given to main method!");
            System.out.println("Error: " +
                    ex.getClass().getSimpleName());
            System.out.println("Usage: java -jar path/to/jar/runmerunner.jar className" +
                    ex.getClass().getSimpleName());
            //Um die Main-Methode testbar zu machen werfen wird eine NoInputException
            throw new NoInputException(ex.getClass().getSimpleName());

        } else for (int i = 0; i < args.length; i++) {
            searchForClass(args[i]);
        }
//    	searchForClass("htwb.ai.RunMeMethods");
    }

    static boolean searchForClass(String className) {
        System.out.println("> search class: " + className);
        System.out.println("---------------");
        int success = 0, failed = 0, total = 0, disabled = 0;
        ArrayList<Method> withoutRunMeMethods = new ArrayList<>();
        ArrayList<Method> runMeMethods = new ArrayList<>();
        ArrayList<Method> notInvokeableMethods = new ArrayList<>();


        ArrayList<String> _withoutRunMeMethods = new ArrayList<>();
        ArrayList<String> _runMeMethods = new ArrayList<>();
        ArrayList<String> _notInvokeableMethods = new ArrayList<>();
        //ArrayList<Method> notAccessable = new ArrayList<>();
        try {
            Class<?> clazz = Class.forName(className);

            ParentClass.create(className);

            //System.out.println("getDeclaredMethods: ");
            Method[] declMethods = clazz.getDeclaredMethods();
            System.out.println("invoking methods...");
            String result = "n/a";

            for (Method m : declMethods) {
                Annotation annotation = m.getAnnotation(RunMe.class);
                RunMe runMeMethod = (RunMe) annotation;

                try {

//                    m.setAccessible(true);
                    m.invoke(clazz.getDeclaredConstructor().newInstance());
                    // RUNME
                    if (m.isAnnotationPresent(RunMe.class)) {
//                    		m.setAccessible(true);
                        runMeMethods.add(m);
                        ++total;
                        success++;
                    } else {
                        // add even if access is not possible
                        withoutRunMeMethods.add(m);
                        ++total;
                        disabled++;
                    }
                } catch (IllegalAccessException | InstantiationException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
                    if (m.isAnnotationPresent(RunMe.class)) {
//                    	if (m.isAccessible()==true) {
//	                	m.setAccessible(false);

                        notInvokeableMethods.add(m);
                        _notInvokeableMethods.add(m.getName() + " " + ex.getClass().getSimpleName());
                        if (m.isAnnotationPresent(RunMe.class)) runMeMethods.add(m);
//                    }
                    }
                    if (!m.isAnnotationPresent(RunMe.class)) withoutRunMeMethods.add(m);

//                    withoutRunMeMethods.add(m);
                    ++total;
                    failed++;
//                    /System.out.println("Usage: java -jar runmerunner-sakvis.jar className");


                }
            }
            System.out.println();
            System.out.println("printing report...");
            if (withoutRunMeMethods.size() != 0) printMethods(withoutRunMeMethods, "Methods without @RunMe:");
            if (runMeMethods.size() != 0) printMethods(runMeMethods, "Methods with @RunMe:");
            if (notInvokeableMethods.size() != 0) _printMethods(_notInvokeableMethods, "not invocable:");

            System.out.printf("%nResult: Total : %d, Successful: %d, failed %d, Disabled %d%n",
                    total,
                    success,
                    failed,
                    disabled);


        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            System.out.println("Error: " + className + " --- " + e.getClass().getSimpleName());
            System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
            return false;

        }
        return true;
    }

    static void printMethods(ArrayList<Method> methods, String message) {
        System.out.println("---------------");
        System.out.println(message);
        System.out.println();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

    }

    static void _printMethods(ArrayList<String> methods, String message) {
        System.out.println("---------------");
        System.out.println(message);
        System.out.println();
        for (String m : methods) {
            System.out.println(m);
        }

    }


    static Object loadClass(Class<?> clazz) throws InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException,
            NoSuchMethodException,
            SecurityException {
        return clazz.getDeclaredConstructor().newInstance();
    }
}
