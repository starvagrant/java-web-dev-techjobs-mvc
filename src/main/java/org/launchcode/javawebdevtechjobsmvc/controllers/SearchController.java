package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    private static ArrayList<Job> searchResults = new ArrayList<>();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);

        return "search";
    }

    @GetMapping("results")
    public String displaySearchResults(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", searchResults);
        return "results";
    }

    @PostMapping("results")
    public String processSearchResults(Model model, @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs = new ArrayList<>();
        if (searchTerm.equalsIgnoreCase("all")){
            searchResults = JobData.findAll();
        } else if (searchType.equalsIgnoreCase("all")){
            searchResults = JobData.findByValue(searchTerm);
        } else {
            searchResults = JobData.findByColumnAndValue(searchType , searchTerm);
        }

        return "redirect:results";

    }
}
