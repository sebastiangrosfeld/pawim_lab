package com.example.desktop_app.viewModel;

import com.example.desktop_app.ApplicationViews;
import com.example.desktop_app.model.Computer;
import com.example.desktop_app.model.Gpu;
import com.example.desktop_app.model.Ram;
import com.example.desktop_app.service.ComputerService;
import com.example.desktop_app.service.GpuService;
import com.example.desktop_app.service.RamService;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEditViewModel {

    private final ComputerService computerService;
    private final RamService ramService;
    private final GpuService gpuService;
    private MainViewModel viewModel;

    @FXML
    private TextField fxNewName;

    @FXML
    private TextField fxNewType;

    @FXML
    private TextField fxNewEnclosureName;

    @FXML
    private TextField fxNewRamName;

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
    public CreateEditViewModel(ComputerService computerService, RamService ramService, GpuService gpuService) {
        this.computerService = computerService;
        this.ramService = ramService;
        this.gpuService = gpuService;
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
        Ram ram = new Ram(fxNewRamName.getText(),8,1300);
        ramService.createRam(ram);
        Gpu gpu = new Gpu(fxNewGpuName.getText(),2300);
        gpuService.createGpu(gpu);
        Computer computer = new Computer(
                fxNewName.getText(),
                fxNewType.getText(),
                fxNewEnclosureName.getText(),
                fxNewCpuName.getText(),
                ram,
//                Integer.valueOf(fxNewRamCapacity.getText()),
                Integer.valueOf(fxNewHardDiskCapacity.getText()),
                gpu,
//                fxNewGpuName.getText(),
                Integer.valueOf(fxNewPowerSupply.getText()),
                Integer.valueOf(fxNewPrice.getText())
        );
        System.out.println(computer);
        computerService.createComputer(computer);
    }

    @FXML
    protected void onUpdateComputerButtonClick() {
        Ram ram = new Ram(fxNewRamName.getText(),8,1300);
        ramService.createRam(ram);
        Gpu gpu = new Gpu(fxNewGpuName.getText(),2300);
        gpuService.createGpu(gpu);
        Computer computer = new Computer(
                fxNewName.getText(),
                fxNewType.getText(),
                fxNewEnclosureName.getText(),
                fxNewCpuName.getText(),
                ram,
//                Integer.valueOf(fxNewRamCapacity.getText()),
                Integer.valueOf(fxNewHardDiskCapacity.getText()),
                gpu,
//                fxNewGpuName.getText(),
                Integer.valueOf(fxNewPowerSupply.getText()),
                Integer.valueOf(fxNewPrice.getText())
        );
        computerService.updateComputer(viewModel.getFieldName(), computer);
    }
}
