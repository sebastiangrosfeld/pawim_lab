package com.example.webclient.controller;

import com.example.webclient.model.Computer;
import com.example.webclient.service.ComputerService;
import com.example.webclient.service.GpuService;
import com.example.webclient.service.RamService;
import com.example.webclient.view.ComputerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;
    private final GpuService gpuService;
    private final RamService ramService;

    @GetMapping
    public String computerList(Model model) {
        var computers = computerService.getAllComputers();
        model.addAttribute("computers", computers);
        return "computer-list";
    }

    @GetMapping("/create")
    public String createComputerForm(Model model) {
        var gpus = gpuService.getAllGpus();
        var rams = ramService.getAllRams();
        model.addAttribute("gpus", gpus);
        model.addAttribute("rams", rams);
        model.addAttribute("computerForm", new ComputerForm());
        return "computer-form";
    }

    @GetMapping("/edit/{name}")
    public String editComputerForm(Model model, @PathVariable String name) {
        var gpus = gpuService.getAllGpus();
        var rams = ramService.getAllRams();
        var computer = computerService.getComputer(name);
        model.addAttribute("gpus", gpus);
        model.addAttribute("rams", rams);
        model.addAttribute("computerForm", createComputerFormFromComputer(computer));
        return "computer-form";
    }

    @PostMapping("/create")
    public String createComputer(@ModelAttribute("computerForm") ComputerForm computerForm) {
        var computer = createComputerFromComputerForm(computerForm);
        computerService.createComputer(computer);
        return "redirect:/computers";
    }

    @PutMapping("/update/{name}")
    public String updateComputer(@PathVariable String name, @ModelAttribute("computerForm") ComputerForm computerForm) {
        var computer = createComputerFromComputerForm(computerForm);
        computer.setName(name);
        computerService.updateComputer(name, computer);
        return "redirect:/computers";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteComputer(@PathVariable String name) {
        computerService.deleteComputer(name);
        return "redirect:/computers";
    }

    @DeleteMapping("delete/all")
    public String deleteAllComputers() {
        computerService.deleteAllComputers();
        return "redirect:/computers";
    }

    private ComputerForm createComputerFormFromComputer(Computer computer) {
        return ComputerForm.builder()
                .name(computer.getName())
                .type(computer.getType())
                .enclosureName(computer.getEnclosureName())
                .cpuName(computer.getCpuName())
                .gpuName(computer.getGpu().getName())
                .hardDiskCapacity(computer.getHardDiskCapacity())
                .ramName(computer.getRam().getName())
                .powerSupply(computer.getPowerSupply())
                .price(computer.getPrice())
                .build();
    }

    private Computer createComputerFromComputerForm(ComputerForm computerForm) {
        var gpu = gpuService.getGpu(computerForm.getGpuName());
        var ram = ramService.getRam(computerForm.getRamName());
        return new Computer(
                computerForm.getName(),
                computerForm.getType(),
                computerForm.getEnclosureName(),
                computerForm.getCpuName(),
                ram,
                computerForm.getHardDiskCapacity(),
                gpu,
                computerForm.getPowerSupply(),
                computerForm.getPrice()
        );
    }
}
