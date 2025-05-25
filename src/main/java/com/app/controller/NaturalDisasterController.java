package com.app.controller;

import com.app.domain.ISingleMeasureRequest;
import com.app.service.NaturalDisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disasters")
public class NaturalDisasterController {

    @Autowired
    private NaturalDisasterService service;

    @GetMapping
    public String showForm() {
        return "disasterForm"; 
    }

    @PostMapping("/search")
    public String searchSingleMeasure(
            @RequestParam String country,
            @RequestParam String disasterType,
            Model model) {

        ISingleMeasureRequest result = service.getSingleMeasure("SearchRequest", country, disasterType);

        if (result == null) {
            model.addAttribute("errorMessage", "No data found for " + country + " and " + disasterType);
            return "disasterForm";
        }

        model.addAttribute("result", result);
        model.addAttribute("descriptiveStats", result.getDescriptiveStatsString());
        model.addAttribute("regression", result.getRegressionResultString());

        return "disasterResult"; 
    }

    @PostMapping("/searchRange")
    public String searchSingleMeasureRange(
            @RequestParam String country,
            @RequestParam String disasterType,
            @RequestParam int fromYear,
            @RequestParam int toYear,
            Model model) {

        if (toYear < fromYear) {
            model.addAttribute("errorMessage", "Invalid year range");
            return "disasterForm";
        }

        ISingleMeasureRequest result = service.getSingleMeasureRange("SearchRequestRange", country, disasterType, fromYear, toYear);

        if (result == null) {
            model.addAttribute("errorMessage", "No data found for given parameters");
            return "disasterForm";
        }

        model.addAttribute("result", result);
        model.addAttribute("descriptiveStats", result.getDescriptiveStatsString());
        model.addAttribute("regression", result.getRegressionResultString());

        return "disasterResult";
    }
}
