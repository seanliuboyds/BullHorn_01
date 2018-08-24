package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    HornsRepository hornsRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listHorns(Model model) {
        model.addAttribute("horns", hornsRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String hornForm(Model model) {
        model.addAttribute("horn", new Horn());
        return "hornform";
    }

    @PostMapping("/loading")
    public String loadingForm(@Valid Horn horn, BindingResult result, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "hornform";
        }
        hornsRepository.save(horn);
        if (file.isEmpty()) {
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourceType", "auto"));
            horn.setImage(uploadResult.get("url").toString());
            hornsRepository.save(horn);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showHorn(@PathVariable("id") long id, Model model) {
        model.addAttribute("horn", hornsRepository.findById(id).get());
        return "show";
    }

    @GetMapping("/update/{id}")
    public String updateHorn(@PathVariable("id") long id, Model model) {
        model.addAttribute("horn", hornsRepository.findById(id).get());

        return "hornform";
    }

    @RequestMapping("/delete/{id}")
    public String delHorn(@PathVariable("id") long id) {
        hornsRepository.deleteById(id);
        return "redirect:/";
    }

    //    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }

    @RequestMapping("/register")
    public String register() {
        return "registration";
    }


}


