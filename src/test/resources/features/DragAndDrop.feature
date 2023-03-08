Feature: Launch the Google

  Scenario: Drag and Drop to Destination
    Given Select option Droppable from left menu under Interactions
    When Drag ‘Drag me to my target’ component to ‘Drop here’ component
    Then verify Drag me to my target option is successfull