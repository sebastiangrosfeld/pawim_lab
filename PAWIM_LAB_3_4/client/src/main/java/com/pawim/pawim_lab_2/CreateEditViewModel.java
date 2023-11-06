package com.pawim.pawim_lab_2;

import com.google.inject.Inject;
import com.pawim.pawim_lab_2.views.MainViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEditViewModel {

    private final ComputerService computerService;
    private MainViewModel viewModel;

    @FXML
    private TextField fxNewName;

    @FXML
    private TextField fxNewType;

    @FXML
    private TextField fxNewEnclosureName;

    @FXML
    private TextField fxNewRamCapacity;

    @FXML
    private TextField fxNewCpuName;

    @FXML
    private TextField fxNewGpuName;

    @FXML
    private TextField fxNewHardDiskCapacity;

    @FXML
    private TextField fxNewPowerSupply;

    @FXML
    private TextField fxNewPrice;

    @FXML
    private TextField fxName;

    public void initialize() {

        viewModel = new MainViewModel();
        fxName.textProperty().bindBidirectional(viewModel.fieldNameProperty());
    }

    @Inject
    public CreateEditViewModel(ComputerService computerService) {
        this.computerService = computerService;
    }

    @FXML
    public void onComputerAppClick(ActionEvent actionEvent) throws Exception {
        loadScene(ApplicationViews.COMPUTER_APP_VIEW, actionEvent);
    }

    private void loadScene(ApplicationViews view, ActionEvent event) throws Exception {
        final var source = (Node) event.getSource();
        final var stage = (Stage) source.getScene().getWindow();
        view.loadScene(stage);
    }

    @FXML
    protected void onCreateComputerButtonClick() {
        Computer computer = new Computer(
                fxNewName.getText(),
                fxNewType.getText(),
                fxNewEnclosureName.getText(),
                fxNewCpuName.getText(),
                Integer.valueOf(fxNewRamCapacity.getText()),
                Integer.valueOf(fxNewHardDiskCapacity.getText()),
                fxNewGpuName.getText(),
                Integer.valueOf(fxNewPowerSupply.getText()),
                Integer.valueOf(fxNewPrice.getText())
        );
        System.out.println(computer);
        computerService.createComputer(computer);
    }

    @FXML
    protected void onUpdateComputerButtonClick() {
        Computer computer = new Computer(
                fxNewName.getText(),
                fxNewType.getText(),
                fxNewEnclosureName.getText(),
                fxNewCpuName.getText(),
                Integer.valueOf(fxNewRamCapacity.getText()),
                Integer.valueOf(fxNewHardDiskCapacity.getText()),
                fxNewGpuName.getText(),
                Integer.valueOf(fxNewPowerSupply.getText()),
                Integer.valueOf(fxNewPrice.getText())
        );
        computerService.updateComputer(viewModel.getFieldName(), computer);
    }
}
