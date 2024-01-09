package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
	private WebElement productsHeader;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='store item']")
	private List<WebElement> products;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Terms')]")
	private WebElement footer;
	
	
	public ProductPage selectProduct(int index) {
		WebElement product = products.get(index);
		WebElement image = product.findElement(AppiumBy.className("android.widget.ImageView"));
		image.click();
		
		return new ProductPage(driver);
		
	}
	
	//Element getters
	public String getProductsText() {
		return productsHeader.getText();
	}
	
	public WebElement getFooterElement() {
		return footer;
	}
	
	public String getFooterText() {
		return footer.getText();
	}
	
}
