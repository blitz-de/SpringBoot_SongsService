//package htwb.ai;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class testingclass {
//
//    public static void main(String[] args) {
//
//
////		Scanner input = new Scanner(System.in);
////		System.out.println("Enter Class Name: " );
////		String packageName = "htwb.ai.";
////		String classInput = input.nextLine();
////		
////		String className = packageName+classInput;
////		
////		if (args.isEmpty()) { //classInput -was
////			System.out.println("no input");
////		}
////
//        if (args.length == 0) {
//            System.out.println("no-input");
//            System.out.println("Proper Usage is: java program filename");
//            System.exit(0);
//        }
//
//        for (int i = 0; i < args.length; i++) {
//            searchForClass(args[i]);
//        }
//    }
//
//    static void searchForClass(String className) {
//        System.out.println("> search class: " + className);
//        System.out.println("---------------");
//        int success = 0, failed = 0, total = 0, disabled = 0;
//        ArrayList<Method> withoutRunMeMethods = new ArrayList<>();
//        ArrayList<Method> runMeMethods = new ArrayList<>();
//        ArrayList<Method> notInvokeableMethods = new ArrayList<>();
//
//
//        ArrayList<String> _withoutRunMeMethods = new ArrayList<>();
//        ArrayList<String> _runMeMethods = new ArrayList<>();
//        ArrayList<String> _notInvokeableMethods = new ArrayList<>();
//        //ArrayList<Method> notAccessable = new ArrayList<>();
//        try {
//            Class<?> clazz = Class.forName(className);
//
//            //System.out.println("getDeclaredMethods: ");
//            Method[] declMethods = clazz.getDeclaredMethods();
//            System.out.println("invoking methods...");
//            String result = "n/a";
//
//            for (Method m : declMethods) {
//                try {
//
//                    //result = (String)
//                    m.invoke(clazz.getDeclaredConstructor().newInstance());
//                    // RUNME
//                    if (m.isAnnotationPresent(RunMe.class)) {
//                        Annotation annotation = m.getAnnotation(RunMe.class);
//                        RunMe runMeMethod = (RunMe) annotation;
//                        if (runMeMethod.enabled()) {
//                            runMeMethods.add(m);
//                            ++total;
//                            success++;
//
//                        }
//                    } else {
//                        withoutRunMeMethods.add(m);
//                        ++total;
//                        disabled++;
//                    }
//                } catch (IllegalAccessException | InstantiationException ex) {
//                    System.out.println("Error: Could not instantiate or access class " + className);
//                    notInvokeableMethods.add(m);
//                    _notInvokeableMethods.add(m.getName()+": IllegalAccessException");
//                    if (m.isAnnotationPresent(RunMe.class)) runMeMethods.add(m);
//                    ++total;
//                    failed++;
//                    System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
//
//                } catch (InvocationTargetException e) {
//
//                    System.out.println("Error: Could not invoke class " + className);
//                    notInvokeableMethods.add(m);
//
//                    _notInvokeableMethods.add(m.getName()+": InvocationTargetException");
//                    ++total;
//                    failed++;
//                    System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
//                } catch (NoSuchMethodException e) {
//                    System.out.println("Error: Could not instantiate class "+ className);
//                    ++total;
//                    failed++;
//                    notInvokeableMethods.add(m);
//                    _notInvokeableMethods.add(m.getName()+": NoSuchMethodException");
//                    System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Error: Could not invoke method with without argument -> "+ m.getName());
//                    ++total;
//                    failed++;
//                    notInvokeableMethods.add(m);
//                    _notInvokeableMethods.add(m.getName()+": IllegalArgumentException");
//                    System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
//                }
//            }
//            System.out.println();
//            System.out.println("printing report...");
//            if (withoutRunMeMethods.size() != 0) printMethods(withoutRunMeMethods, "Methods without @RunMe:");
//            if (runMeMethods.size() != 0) printMethods(runMeMethods, "Methods with @RunMe:");
//            if (notInvokeableMethods.size() != 0) _printMethods(_notInvokeableMethods, "not invocable:");
//
//            System.out.printf("%nResult: Total : %d, Successful: %d, failed %d, Disabled %d%n",
//                    total,
//                    success,
//                    failed,
//                    disabled);
//
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Error: Could not find class " + className);
//            System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
//        }
//    }
//
//    static void printMethods(ArrayList<Method> methods, String message) {
//        System.out.println("---------------");
//        System.out.println(message);
//        System.out.println();
//        for (Method m : methods) {
//            System.out.println(m.getName());
//        }
//
//    }
//    static void _printMethods(ArrayList<String> methods, String message) {
//        System.out.println("---------------");
//        System.out.println(message);
//        System.out.println();
//        for (String m : methods) {
//            System.out.println(m);
//        }
//
//    }
//
//
//    static Object loadClass(Class<?> clazz) throws InstantiationException,
//            IllegalAccessException,
//            IllegalArgumentException,
//            InvocationTargetException,
//            NoSuchMethodException,
//            SecurityException {
//        return clazz.getDeclaredConstructor().newInstance();
//    }
//}
