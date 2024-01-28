package testCases;

import org.testng.annotations.Test;

import testUtils.BaseTest;

public class TestClass2 extends BaseTest {

	@Test
	public void printABC() {
		cataloguePage.openMenu();
		cataloguePage.selectLogin();
		System.out.println("***ABC***");
	}
}
