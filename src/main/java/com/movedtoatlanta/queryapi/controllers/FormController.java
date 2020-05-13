package com.movedtoatlanta.queryapi.controllers;

import com.movedtoatlanta.network.models.Event;
import com.movedtoatlanta.queryapi.controllers.exceptions.NoRecordsFoundException;
import com.movedtoatlanta.queryapi.models.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * This class will manage the UI
 */
@Controller
public class FormController {

    private final QueryController queryController;

    @Autowired
    public FormController(QueryController queryController) {
        this.queryController = queryController;
    }

    /**
     * Method to render the form with the objects needed for POST
     *
     * @param model {@link org.springframework.ui.Model}
     * @return String
     */
    @GetMapping("query")
    public String queryForm(Model model) {
        model.addAttribute("query", new Query());
        return "queryForm";
    }

    /**
     * Method to render the results of the POST
     *
     * @param query {@link com.movedtoatlanta.queryapi.models.Query}
     * @param model {@link org.springframework.ui.Model}
     * @return String
     */
    @PostMapping("query")
    public String getResults(@ModelAttribute Query query, Model model) {
        try {
            List<Event> events = queryController.getEventDetails(query.getOwner(), query.getRepo(), query.getType());
            model.addAttribute("events", events);
            return "queryResults";
        } catch (NoRecordsFoundException nre) {
            return "noResults";
        }
    }
}
