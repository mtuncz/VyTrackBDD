Feature: As a user when I am on Vytrack -> Fleet -> Vehicles
  I should be able to see Export Grid dropdown, Refresh, Reset, and Grid Settings Buttons

  Scenario Outline: User to see Export Grid dropdown, Refresh, Reset and Grid Settings Buttons
    Given user is on login page
    When user enters "<username>" and "<password>" and logs in
    And user lands on homepage
    Then user opens vehicles under fleet dropdown menu
    Then user should be able to click on export grid dropdown
    And export grid dropdown button should be on the left of the page
    And user should be able to click refresh button
    And user should be able to click reset button
    And user should be able to click Grid Settings button
    And refresh button should be on the left side of reset button
    And grid settings should be on the right side of the reset button
    And grid setting button is on the right of page
    Then user logs out from the application

    Examples:
      | username        | password    |
      | storemanager57  | UserUser123 |
      | storemanager58  | UserUser123 |
      | salesmanager110 | UserUser123 |
      | salesmanager111 | UserUser123 |
      | salesmanager112 | UserUser123 |
      | user10          | UserUser123 |
      | user11          | UserUser123 |
      | user12          | UserUser123 |