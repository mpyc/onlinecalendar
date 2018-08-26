package pl.pycela.onlinecalendar.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pycela.onlinecalendar.Models.Event;
import pl.pycela.onlinecalendar.Repositories.EventRepository;
import pl.pycela.onlinecalendar.Services.FindPermlink;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/calendar/api")
public class EventController {

    private EventRepository eventRepository;


    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }

    @GetMapping(value = "/{permlink}")
    public String getAll(@PathVariable String permlink) {
        String jsonMsg = null;
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
        try {
            jsonMsg = ow.writeValueAsString(eventRepository.findAllByPermlink(permlink));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/{permlink}/create", method = RequestMethod.POST)
    public void save(@PathVariable String permlink, @RequestParam String text, @RequestParam String start_date, @RequestParam String end_date, @RequestParam String id, @RequestParam String dhx_editor_status) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Event event = new Event(Long.valueOf(id), text, LocalDateTime.parse(start_date, df), LocalDateTime.parse(end_date, df), FindPermlink.findPermlinkByName(permlink));

        if (("inserted").equals(dhx_editor_status) || ("updated").equals(dhx_editor_status)) {
            eventRepository.save(event);
        } else if (dhx_editor_status.equals("deleted")) {
            eventRepository.delete(event);
        }
    }


}
