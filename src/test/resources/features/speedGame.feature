@Speed
Feature: Speed Game

  Check that a message is shown when hitting the "End Game" button as soon as it appears.

  Scenario: Message shown on end game
  """
Check that a message is shown when hitting the "End Game" button as soon as it appears.
"""
    Given I navigate to "https://thelab.boozang.com/speedGame"
    When I start the game
    And I end the game
    Then a success message should be shown