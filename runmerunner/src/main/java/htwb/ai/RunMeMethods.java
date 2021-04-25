package htwb.ai;

public class RunMeMethods extends ParentClass {
	
	@RunMe
	public void findMe1 () {
		System.out.println("findMe1 - public method: done");
	}
	
	@RunMe
	public void findMe2 () {
		System.out.println("findMe2 - public method: done");
	}
	
	@RunMe
	private void findMe3 () {
		System.out.println("findMe3 - private  method: done");
		//IllegalAccessException
	}
//	
	@RunMe
	void findMe4 () {
		System.out.println("findMe4 - package-private method: done");
	}
	
	@RunMe
	public static String findMe5 () {
		System.out.println("findMe5 - public and static: done");
		return "findMe5".toUpperCase();
	}
//	
//	@RunMe
//	public void findMe6 () {
//		System.out.println("findMe6: done");
//	}
	
	//didn't run
	public void testWithoutRunMe() {
		System.out.println("testWithoutRunMe: done");
	}
	
	void testNoRM() {
		System.out.println("testNoRM: done");
	}
	@RunMe
	public void methodOk1 () {

		System.out.println("methodOk1: done");
	}
	
	@RunMe
	public void methodOk2 () {

		System.out.println("methodOk2: done");
	}
	private void methodPackagePrivate() {

		System.out.println("methodPackagePrivate: done");
	}
	
	@RunMe
	protected void methodProtected() {
		System.out.println("methodProtected: done");
	}
	
	@Override
	@RunMe
	public int methodBOOM(){
		System.out.println("methodBOOM: done");
		return 1/0;
	}
	
	@RunMe 
	public void methodWithArg (String str) {
		System.out.println("methodWithArg: done");
		
	}

	@Override
	@RunMe
	int methodInt() {
		System.out.println("methodInt: done");
		// TODO Auto-generated method stub
		return 0;
	}
	
	void noRunMe1() {

		System.out.println("noRunMe1: done");
	}
	
	public void noRunMe2 () {

		System.out.println("noRunMe2: done");
	}
	
	public void noRunMe3 () {
		System.out.println("noRunMe3: done");
	}



}
