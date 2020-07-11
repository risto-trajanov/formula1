package finki.lab.formula.fia.port.ui;

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
import finki.lab.formula.fia.application.RaceCatalog;
import finki.lab.formula.fia.application.form.DriverLeaderboardForm;
import finki.lab.formula.fia.application.form.DriverPlacementForm;
import finki.lab.formula.fia.domain.model.Driver;
import finki.lab.formula.racing.port.ui.TabContainer;

@Route("create-race")
@PageTitle("Racing")
public class LeaderboardView extends VerticalLayout {
    private final RaceCatalog raceCatalog;
    private final Binder<DriverLeaderboardForm> binder;
    private final Grid<DriverPlacementForm> placementGrid;

    public LeaderboardView(RaceCatalog raceCatalog) {
        this.raceCatalog = raceCatalog;
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
        placementGrid.addColumn(form -> form.getPlace()).setHeader("Place");

        var driverPlacementLayout = new DriverPlacementLayout();
        tabContainer.addTab("Leaderboard", new Div(placementGrid, driverPlacementLayout));


        binder.setBean(new DriverLeaderboardForm());
        tabs.setSelectedIndex(0);
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
            driver.setItemLabelGenerator(driver1 -> String.valueOf(driver1.getName()));
            add(driver);

//            tax = new TextField("VAT");
//            tax.setReadOnly(true);
//            tax.setWidth("60px");
//            add(tax);


            place = new TextField("Place");
            place.isReadOnly();
            place.setWidth("50px");
            add(place);

        }


    }
}
