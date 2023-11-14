Feature: Login to Zeetech
  @Zeetech
  Scenario: Enter Username and Password and click on Login button
    Given Enter UserName Password and Click on login Button
    When User clicks on Login Button
    Given Wait for welcome screen to appear
    When User clicks on markAsPresent link
    Then User clicks on My Project Link
