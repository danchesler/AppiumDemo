package testCases;

import org.testng.annotations.Test;

import testUtils.BaseTest;

public class TestClass1 extends BaseTest {

	@Test
	public void print123() {
		cataloguePage.openMenu();
		cataloguePage.selectCatalogue();
		System.out.println("***123***");
	}
}
