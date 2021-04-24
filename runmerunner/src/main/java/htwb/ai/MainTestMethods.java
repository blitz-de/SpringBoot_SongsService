package htwb.ai;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MainTestMethods {
	
	public static void main (String[] args) {

		
//		Scanner input = new Scanner(System.in);
//		System.out.println("Enter Class Name: " );
//		String packageName = "htwb.ai.";
//		String classInput = input.nextLine();
//		
//		String className = packageName+classInput;
//		
//		if (args.isEmpty()) { //classInput -was
//			System.out.println("no input");
//		}
//
		if (args.length ==0 ) {
			System.out.println("no-input");
			System.out.println("Proper Usage is: java program filename");
			System.exit(0);
		}

		for(int i = 0; i<args.length;i++){
			searchForClass(args[i]);
		}
	}
	static void searchForClass(String className){
		System.out.println("> search class: "+ className);
		System.out.println("---------------");
		int success= 0, failed=0, total=0, disabled = 0;
		ArrayList<Method> withoutRunMeMethods = new ArrayList<>();
		ArrayList<Method> runMeMethods = new ArrayList<>();
		ArrayList<Method> notInvokeableMethods = new ArrayList<>();
			try {
				Class<?> clazz = Class.forName(className);

				//System.out.println("getDeclaredMethods: ");
				Method[] declMethods = clazz.getDeclaredMethods();
				System.out.println("trying to invoke methods with @runMe...");
				System.out.println();
				for (Method m: declMethods) {

					// RUNME
					if (m.isAnnotationPresent(RunMe.class)) {
						Annotation annotation = m.getAnnotation(RunMe.class);
						RunMe runMeMethod = (RunMe) annotation;
						if (runMeMethod.enabled()) {

							String result = "n/a";
							try {
								result = (String) m.invoke(clazz.newInstance());
								/*System.out.printf("%s - runMeMethod '%s' - processed %n - result: %n",
										++total,
										m.getName(),
										result);*/
								runMeMethods.add(m);
								++total;
								success++;
							} catch (Throwable ex) {
								/*System.out.printf("%s - non-invokable '%s' - didn't process: %s %n",
										++total,
										m.getName(),
										ex.getCause());*/
								notInvokeableMethods.add(m);
								++total;
								failed++;
							}
						}
					} else {
						withoutRunMeMethods.add(m);
						++total;
						disabled++;
					}
				}
				System.out.println();
				System.out.println("printing method groups...");
				printMethods(withoutRunMeMethods, "Methods without @RunMe:");
				printMethods(runMeMethods, "Methods with @RunMe:");
				printMethods(notInvokeableMethods, "not invocable:");

				System.out.printf("%nResult: Total : %d, Successful: %d, failed %d, Disabled %d%n",
						total,
						success,
						failed,
						disabled);


			} catch (ClassNotFoundException e) {
				System.out.println("Error: Could not find class "+className);
				System.out.println("Usage: java -jar runmerunner-sakvis.jar className");
			}
	}
	static void printMethods(ArrayList<Method> methods, String message){
		System.out.println("---------------");
		System.out.println(message);
		System.out.println();
		for (Method m: methods) {
			System.out.println(m.getName());
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
