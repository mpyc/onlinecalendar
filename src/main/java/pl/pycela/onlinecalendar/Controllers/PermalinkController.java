package pl.pycela.onlinecalendar.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;
import pl.pycela.onlinecalendar.Services.CheckingExpiredPermalinks;
import pl.pycela.onlinecalendar.Services.Encryption;

@Controller
public class PermalinkController {
    final static Logger logger = LoggerFactory.getLogger(PermalinkController.class);


    private PermalinkRepository permalinkRepository;
    private CheckingExpiredPermalinks checkingExpiredPermlinks;
    private Encryption encryption;

    @Autowired
    public PermalinkController(PermalinkRepository permalinkRepository, CheckingExpiredPermalinks checkingExpiredPermlinks, Encryption encryption) {
        this.permalinkRepository = permalinkRepository;
        this.checkingExpiredPermlinks = checkingExpiredPermlinks;
        this.encryption = encryption;
    }


    @RequestMapping(value = "/{permalink}")
    public String Calculator(@PathVariable("permalink") String permalink, Model model) {
        if ((permalinkRepository.findByName(permalink) != null) && !(checkingExpiredPermlinks.isPermalinkToDestroy(permalinkRepository.findByName(permalink)))) {
            model.addAttribute("password", permalinkRepository.findPasswordByName(permalink));
            model.addAttribute("permalink", permalink);
            return "calendar/calendar.html";
        } else {
            return "errors/404.html";
        }
    }

    @RequestMapping(value = "/{permalink}/check")
    @ResponseBody
    public boolean Test(@RequestParam("password") String password, @RequestParam("permalink") String permalink) {
        return encryption.checkPassword(password, permalinkRepository.findPasswordByName(permalink));
    }

}
