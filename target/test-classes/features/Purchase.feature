Feature: GreenKart Purchase Functionality

  @purchase
  Scenario: Successful purchase of products with specific quantities
    Given I am on the GreenKart landing page
    When I add the following products to the cart:
      | product       | quantity |
      | Cucumber - 1 Kg | 2       |
      | Brocolli - 1 Kg | 3       |
      | Beetroot - 1 Kg | 1       |
    And I proceed to checkout
    And I enter the promo code
    And I place the order
    And I select the country and agree to terms
    Then I should see the confirmation message

@purchase
  Scenario: Add a single product to the cart and proceed to checkout
    Given I am on the GreenKart landing page
    When I add "Cucumber - 1 Kg" to the cart
    And I proceed to checkout
    Then I should see the checkout page
  @purchase
  Scenario: Search for a product and add it to the cart
    Given I am on the GreenKart landing page
    When I search for "Cucumber"
    And I add the first search result to the cart
    Then the cart should contain "Cucumber - 1 Kg"
  @purchase
  Scenario: Add multiple quantities of a product to the cart
    Given I am on the GreenKart landing page
    When I search for "Cucumber"
    And I add 3 quantities of the first search result to the cart
    Then the cart should contain "Cucumber - 1 Kg" with quantity 3
  @purchase
  Scenario: Verify correct product name and price in the cart
    Given I am on the GreenKart landing page
    When I add "Cucumber - 1 Kg" to the cart
    Then the cart should contain "Cucumber - 1 Kg"
  @purchase
  Scenario: Proceed to checkout with an empty cart
    Given I am on the GreenKart landing page
    When I proceed to checkout
    Then I should see a message indicating the cart is empty
