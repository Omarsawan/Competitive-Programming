package validator;
import java.io.*;
import java.lang.reflect.Method;

public class test {
	//take random input and generate output based on the brute force solution
	public static String bruteForce(String randomInput) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(randomInput.getBytes());
		System.setIn(in);

		ByteArrayOutputStream bruteForceOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bruteForceOutput));

		callMainMethod(bruteForce.class);
		
		return bruteForceOutput.toString();
	}
	//generate random input based on the problem
	static String randomInput() throws Exception{
		ByteArrayOutputStream randomTestCase = new ByteArrayOutputStream();
		System.setOut(new PrintStream(randomTestCase));
		
		callMainMethod(testCaseGenerator.class);
		
		return randomTestCase.toString();
	}
	
	//The class (cls) should be in the same package with the package of class (test)
	static void callMainMethod(Class<?> cls) throws Exception {
        Method meth = cls.getMethod("main", String[].class);
        meth.invoke(null,(Object)(new String[0]));
	}
	public static void testCases(int numberOfTests) throws Exception {
		PrintStream stdout = System.out;
		boolean ok=true;
		for(int c=0;c<numberOfTests;c++) {
			String input  = randomInput();
			String output = bruteForce(input);
			
			ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));
			
			callMainMethod(Solution.class);
			
			if(!output.equals(out.toString())) {
				System.setOut(stdout);
				System.out.println("Input is :");
				System.out.println(input);
				
				System.out.println("Expected Output : ");
				System.out.println(output);
				
				System.out.println("Output : ");
				System.out.println(out.toString());
				
				System.out.println("_________________________________________________________________________________________");
				ok=false;
			}
		}
		if(ok) {
			System.setOut(stdout);
			System.out.println("The output match all generated test cases");
		}
	}
	public static void main(String[] args) throws Exception {
		testCases(5);
	}

}
