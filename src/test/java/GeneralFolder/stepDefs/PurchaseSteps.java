package GeneralFolder.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import GeneralFolder.pages.GreenKartPage;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;


public class PurchaseSteps {
    GreenKartPage greenKartPage = new GreenKartPage();

    @When("I add {string} to the cart")
    public void i_add_to_the_cart(String productName) {
        WebElement product =  greenKartPage.getAddButton(productName);
                product.click();

    }
    @Then("I should see the checkout page")
    public void i_should_see_the_checkout_page() {
        greenKartPage.checkoutPage();
    }
    @When("I search for {string}")
    public void i_search_for(String productName) {
         greenKartPage.searchProduct(productName);
    }
    @When("I add the first search result to the cart")
    public void i_add_the_first_search_result_to_the_cart() {
        greenKartPage.getAddToCartButton("Cucumber - 1 Kg").click();
    }
    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String expectedProductName) {
        greenKartPage.clickToCheckout();
        assertEquals(expectedProductName,greenKartPage.getCartProductName());
    }
    @When("I add {int} quantities of the first search result to the cart")
    public void i_add_quantities_of_the_first_search_result_to_the_cart(Integer quantity) {
        greenKartPage.addFirstSearchResultToCart(quantity);
        greenKartPage.clickToCheckout();

    }
    @Then("the cart should contain {string} with quantity {int}")
    public void the_cart_should_contain_with_quantity(String expectedProductName, int expectedProductQuantity) {
        assertEquals(expectedProductName,greenKartPage.getCartProductName());
        String expectedProductQuantity1 = greenKartPage.getCartQuantity(expectedProductQuantity).getText();
        int productQuantity =Integer.parseInt( expectedProductQuantity1);
        assertEquals(expectedProductQuantity,productQuantity);
    }

    @Then("I should see a message indicating the cart is empty")
    public void i_should_see_a_message_indicating_the_cart_is_empty() {
        assertEquals("You cart is empty!",greenKartPage.getEmptyCartMessage().getText());
    }
}
