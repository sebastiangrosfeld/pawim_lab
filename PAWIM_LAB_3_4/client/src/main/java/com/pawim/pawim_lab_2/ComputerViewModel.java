package com.pawim.pawim_lab_2;

import com.google.inject.Inject;
import com.pawim.pawim_lab_2.views.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

import static com.pawim.pawim_lab_2.Constants.ROWS_PER_PAGE;

public class ComputerViewModel {
    private final ComputerService computerService;
    private MainViewModel viewModel;
    private final ObservableList<Computer> computers = FXCollections.observableArrayList();

    @FXML
    private TextField fxName;

    @FXML
    private TableView<Computer> fxTable;

    @FXML
    private Pagination fxPagination;

    public void initialize() {
        createColumns();
        viewModel = new MainViewModel();
        computers.addAll(computerService.getAllComputers());
        configurePages();
        fxName.textProperty().bindBidirectional(viewModel.fieldNameProperty());
    }

    @Inject
    public ComputerViewModel(ComputerService computerService) {
        this.computerService = computerService;
    }

    @FXML
    public void onWeatherClick(ActionEvent actionEvent) throws Exception {
        loadScene(ApplicationViews.WEATHER_APP_VIEW, actionEvent);
    }

    @FXML
    public void onCreateEditClick(ActionEvent actionEvent) throws Exception {
        loadScene(ApplicationViews.CREATE_EDIT_VIEW, actionEvent);
    }

    private void loadScene(ApplicationViews view, ActionEvent event) throws Exception {
        final var source = (Node) event.getSource();
        final var stage = (Stage) source.getScene().getWindow();
        view.loadScene(stage);
    }
    @FXML
    protected void onGetAllComputersButtonClick() {
        computers.clear();
        computers.addAll(computerService.getAllComputers());
        configurePages();
    }

    @FXML
    protected void onGetComputerButtonClick() {
        computers.clear();
        computers.add(computerService.getComputer(viewModel.getFieldName()));
        configurePages();
    }

    @FXML
    protected void onDeleteComputerButtonClick() {
        computerService.deleteComputer(viewModel.getFieldName());
    }

    @FXML
    protected void onDeleteAllComputersButtonClick() {
        computerService.deleteAllComputers();
    }

    private void configurePages() {
        final var pageCount = (int) Math.ceil((double) computers.size() / ROWS_PER_PAGE);
        fxPagination.setPageCount(pageCount);
        fxPagination.setPageFactory(this::createPage);
    }

    private Node createPage(Integer pageIndex) {
        final var fromIndex = pageIndex * ROWS_PER_PAGE;
        final var toIndex = Math.min(fromIndex + ROWS_PER_PAGE, computers.size());
        fxTable.setItems(FXCollections.observableArrayList(computers.subList(fromIndex, toIndex)));
        final var vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(fxTable);
        return vBox;
    }

    private void createColumns() {
        TableColumn<Computer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        TableColumn<Computer, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type()));
        TableColumn<Computer, String> enclosureNameColumn = new TableColumn<>("Enclosure Name");
        enclosureNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().enclosureName()));
        TableColumn<Computer, String> cpuNameColumn = new TableColumn<>("CPU Name");
        cpuNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cpuName()));
        TableColumn<Computer, String> gpuNameColumn = new TableColumn<>("GPU Name");
        gpuNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().gpuName()));
        TableColumn<Computer, String> ramCapacityColumn = new TableColumn<>("Ram Capacity");
        ramCapacityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ramCapacity() + " GB"));
        TableColumn<Computer, String> hardDiskCapacityColumn = new TableColumn<>("Hard Disk Capacity");
        hardDiskCapacityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().hardDiskCapacity() + " GB"));
        TableColumn<Computer, String> powerSupplyColumn = new TableColumn<>("Power Supply");
        powerSupplyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().powerSupply() + " W"));
        TableColumn<Computer, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().price() + " $"));
        fxTable.getColumns().addAll(
                nameColumn,
                typeColumn,
                enclosureNameColumn,
                cpuNameColumn,
                gpuNameColumn,
                ramCapacityColumn,
                hardDiskCapacityColumn,
                powerSupplyColumn,
                priceColumn
        );
    }

    private boolean isFieldNameIsNotEmpty(MainViewModel viewModel) {
        return viewModel.getFieldName() != null && !Objects.equals(viewModel.getFieldName(), "");
    }
}
