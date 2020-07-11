package finki.lab.formula.racing.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import finki.lab.formula.racing.application.Racing;
import finki.lab.formula.racing.application.form.DriverPlacementForm;
import finki.lab.formula.racing.application.form.RaceForm;
import finki.lab.formula.racing.domain.model.Driver;
import finki.lab.formula.racing.domain.model.Season;
import finki.lab.formula.racing.domain.repository.DriverRepository;
import finki.lab.formula.racing.domain.repository.RaceRepository;
import finki.lab.formula.racing.domain.repository.SeasonRepository;

@Route("create-race")
@PageTitle("Racing")
public class CreateRaceView extends VerticalLayout {
    private final Racing racing;
    private final Binder<RaceForm> binder;
    private final Grid<DriverPlacementForm> placementGrid;
    private final DriverRepository driverRepository;
    private final SeasonRepository seasonRepository;
    private final RaceRepository raceRepository;

    public CreateRaceView(Racing racing, DriverRepository driverRepository, SeasonRepository seasonRepository, RaceRepository raceRepository) {
        this.racing = racing;
        this.driverRepository = driverRepository;
        this.seasonRepository = seasonRepository;
        this.raceRepository = raceRepository;

        setSizeFull();

        binder = new Binder<>();

        var title = new Html("<h3>Create Order</h3>");
        add(title);

        var tabs = new Tabs();
        tabs.setWidth("100%");
        add(tabs);
        var tabContainer = new TabContainer(tabs);
        tabContainer.setWidth("100%");
        tabContainer.setHeight("100%");
        add(tabContainer);

        placementGrid = new Grid<>();
        placementGrid.addColumn(form -> form.getDriver().getName()).setHeader("Driver");
        placementGrid.addColumn(form -> form.getDriver().getTeam().getName()).setHeader("Team");
        placementGrid.addColumn(form -> form.getPlace()).setHeader("Place");

        var driverPlacementLayout = new DriverPlacementLayout();
        tabContainer.addTab("Placement", new Div(placementGrid, driverPlacementLayout));

        var season = new ComboBox<>("Season", seasonRepository.findAllByseasonNumber());
        binder.forField(season)
                .asRequired()
                .bind(RaceForm::getSeason, RaceForm::setSeason);
        tabContainer.addTab("Season", season);

        var raceName = new TextArea();
        binder.forField(raceName)
                .asRequired()
                .bind(RaceForm::getName, RaceForm::setName);
        tabContainer.addTab("Race Name", raceName);

        var createRace = new Button("Add Race", evt -> createRace());
        createRace.setEnabled(false);
        createRace.getElement().getThemeList().set("primary", true);

        add(createRace);

        binder.setBean(new RaceForm());
        binder.addValueChangeListener(evt -> createRace.setEnabled(binder.isValid()));
        tabs.setSelectedIndex(0);
    }

    private void addPlacement(DriverPlacementForm placement) {
        binder.getBean().getPlacements().add(placement);
        placementGrid.setItems(binder.getBean().getPlacements());
    }

    private void createRace() {
        try {
            var raceId = racing.createRace(binder.getBean());
            getUI().ifPresent(ui -> ui.navigate(RaceDetailsView.class, raceId.getId()));
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }
    }

    class DriverPlacementLayout extends HorizontalLayout {

        private Binder<DriverPlacementForm> binder;
        private ComboBox<Driver> driver;
        private TextField place;
        private TextField race;
        //        private TextField tax;
        private Button addPlacement;

        DriverPlacementLayout() {
            setWidth("630px");

            setAlignItems(Alignment.END);
            driver = new ComboBox<>("Driver", driverRepository.findAll());
            driver.setItemLabelGenerator(driver1 -> String.valueOf(driver1.getName()));
            add(driver);

//            tax = new TextField("VAT");
//            tax.setReadOnly(true);
//            tax.setWidth("60px");
//            add(tax);


            place = new TextField("Place");
            place.setWidth("50px");
            add(place);

            addPlacement = new Button("Add", evt -> {
                addPlacement(binder.getBean());
                binder.setBean(new DriverPlacementForm());
                addPlacement.setEnabled(false);
            });
            addPlacement.setEnabled(false);
            add(addPlacement);


            binder = new Binder<>();
            binder.forField(driver)
                    .asRequired()
                    .bind(DriverPlacementForm::getDriver, DriverPlacementForm::setDriver);
            binder.forField(place)
                    .asRequired()
                    .withConverter(new StringToIntegerConverter("Please enter a valid place"))
                    .bind(DriverPlacementForm::getPlace, DriverPlacementForm::setPlace);
            binder.addValueChangeListener(evt -> addPlacement.setEnabled(binder.isValid()));
            binder.setBean(new DriverPlacementForm());
        }


    }
}
