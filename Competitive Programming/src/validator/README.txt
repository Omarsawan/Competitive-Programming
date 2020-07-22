To check if a solution is different from the brute force solution or the solution that is proved to be right by generating random test cases.
it consists of 4 classes:
	1-bruteForce.java : The brute force solution or the solution that is proved to be right. depends on the problem
	2-Solution.java : The solution that needs to be verified. depends on the problem
	3-testCaseGenerator.java : The class that generate random test cases for the specified problem. depends on the problem
	4-test.java : the main class that uses all the previous classes to test the solution. doesn't depend on the problem , we only choose how many times we will generate the test cases