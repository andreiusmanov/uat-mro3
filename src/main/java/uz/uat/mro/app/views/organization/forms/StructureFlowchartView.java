package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class StructureFlowchartView extends VerticalLayout {
    private H3 header;

    public StructureFlowchartView() {
        header();
    }

    private void header() {
        header = new H3("Орг структура в виде списка");
    }
}