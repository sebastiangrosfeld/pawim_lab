package com.example.webclient.controller;

import com.example.webclient.model.Ram;
import com.example.webclient.service.RamService;
import com.example.webclient.view.GpuForm;
import com.example.webclient.view.RamForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rams")
@RequiredArgsConstructor
public class RamController {

    private final RamService ramService;

    @GetMapping
    public String ramList(Model model) {
        var rams = ramService.getAllRams();
        model.addAttribute("rams", rams);
        return "ram-list";
    }

    @GetMapping("/create")
    public String createRamForm(Model model) {
        var rams = ramService.getAllRams();
//        model.addAttribute("gpus", gpus);
        model.addAttribute("ramForm", new RamForm());
        return "ram-form";
    }

    @GetMapping("/edit/{name}")
    public String editRamForm(Model model, @PathVariable String name) {
//        var gpus = gpuService.getAllGpus();
        var ram = ramService.getRam(name);
        model.addAttribute("ramForm", createRamFormFromRam(ram));
        return "ram-form";
    }

    @PostMapping("/create")
    public String createRam(@ModelAttribute("ramForm") RamForm ramForm) {
        var ram = createRamFromRamForm(ramForm);
        ramService.createRam(ram);
        return "redirect:/rams";
    }

    @PutMapping("/update/{name}")
    public String updateRam(@PathVariable String name, @ModelAttribute("ramForm") RamForm ramForm) {
        var ram = createRamFromRamForm(ramForm);
        ram.setName(name);
        ramService.updateRam(name, ram);
        return "redirect:/rams";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteRam(@PathVariable String name) {
        ramService.deleteRam(name);
        return "redirect:/rams";
    }

    @DeleteMapping("delete/all")
    public String deleteAllRams() {
        ramService.deleteAllRams();
        return "redirect:/rams";
    }

    private Ram createRamFromRamForm(RamForm ramForm) {
        return new Ram(
                ramForm.getName(),
                ramForm.getRamCapacity(),
                ramForm.getMemoryRate()
        );
    }

    private RamForm createRamFormFromRam(Ram ram) {
        return RamForm.builder()
                .name(ram.getName())
                .ramCapacity(ram.getRamCapacity())
                .memoryRate(ram.getMemoryRate())
                .build();
    }
}
