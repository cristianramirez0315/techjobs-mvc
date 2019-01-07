package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.util.StringUtils.capitalize;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")

    public String results(HttpServletRequest request, Model model) {
        model.addAttribute("columns", ListController.columnChoices);

        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");

        model.addAttribute("test", capitalize(searchType));

        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            int count = jobs.size();
            String count_jobs = String.valueOf(count);
            model.addAttribute("count", count_jobs + " Result(s)");
            model.addAttribute("jobs", jobs);
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            int count = jobs.size();
            String count_jobs = String.valueOf(count);
            model.addAttribute("count", count_jobs + " Result(s)");
            model.addAttribute("jobs", jobs);
        }
        return "search";
    }
}
