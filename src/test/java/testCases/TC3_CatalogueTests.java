package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import testUtils.BaseTest;

public class TC3_CatalogueTests extends BaseTest {
	
	@Test
	public void AscendingNameSortVerification() {
		cataloguePage.clickSortButton();
		cataloguePage.selectNameAsc();
		cataloguePage.clickSortButton();
		Assert.assertTrue(cataloguePage.isNameAscSortActive());
		cataloguePage.selectNameAsc();
		
		List<String> productNames = new ArrayList<>();
		for (int i = 0; i < cataloguePage.numOfProducts(); i++) {
			if ((i+1) % 4 == 0) {
				cataloguePage.scrollToBottom();
			}
			productNames.add(cataloguePage.getProductName(i));
		}
		
		List<String> namesSorted = new ArrayList<>(productNames);
		Collections.sort(namesSorted);
		
		int size = productNames.size();
		for (int i = 0; i < size; i++) {
			Assert.assertEquals(productNames.get(i), namesSorted.get(i));
		}
		
		cataloguePage.scrollToTop();
	}
	
	@Test
	public void DescendingNameSortVerification() {
		cataloguePage.clickSortButton();
		cataloguePage.selectNameDesc();
		cataloguePage.clickSortButton();
		Assert.assertTrue(cataloguePage.isNameDescSortActive());
		cataloguePage.selectNameDesc();
		
		List<String> productNames = new ArrayList<>();
		for (int i = 0; i < cataloguePage.numOfProducts(); i++) {
			if ((i+1) % 4 == 0) {
				cataloguePage.scrollToBottom();
			}
			productNames.add(cataloguePage.getProductName(i));
		}
		
		List<String> namesSorted = new ArrayList<>(productNames);
		Collections.sort(namesSorted);
		
		int size = productNames.size();
		int i = 0, j = size - 1;
		while (i < size && j >= 0) {
			Assert.assertEquals(productNames.get(i), namesSorted.get(j));
			i++;
			j--;
		}
		
		cataloguePage.scrollToTop();
	}
	
	@Test
	public void AscendingPriceSortVerification() {
		cataloguePage.clickSortButton();
		cataloguePage.selectPriceAsc();
		cataloguePage.clickSortButton();
		Assert.assertTrue(cataloguePage.isPriceAscSortActive());
		cataloguePage.selectPriceAsc();
		
		List<Double> prices = new ArrayList<>();
		for (int i = 0; i < cataloguePage.numOfProducts(); i++) {
			if ((i+1) % 4 == 0) {
				cataloguePage.scrollToBottom();
			}
			prices.add(cataloguePage.getProductPrice(i));
		}
		
		List<Double> pricesSorted = new ArrayList<>(prices);
		Collections.sort(pricesSorted);
		
		int size = prices.size();
		for (int i = 0; i < size; i++) {
			Assert.assertEquals(prices.get(i),pricesSorted.get(i));
		}
		
		cataloguePage.scrollToTop();
	}
	
	@Test
	public void DescendingPriceSortVerification() {
		cataloguePage.clickSortButton();
		cataloguePage.selectPriceDesc();
		cataloguePage.clickSortButton();
		Assert.assertTrue(cataloguePage.isPriceDescSortActive());
		cataloguePage.selectPriceDesc();
		
		List<Double> prices = new ArrayList<>();
		for (int i = 0; i < cataloguePage.numOfProducts(); i++) {
			if ((i+1) % 4 == 0) {
				cataloguePage.scrollToBottom();
			}
			prices.add(cataloguePage.getProductPrice(i));
		}
		
		List<Double> pricesSorted = new ArrayList<>(prices);
		Collections.sort(pricesSorted);
		
		int size = prices.size();
		int i = 0, j = size - 1;
		while (i < size && j >= 0) {
			Assert.assertEquals(prices.get(i), pricesSorted.get(j));
			i++;
			j--;
		}
		
		cataloguePage.scrollToTop();
	}
	
	@Test
	public void ProductRatingVerification() {
		for (int i = 1; i < 6; i++) {
			cataloguePage.selectRating(i);
			Assert.assertEquals(cataloguePage.getReviewPopupText(), "Thank you for submitting your review!");
			cataloguePage.closeModal();
		}
	}
	
	@Test
	public void FooterVerification() {
		/* scroll down to footer
		 * verify copyright text is displayed and text is correct
		 * verify social media is displayed
		 * 
		 */
		cataloguePage.scrollToBottom();
		
		String copyright = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.";
		Assert.assertEquals(cataloguePage.getFooterText(), copyright);
		
		for (int i = 1; i < 4; i++) {
			Assert.assertTrue(cataloguePage.isfooterSNSDisplayed(i));
		}
		
	}
}
