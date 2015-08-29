package beginner;

import java.io.IOException;
import java.lang.RuntimeException;

import com.sandwich.koan.Koan;
import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutExceptions {
	
	private void doStuff() throws IOException {
		throw new IOException();
	}
	
	@Koan
	public void catchCheckedExceptions() {
		String s;
		try {
			doStuff();
			s = "code ran normally";
		} catch(IOException e) { 
			s = "exception thrown";
		}
		assertEquals(s, "exception thrown");
	}
	
	@Koan
	public void useFinally() {
		String s = "";
		try {
			doStuff();
			s += "code ran normally";
		} catch(IOException e) { 
			s += "exception thrown";
		} finally {
			s += " and finally ran as well";
		}
		assertEquals(s, "exception thrown and finally ran as well");
	}
	
	@Koan
	public void finallyWithoutCatch() {
		String s = "";
		try {
			s = "code ran normally";
		} finally {
			s += " and finally ran as well";
		}
		assertEquals(s, "code ran normally and finally ran as well");
	}
	
	private void tryCatchFinallyWithVoidReturn(StringBuilder whatHappened) {
		try {
			whatHappened.append("did something dangerous");
			doStuff();
		} catch(IOException e) { 
			whatHappened.append("; the catch block executed");
			return;
		} finally {
			whatHappened.append(", but so did the finally!");
		}
	}
	
	@Koan
	public void finallyIsAlwaysRan() {
		StringBuilder whatHappened = new StringBuilder();
		tryCatchFinallyWithVoidReturn(whatHappened);
		assertEquals(whatHappened.toString(), "did something dangerous; the catch block executed, but so did the finally!");
	}
	
	@SuppressWarnings("finally") // this is suppressed because returning in finally block is obviously a compiler warning
	private String returnStatementsEverywhere(StringBuilder whatHappened) {
		try {
			whatHappened.append("try");
			doStuff();
			return "from try";
		} catch (IOException e) {
			whatHappened.append(", catch");
			return "from catch";
		} finally {
			whatHappened.append(", finally");
			// Think about how bad an idea it is to put a return statement in the finally block
			// DO NOT DO THIS!
			return "from finally";
		}
	}
	
	@Koan
	public void returnInFinallyBlock() {
		StringBuilder whatHappened = new StringBuilder();
		// Which value will be returned here?
		assertEquals(returnStatementsEverywhere(whatHappened), "from finally");
		assertEquals(whatHappened.toString(), "try, catch, finally");
	}
	
	private void doUncheckedStuff() {
		throw new RuntimeException();	
	}
	
	@Koan
	public void catchUncheckedExceptions() {
		// What do you need to do to catch the unchecked exception?
		try {
			doUncheckedStuff();
		} catch (RuntimeException e) {

		}
	}
	
	@SuppressWarnings("serial")
	static class ParentException extends Exception {}  
	@SuppressWarnings("serial")
	static class ChildException extends ParentException {}
	
	private void throwIt() throws ParentException {
		throw new ChildException();
	}
	
	@Koan
	public void catchOrder() {
		String s = "";
		try {
			throwIt();
		} catch(ChildException e) {
			s = "ChildException";
		} catch(ParentException e) {
			s = "ParentException";
		}
		assertEquals(s, "ChildException");
	}	
}
