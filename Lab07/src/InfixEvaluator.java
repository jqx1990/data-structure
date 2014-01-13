/**
 * Name: Qingxiang Jia
 * Assignment: Lab #7
 * Title: Expression Evaluator
 * Course: CSCE 270
 * Lab Section: 01
 * Semester: Spring 2011
 * Instructor: David Wolff
 * Date: 4/10/2011
 * Sources consulted: Java Docs, Tutor Matt,
 * http://www.codeguru.com/forum/showthread.php?t=277281
 * and http://junit.sourceforge.net/javadoc/org/junit/BeforeClass.html.
 * Program description: It implements all the required functionalities and it also
 * does a lot of creativity. It has three files. They are: InfixEvaluator.java,
 * InfixEvaluatorGUI.java and InfixEvaluatorTest.java. The first file does all the
 * required works and InfixEvaluatorGUI.java does creativity. The last file does
 * both required works and creativity. 
 * Known Bugs: Not found yet.
 * 
 * Creativity: This program can evaluate % and it also has a GUI to display 
 * the graphic representation of the stack in the test program. Every time you 
 * use InfixEvaluator it will generate a file and record all the stack state 
 * information into the file report.txt. If you use it at the second time,
 * the previous record will be kept so even though you test multiple @test
 * in JUnit, the report will be kept. This function actually create a problem.
 * When you test the program several times the report.txt will get really big.
 * So in order to solve this problem I use @BeforeClass in JUnit class to clean
 * previous records so the report you need can be kept and the thing you don't
 * want will be eliminated. I think this should be another creativity. Another 
 * creativity is in the GUI, I use % to determine where should the program print
 * a bar to separate the pair of operand stack and operator stack. There is a small
 * creativity is that when you click "Display report" button, the program will not
 * generate new information because the .setText(""). The last creativity is my
 * program can detect whether it is caused by operand stack or operator stack when
 * an exception happens and it will point out which exception(an prematurely empty
 * operand stack or a prematurely empty operand stack).
 * 
 * Quick questions:
 * Why don't you create a InfixEvaluator object when test?
 * Why does output file lose all formating?
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Stack;

public class InfixEvaluator 
{
	/**
	 * Class fields
	 */
	static Stack<String> operand = new Stack<String>();
	static Stack<String> operator = new Stack<String>();	
	static boolean reachExpressionEnd = false;
	static int finalResult;
	static boolean operandEverEmpty = true;
	static boolean operatorEverEmpty = true;
	static File outputFile;
	static PrintWriter pw;

	/**
	 * @param expression The input mathematical expression.
	 * @return The integer result.
	 * @throws SyntaxErrorException Happens whenever either of the stack gets 
	 * empty prematurely.
	 * @throws IOException This is for the file thing.
	 */
	public static int evaluate( String expression ) throws SyntaxErrorException,
	IOException
	{
		String[] operandsAndOperators; // It holds both operands and operators.
		String opeandOrOperator; // It stores both operands and operators.
		String equation = expression;
		operandsAndOperators = equation.split(" ");
		/**
		 * The method below solves the problem so the program can append
		 * the data without overwriting the previous data, but honestly 
		 * don't know how it works. I learned this from the address below:
		 * http://www.codeguru.com/forum/showthread.php?t=277281
		 */
		pw = new PrintWriter(new FileWriter("report.txt", true));

		// Overwrite previous values.
		operandEverEmpty = true;
		operatorEverEmpty = true;
		finalResult = 0;
		reachExpressionEnd = false;
		operand.clear();
		operator.clear();
		
		/**
		 * The for loop below is one of the core parts of this program.
		 * It inputs the expression and passes it to other methods to 
		 * process.
		 */
		for(int i = 0; i < operandsAndOperators.length; i++)
		{
			opeandOrOperator = operandsAndOperators[i];
			try
			{
				processOp(opeandOrOperator);
			}
			catch(EmptyStackException e)
			{
				throw new SyntaxErrorException("Either of the stack is empty" +
						"prematurely!");
			}
			// Throw exception for an empty operand stack.
			if(operand.isEmpty() && !operandEverEmpty)
			{
				throw new SyntaxErrorException("Operand stack is " +
						"empty prematurely!");
			}
			// Throw exception for an empty operator stack.
			if(operator.isEmpty() && !operatorEverEmpty)
			{
				throw new SyntaxErrorException("Operator stack is " +
						"empty prematurely!");
			}
		}
		reachExpressionEnd = true;
		try
		{
			processOp(""); // This is suggested by tutor Matt.
		}
		catch(EmptyStackException e)
		{
			throw new SyntaxErrorException("Either of the stack is empty" +
					"prematurely");
		}
		if(operand.size()!= 1)
		{
			throw new SyntaxErrorException("Operand stack does not " +
					"contain a single element when the processing is complete.!");
		}
		return finalResult;
	}
	
	/**
	 * @param Undecided Operand or operator.
	 * @return A boolean value.
	 */
	private static boolean isOperand(String Undecided)
	{
		boolean result = false;
		boolean stop = false;
		String nums = "0123456789";
		for(int i = 0; i < 10 && stop == false; i++)
		{
			if(Undecided.charAt(0) == nums.charAt(i))
			{
				result = true;
				stop = true;
			}
		}
		return result;
	}

	/**
	 * @param opToken It is a abbreviation of operandOrOperatorToken.
	 * It processes both operands and operators.
	 */
	private static void processOp(String opToken)
	{
		if(!reachExpressionEnd && isOperand(opToken))
		{
				operand.push(opToken);
				operandEverEmpty = false;// It is used to avoid a false alarm.
				pw.append("ja" + operand.toString()+"ja" // "ja" is a delimiter.
						+operator.toString());
		}
		else if(operator.empty() || opToken.equals("("))
		{
			operator.push(opToken);
			operatorEverEmpty = false;// It is used to avoid a false alarm.
			pw.append("ja" + operand.toString()+"ja"// "ja" is a delimiter.
					+operator.toString());
		}
		else if(opToken.equals(")"))
		{
			while(!operator.peek().equals("("))
			{
				operand.push(calculator((String) operator.pop(), 
						Double.parseDouble(operand.pop()), 
						Double.parseDouble(operand.pop())));
				pw.append("ja" + operand.toString()+"ja"// "ja" is a delimiter.
						+operator.toString());
			}
			operator.pop(); // Get rid of "(".
			operatorEverEmpty = true; // Does this prematurely empty case matter?
		}
		else if(!reachExpressionEnd && precedence(opToken) <= 
				precedence((String) operator.peek())
				)
		{
			operand.push(calculator((String) operator.pop(), 
					Double.parseDouble(operand.pop()), 
					Double.parseDouble(operand.pop())));
			operator.push(opToken);
			pw.append("ja" + operand.toString()+"ja"// "ja" is a delimiter.
					+operator.toString());
			operatorEverEmpty = false;// It is used to avoid a false alarm.
		}
		else if(!reachExpressionEnd && precedence(opToken) >
				precedence((String) operator.peek())
				)
		{
			operator.push(opToken);
			operatorEverEmpty = false;// It is used to avoid a false alarm.
			pw.append("ja" + operand.toString()+"ja"// "ja" is a delimiter.
					+operator.toString());
		}
		else if(reachExpressionEnd)
		{
			while(!operator.isEmpty())
			{
				operand.push(calculator((String) operator.pop(), 
						Double.parseDouble(operand.pop()), 
						Double.parseDouble(operand.pop())));
				pw.append("ja" + operand.toString()+"ja"// "ja" is a delimiter.
						+operator.toString() + "ja" + "end" + "ja");
				pw.close();// Close the file.
			}
			finalResult = (int) Double.parseDouble(operand.peek());
		}
	}
	
	/**
	 * @param token The operators.
	 * @return It returns the precedence of the operator.
	 */
	private static int precedence(String token)
	{
		int result = 0;
		if(token.equals("+"))
		{
			result = 1;
		}
		if(token.equals("-"))
		{
			result = 1;
		}
		if(token.equals("*"))
		{
			result = 2;
		}
		if(token.equals("/"))
		{
			result = 2;
		}
		if(token.equals("^"))
		{
			result = 3;
		}
		if(token.equals("("))
		{
			result = 0;
		}
		if(token.equals(")"))
		{
			result = 0;
		}
		if(token.equals("%"))
		{
			result = 2;
		}
		return result;
	}
	
	/**
	 * @param operator 
	 * @param rhs operands
	 * @param lhs operands
	 * @return It returns the result of the calculation.
	 */
	private static String calculator(String operator,
									double rhs, double lhs)
	{
		double value = 0;
		if(operator.equals("+"))
		{
			value = lhs + rhs;
		}
		if(operator.equals("-"))
		{
			value = lhs - rhs;
		}
		if(operator.equals("*"))
		{
			value = lhs * rhs;
		}
		if(operator.equals("/"))
		{
			value = lhs / rhs;
		}
		if(operator.equals("^"))
		{
			value = (int) Math.pow(lhs, rhs);
		}
		if(operator.equals("%"))
		{
			value = lhs % rhs;
		}
		String result = "" + value; // Trick.
		return result;
	}
}