package beginner;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.sandwich.koan.Koan;

public class AboutInheritance {

	class Parent {
		public String doStuff() { return "parent"; }
	}
	class Child extends Parent {
		public String doStuff() { return "child"; }
		public String doStuff(String s) { return s; }
	}
	
	@Koan
	public void differenceBetweenOverloadingAndOverriding() {
		assertEquals(new Parent().doStuff(), "parent");
		assertEquals(new Child().doStuff(), "child");
		assertEquals(new Child().doStuff("oh no"), "oh no");
	}
	
	abstract class ParentTwo {
		abstract public Collection<?> doStuff();
	}
	
	class ChildTwo extends ParentTwo {
		public List<?> doStuff() { return Collections.emptyList(); };
	}
	
	@Koan
	public void overriddenMethodsMayReturnSubtype() {
		// What do you need to change in order to get rid of the type cast?
		// Why does this work?
		List<?> list = new ChildTwo().doStuff();
		assertEquals(list instanceof List, true);
	}
}
