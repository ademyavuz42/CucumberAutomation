package GeneralFolder.pages;

import GeneralFolder.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class GreenKartPage extends PageObject {
    public GreenKartPage() {
    }

    @FindBy(css = "input.search-keyword")
    WebElement searchBox;

    @FindBy(css = "button.search-button")
    WebElement searchButton;

    @FindBy(css = "div.products div.product")
    private List<WebElement> searchResults;

    @FindBy(css = "img[alt='Cart']")
    WebElement cartIcon;

    @FindBy(xpath = "//button[contains(text(),'PROCEED TO CHECKOUT')]")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//p[contains(text(),'Cucumber - 1 Kg')]")
    WebElement productName;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement inputQuantity;

    @FindBy(xpath = "//button[contains(text(),'ADD TO CART')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//h2[contains(text(),'You cart is empty!')]")
    public WebElement noSearchResultsMessage;

    @FindBy(xpath = "//div[@class='product'][1]//input")
    private WebElement searchFirstInputQuantity;




    // Method to get the dynamic "Add to Cart" button based on product name
    public WebElement getAddToCartButton(String productName) {
        return Driver.getDriver().findElement(By.xpath("//h4[text()='" + productName + "']/following-sibling::div/button"));
    }

    // Method to get the dynamic product name in the cart
    public WebElement getCartProductName(String productName) {
        return Driver.getDriver().findElement(By.xpath("//p[contains(text(),'" + productName + "')]"));
    }

    // Method to get the dynamic quantity of the product in the cart
    public WebElement getCartQuantity(Integer productName) {
        return Driver.getDriver().findElement(By.xpath("//p[contains(text(),'" + productName + "')]//../../td[3]"));
    }

    // Method to get the dynamic price of the product in the cart
    public WebElement getCartProductPrice(String productName) {
        return Driver.getDriver().findElement(By.xpath("//p[contains(text(),'" + productName + "')]/following-sibling::p[@class='amount']"));
    }

    // Method to get the empty cart message
    public WebElement getEmptyCartMessage() {
        return Driver.getDriver().findElement(By.xpath("//h2[text()='You cart is empty!']"));
    }


    // ---- eskiler


    @FindBy(css = "input.promoCode")
    private WebElement promoCodeInput;

    @FindBy(css = "button.promoBtn")
    private WebElement applyPromoButton;

    @FindBy(css = "span.promoInfo")
    private WebElement promoInfoMessage;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(tagName = "select")
    private WebElement countryDropdown;

    @FindBy(css = "input[type='checkbox']")
    private WebElement agreeTermCheckBox;

    @FindBy(xpath = "//button[text()='Proceed']")
    private WebElement proceedButton;


// -------------- METHODS ---------------

    // Dynamically locate the quantity input field based on product name
    public WebElement getQuantityInput(String productName) {
        return Driver.getDriver().findElement(By.xpath("//h4[text()='" + productName + "']/following-sibling::div/input"));
    }

    // Dynamically locate the add button based on product name
    public WebElement getAddButton(String productName) {
        return Driver.getDriver().findElement(By.xpath("//h4[text()='" + productName + "']/following-sibling::div/button"));
    }

    public void clickToCheckout() {
        cartIcon.click();
        proceedToCheckoutButton.click();
    }

    public void clickToApplyPromotion() {
        promoCodeInput.sendKeys("rahulshettyacademy");
        applyPromoButton.click();
        waitUntilVisibilityOf(promoInfoMessage);
        assertEquals("Code applied ..!", promoInfoMessage.getText());
    }

    public void clickToPlaceOrder() {
        placeOrderButton.click();
    }

    public void selectCountryAndProceedOrder() {
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("Turkey");
        agreeTermCheckBox.click();
        proceedButton.click();
    }

    public boolean lastPage() {
        String url = Driver.getDriver().getCurrentUrl();
        return url.contains("country");
    }

    public void checkoutPage() {
        assertEquals("Place Order", placeOrderButton.getText());
    }

    public void searchProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public String getCartProductName() {
        return productName.getText();
    }

    public void setInputQuantity(Integer quantity) {
        inputQuantity.clear();
        inputQuantity.sendKeys("quantity");
        addToCartButton.click();
    }

    public void setSearchFirstInputQuantity(Integer quantity) {
        searchFirstInputQuantity.clear();
        searchFirstInputQuantity.sendKeys("quantity");
        addToCartButton.click();
    }

    public void addFirstSearchResultToCart(int quantity) {
        if (searchResults.isEmpty()) {
            throw new RuntimeException("No search results found!");
        }

        WebElement firstProduct = searchResults.get(2);

        // Click on the product to expand quantity options
        firstProduct.findElement(By.cssSelector("a.increment")).click();
        WebElement quantityInput = firstProduct.findElement(By.cssSelector("input.quantity"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));

        // Add to cart
        firstProduct.findElement(By.cssSelector("div.product-action button")).click();
    }


}
