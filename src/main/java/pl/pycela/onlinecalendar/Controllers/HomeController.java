package pl.pycela.onlinecalendar.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pycela.onlinecalendar.Models.Permalink;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;
import pl.pycela.onlinecalendar.Services.CheckingDigit;
import pl.pycela.onlinecalendar.Services.Encryption;
import pl.pycela.onlinecalendar.Services.GeneretingPermlink;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class HomeController {
    final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final GeneretingPermlink generetingPermlink;
    private final PermalinkRepository permalinkRepository;
    private Encryption encryption;

    @Autowired
    public HomeController(GeneretingPermlink generetingPermlink, PermalinkRepository permalinkRepository, Encryption encryption) {
        this.encryption = encryption;
        this.generetingPermlink = generetingPermlink;
        this.permalinkRepository = permalinkRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home() {
        return "index/home.html";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String Generate(HttpServletRequest request, @RequestParam("expiry_date") String expiry_date, @RequestParam("password") String password) {
        String address = generetingPermlink.alphanumericString();
        if (CheckingDigit.CheckingIsDigit(expiry_date)) {
            permalinkRepository.save(new Permalink(address, LocalDateTime.now(), LocalDateTime.now().plusDays(Integer.valueOf(expiry_date))
                    , encryption.hashPassword(password)));
            return "redirect:" + "/" + address;
        } else {
            return "errors/404.html";
        }

    }
}
