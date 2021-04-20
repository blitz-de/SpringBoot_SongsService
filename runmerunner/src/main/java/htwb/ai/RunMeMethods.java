package htwb.ai;

public class RunMeMethods extends ParentClass {
	
	@RunMe
	void findMe1 () {
		System.out.println("this method1");
	}
	
	@RunMe
	void findMe2 () {
		System.out.println("this is method2");
	}
	
	@RunMe
	private void findMe3 () {
		System.out.println("I am not invocable");
		//InvocationTargetException?
	}
	
	@RunMe
	void findMe4 () {
		System.out.println("this is method2");
	}
	
	@RunMe
	void findMe5 () {
		System.out.println("this is method2");
	}
	
	@RunMe
	void findMe6 () {
		System.out.println("this is method2");
	}
	
	//didn't run
	void testWithoutRunMe() {
		System.out.println("no RunMe here");
	}
	
	void testNoRM() {
		System.out.println("RunMe ain't here too");
	}
	@RunMe
	public void methodOk1 () {
		
	}
	
	@RunMe
	public void methodOk2 () {
		
	}
	void methodPackagePrivate() {
		
	}
	
	@RunMe
	protected void methodProtected() {
		
	}
	
	@Override
	@RunMe
	public void methodBOOM() {
		System.out.println("InvocationTargetException");
	}
	
	@RunMe
	public void methodWithArg (String str) {
		
	}

	@Override
	@RunMe
	int methodInt() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void noRunMe1() {
		
	}
	
	public void noRunMe2 () {
		
	}
	
	public void noRunMe3 () { 
		
	}
	
}
