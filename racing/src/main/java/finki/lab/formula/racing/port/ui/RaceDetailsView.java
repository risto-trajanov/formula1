package finki.lab.formula.racing.port.ui;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import finki.lab.formula.racing.application.Racing;
import finki.lab.formula.racing.application.form.RaceForm;
import finki.lab.formula.racing.domain.model.DriverPlacement;
import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.racing.domain.model.RaceId;

import java.util.Optional;

@Route("race")
@PageTitle("Race")
public class RaceDetailsView extends VerticalLayout implements HasUrlParameter<String> {

    private final Racing racing;

    public RaceDetailsView(Racing racing) {
        this.racing = racing;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        Optional<Race> race = Optional.ofNullable(parameter).map(RaceId::new).flatMap(raceId -> racing.findById(raceId));
        if (race.isPresent()) {
            showRace(race.get());
        } else {
            showNoSuchOrder();
        }
    }

    private void showRace(Race race) {
        var title = new Html("<h3>Race Details</h3>");
        add(title);

        var header = new FormLayout();
        header.addFormItem(createReadOnlyTextField(race.getName().toString()), "Race");
        header.addFormItem(createReadOnlyTextField(race.getRaceDate().toString()), "Date");
        header.addFormItem(createReadOnlyTextField(race.getSeason().toString()), "Season");
        add(header);

        var items = new Grid<DriverPlacement>();
        items.addColumn(placement -> placement.getDriver().getName()).setHeader("Driver");
        items.addColumn(placement -> placement.getDriversTeam().getName()).setHeader("Team");
        items.addColumn(placement -> placement.getPosition().getPlace()).setHeader("Place");
        add(items);
    }
    private TextField createReadOnlyTextField(String value) {
        var textField = new TextField();
        textField.setReadOnly(true);
        textField.setValue(value);
        return textField;
    }

    private TextArea createReadOnlyPlacementArea(DriverPlacement placement) {
        var textArea = new TextArea();
        textArea.setHeight("140px");
        textArea.setValue(formatPlacement(placement));
        textArea.setReadOnly(true);
        return textArea;
    }

    private String formatPlacement(DriverPlacement placement) {
        var sb = new StringBuilder();
        sb.append(placement.getDriver().getName()).append("\n");
        sb.append(placement.getDriversTeam().getName()).append("\n");
        sb.append(placement.getPosition().getPlace()).append("\n");
        sb.append(placement.getPosition().getPoints());
        return sb.toString();
    }

    private void showNoSuchOrder() {
        add(new Text("The order does not exist."));
    }

}
