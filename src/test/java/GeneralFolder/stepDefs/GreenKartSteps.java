package GeneralFolder.stepDefs;

import GeneralFolder.pages.GreenKartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class GreenKartSteps {

    GreenKartPage greenCardPage = new GreenKartPage();

    @Given("I am on the GreenKart landing page")
    public void i_am_on_the_green_kart_landing_page() {
        // Calling from Hooks
    }
    @When("I add the following products to the cart:")
    public void i_add_the_following_products_to_the_cart(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> product : products) {
            String productName = product.get("product");
            int quantity = Integer.parseInt(product.get("quantity"));
            WebElement quantityInput = greenCardPage.getQuantityInput(productName);
            WebElement addButton = greenCardPage.getAddButton(productName);

            if (quantityInput !=null && addButton != null){
                quantityInput.clear();
                quantityInput.sendKeys(String.valueOf(quantity));
                addButton.click();
            }
        }
    }
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        greenCardPage.clickToCheckout();
    }
    @When("I enter the promo code")
    public void i_enter_the_promo_code() {
        greenCardPage.clickToApplyPromotion();
    }
    @When("I place the order")
    public void i_place_the_order() {
        greenCardPage.clickToPlaceOrder();
    }

    @When("I select the country and agree to terms")
    public void i_select_the_country_and_agree_to_terms() {
       greenCardPage.selectCountryAndProceedOrder();
    }

    @Then("I should see the confirmation message")
    public void i_should_see_the_confirmation_message() {
            assertTrue(greenCardPage.lastPage());
    }

}
