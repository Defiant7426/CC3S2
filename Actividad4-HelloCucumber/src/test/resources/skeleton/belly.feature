Feature: BellyFeature

  Scenario: eaten many cukes and growl
    Given I have eaten 27 cukes
    When I wait 3 hour
    Then my Belly should Growl

  Scenario: eaten many cukes and not growl
    Given I have eaten 27 cukes
    When I wait 1 hour
    Then my belly Should not Growl

  Scenario: Eating few cukes
    Given I have eaten 5 cukes
    When I wait 1 hour
    Then my belly should not growl

  Scenario: Eating many cukes
    Given I have eaten 12 cukes
    When I wait 2 hour
    Then my belly should growl

  Scenario: Eating no cukes
    Given I have eaten 0 cukes
    When I wait 3 hour
    Then my belly should not growl

  Scenario: I wait 0 hour and I eat many cukes
    Given I have eaten 30 cukes
    When I wait 0 hour
    Then my belly should not growl

  Scenario: I wait 0 hour and I eat 0 cukes
    Given I have eaten 0 cukes
    When I wait 0 hour
    Then my belly should not growl