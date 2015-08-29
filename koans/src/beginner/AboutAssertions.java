package beginner;

// FYI - usually bad practice to import statically, but can make code cleaner
import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertFalse;
import static com.sandwich.util.Assert.assertNotNull;
import static com.sandwich.util.Assert.assertNotSame;
import static com.sandwich.util.Assert.assertNull;
import static com.sandwich.util.Assert.assertSame;
import static com.sandwich.util.Assert.assertTrue;

import com.sandwich.koan.Koan;

public class AboutAssertions {

	@Koan 
	public void assertBooleanTrue() {
		assertTrue(true); // should be true really
	}

	@Koan
	public void assertBooleanFalse() {
		assertFalse(false);
	}
	
	@Koan
	public void assertNullObject(){
		assertNull(null);
	}
	
	@Koan
	public void assertNotNullObject(){
		assertNotNull(true); // anything other than null should pass here...
	}

	@Koan
	public void assertEqualsUsingExpression(){
		assertTrue("Hello World!".equals("Hello World!"));
	}

	@Koan
	public void assertEqualsWithBetterFailureMessage(){
		assertEquals(1, 1);
	}

	@Koan
	public void assertEqualsWithDescriptiveMessage() {
		// Generally, when using an assertXXX methods, expectation is on the 
		// left and it is best practice to use a String for the first arg 
		// indication what has failed
		assertEquals("The answer to 'life the universe and everything' should be 42", 42, 42);
	}
	
	@Koan
	public void assertSameInstance(){
		Object same = new Integer(1);
		Object sameReference = same;
		assertSame(same, sameReference);
	}
	
	@Koan
	public void assertNotSameInstance(){
		Integer same = new Integer(1);
		Integer sameReference = new Integer(1);
		assertNotSame(same, sameReference); 
	}
}
