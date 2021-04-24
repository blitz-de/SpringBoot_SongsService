//package htwb.ai;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Scanner;
//
//
//public class Experimenting1 {
//
//		public static void main(String[] args) {
//				
//			int success= 0, failed=0, total=0, disabled = 0;
//			
//			Scanner input = new Scanner(System.in);
//			System.out.println("Enter Class Name: " );
//			String packageName = "htwb.ai.";
//			String classInput = input.nextLine();
//			
//			String className = packageName+classInput;
//			System.out.println("Entered Class is: " +className);
//				
//			if (classInput.isEmpty()) {
//				System.out.println("no input");
//			}
//			
//			if (!classInput.isEmpty()) {
////			String className = "htwb.ai.RunMeMethods";
//				try {
//					System.out.println("ClassName: " +className);
//					Class<?> clazz = Class.forName(className);
////					
////					
////					System.out.println("getMethods: ");
////					Method[] methods = clazz.getMethods();
//		//			Arrays.stream(methods).forEach(x -> System.out.println(x.getName()));
//					
//					System.out.println("getDeclaredMethods: ");
//					Method[] declMethods = clazz.getDeclaredMethods();
//					
//					for (Method m: declMethods) {
//						
//						// RUNME
//						if (m.isAnnotationPresent(RunMe.class)) {
//							Annotation annotation = m.getAnnotation(RunMe.class);
//							RunMe runMeMethod = (RunMe) annotation;
//							if (runMeMethod.enabled()) {
//								
//								String result = "n/a";
//								
//								try {
//									result = (String) m.invoke(clazz.newInstance());
//									System.out.printf("%s - runMeMethod '%s' - processed %n - result: %n",
//											++total,
//											m.getName(),
//											result);
//									success++;
//								} catch (Throwable ex) {
//									System.out.printf("%s - runMeMethod '%s' - didn't process: %s %n",
//											++total,
//											m.getName(),
//											ex.getCause());
//									
//									failed++;
//								}
//							} //if - child1
//							else {
//								
//								System.out.printf("%s - RunMeMethod '%s' - didn't process%n",
//										++total,
//										m.getName()
//										);
//								disabled++;
//							} //else2
//						} //if - parent - RUNME-METHODS
//						//--------------------------------------------
//						// start -second if- NoRUNME-Methods
//						
//						if (!m.isAnnotationPresent(RunMe.class)) {
//							//Annotation annotation = m.getAnnotation(RunMe.class);
//							//RunMe runMeMethod = (RunMe) annotation;
//							//if (!runMeMethod.enabled()) {
//								
//								String result = "n/a";
//								
//								try {
//									result = (String) m.invoke(clazz.newInstance());
//									System.out.printf("%s - NoRunMeMethod '%s' - processed %n - result: %n", 
//											++total,
//											m.getName(),
//											result);
//									success++;
//								} catch (Throwable ex) {
//									System.out.printf("%s - NoRunMeMethod '%s' - didn't process: %s %n",
//											++total,
//											m.getName(),
//											ex.getCause());
//									failed++;
//								}
//							//} //if - child1
//							//else {
//								System.out.printf("%s - NoRunMeMethod '%s' - didn't process%n",
//										++total,
//										m.getName());
//								disabled++;
//							//} //else2
//						} 
//						//End of - second if - NoRunME-Methods
//						//-----------------------------------------
//					//	System.out.println(m.getName());
//					} //for
//					
//					System.out.printf("%nResult: Total : %d, Successful: %d, failed %d, Disabled %d%n",
//							total,
//							success,
//							failed,
//							disabled);
//					
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		static Object loadClass(Class<?> clazz) throws InstantiationException,
//		IllegalAccessException,
//		IllegalArgumentException,
//		InvocationTargetException,
//		NoSuchMethodException, 
//		SecurityException {
//			return clazz.getDeclaredConstructor().newInstance();
//		}
//}
