Feature: Google Search

  Scenario: Search "Microsoft"
    Given I'm in Google Search page
    When I attempt to search for "Microsoft"
    Then I should see the site "https://www.microsoft.com" in the first result page