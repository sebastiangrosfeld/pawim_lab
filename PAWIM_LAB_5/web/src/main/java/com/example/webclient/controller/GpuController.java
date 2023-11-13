package com.example.webclient.controller;

import com.example.webclient.model.Gpu;
import com.example.webclient.service.GpuService;
import com.example.webclient.view.GpuForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gpus")
@RequiredArgsConstructor
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    public String gpuList(Model model) {
        var gpus = gpuService.getAllGpus();
        model.addAttribute("gpus", gpus);
        return "gpu-list";
    }

    @GetMapping("/create")
    public String createGpuForm(Model model) {
        var gpus = gpuService.getAllGpus();
//        model.addAttribute("gpus", gpus);
        model.addAttribute("gpuForm", new GpuForm());
        return "gpu-form";
    }

    @GetMapping("/edit/{name}")
    public String editGpuForm(Model model, @PathVariable String name) {
//        var gpus = gpuService.getAllGpus();
        var gpu = gpuService.getGpu(name);
        model.addAttribute("gpuForm", createGpuFormFromGpu(gpu));
        return "gpu-form";
    }

    @PostMapping("/create")
    public String createGpu(@ModelAttribute("gpuForm") GpuForm gpuForm) {
        var gpu = createGpuFromGpuForm(gpuForm);
        gpuService.createGpu(gpu);
        return "redirect:/gpus";
    }

    @PutMapping("/update/{name}")
    public String updateGpu(@PathVariable String name, @ModelAttribute("gpuForm") GpuForm gpuForm) {
        var gpu = createGpuFromGpuForm(gpuForm);
        gpu.setName(name);
        gpuService.updateGpu(name, gpu);
        return "redirect:/gpus";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteGpu(@PathVariable String name) {
        gpuService.deleteGpu(name);
        return "redirect:/gpus";
    }

    @DeleteMapping("delete/all")
    public String deleteAllGpus() {
        gpuService.deleteAllGpus();
        return "redirect:/gpus";
    }

    private Gpu createGpuFromGpuForm(GpuForm gpuForm) {
        return new Gpu(
                gpuForm.getName(),
                gpuForm.getVideoRamCapacity()
        );
    }

    private GpuForm createGpuFormFromGpu(Gpu gpu) {
        return GpuForm.builder()
                .name(gpu.getName())
                .videoRamCapacity(gpu.getVideoRamCapacity())
                .build();
    }
}
