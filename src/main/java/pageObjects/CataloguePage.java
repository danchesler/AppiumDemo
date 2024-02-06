package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class CataloguePage extends PageCommon {
	private AndroidDriver driver;
	
	public CataloguePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility="sort button") 
	private WebElement sortButton;
	
	@AndroidFindBy(accessibility="nameAsc")
	private WebElement nameAscending;
	
	@AndroidFindBy(accessibility="nameDesc")
	private WebElement nameDescending;
	
	@AndroidFindBy(accessibility="priceAsc")
	private WebElement priceAscending;
	
	@AndroidFindBy(accessibility="priceDesc")
	private WebElement priceDescending;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
	private WebElement productsHeader;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='store item']")
	private List<WebElement> products;
	
	@AndroidFindBy(className="android.widget.TextView")
	private WebElement reviewPopupText;
	
	@AndroidFindBy(accessibility="Close Modal button")
	private WebElement closeModal;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Terms')]")
	private WebElement footer;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Terms')]/parent::android.view.ViewGroup")
	private WebElement footerArea;
	
	public ProductPage selectProduct(int index) {
		WebElement product = products.get(index);
		WebElement image = product.findElement(AppiumBy.className("android.widget.ImageView"));
		image.click();
		
		return new ProductPage(driver);
	}
	
	//Element getters
	public String getProductsText() {
		waitForElementToAppear(productsHeader, driver);
		return productsHeader.getText();
	}
	
	public boolean isfooterSNSDisplayed (int num) {
		WebElement sns = footerArea.findElement(By.xpath("//android.widget.ImageView[" + num +"]"));
		
		return sns.isDisplayed();
	}
	
	public String getFooterText() {
		return footer.getText();
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='store item text']")
	private List<WebElement> prodNames;
	
	public String getProductName(int num) {
		WebElement itemName = products.get(num).findElement(By.xpath("//android.widget.TextView[@content-desc='store item text']"));
		
		return itemName.getText();
	}
	
	public double getProductPrice(int num) {
//		List<WebElement> prices = driver.findElements(By.xpath("//android.widget.TextView[@content-desc='store item price']"));
//		for (WebElement e : prices) {
//			System.out.println(e.getText());
//		}
		
		WebElement itemPrice = products.get(num).findElement(By.xpath("//android.widget.TextView[@content-desc='store item price']"));

		return formatPrice(itemPrice.getText());
	}
	
	public int numOfProducts() {
		return products.size();
	}

	
	public boolean isNameAscSortActive() {
		try {
			nameAscending.findElement(AppiumBy.accessibilityId("active option"));
		} catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isNameDescSortActive() {
		try {
			nameDescending.findElement(AppiumBy.accessibilityId("active option"));
		} catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isPriceAscSortActive() {
		try {
			priceAscending.findElement(AppiumBy.accessibilityId("active option"));
		} catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isPriceDescSortActive() {
		try {
			priceDescending.findElement(AppiumBy.accessibilityId("active option"));
		} catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	public String getReviewPopupText() {
		return reviewPopupText.getText();
	}
	
	//Actions methods
	public void clickSortButton() {
		sortButton.click();
	}
	
	public void selectNameAsc() {
		nameAscending.click();
	}
	
	public void selectNameDesc() {
		nameDescending.click();
	}
	
	public void selectPriceAsc() {
		priceAscending.click();
	}
	
	public void selectPriceDesc() {
		priceDescending.click();
	}
	
	public void selectRating(int rating) {
		WebElement firstProduct = products.get(0); 
		WebElement reviewStar = firstProduct.findElement(By.xpath("//android.view.ViewGroup[@content-desc='review star " + rating + "']"));
		reviewStar.click();
	}
	
	public void closeModal() {
		closeModal.click();
	}
}
