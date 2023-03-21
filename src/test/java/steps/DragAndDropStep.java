package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DragAndDropPage;

public class DragAndDropStep {

    DragAndDropPage dragAndDropPage = new DragAndDropPage(Environment.getDriver());

    @Given("Select option Droppable from left menu under Interactions")
    public void select_option_droppable_from_left_menu_under_interactions() {
        dragAndDropPage.clickOnDroppableElement();
        dragAndDropPage.verifyTitleOfPage("Droppable | jQuery UI");
    }

    @When("Drag ‘Drag me to my target’ component to ‘Drop here’ component")
    public void drag_drag_me_to_my_target_component_to_drop_here_component() {
        System.out.println("");
    }

    @Then("verify Drag me to my target option is successfull")
    public void verify_drag_me_to_my_target_option_is_successfull() {
        System.out.println("");
    }

}
