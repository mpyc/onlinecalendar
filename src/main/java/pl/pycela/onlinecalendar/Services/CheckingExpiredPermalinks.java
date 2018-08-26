package pl.pycela.onlinecalendar.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pycela.onlinecalendar.Models.Permalink;
import pl.pycela.onlinecalendar.Repositories.EventRepository;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;

import java.time.LocalDateTime;

@Service
public class CheckingExpiredPermalinks {
    final static Logger logger = LoggerFactory.getLogger(CheckingExpiredPermalinks.class);


    private final PermalinkRepository permalinkRepository;
    private final EventRepository eventRepository;

    @Autowired
    public CheckingExpiredPermalinks(PermalinkRepository permalinkRepository, EventRepository eventRepository) {
        this.permalinkRepository = permalinkRepository;
        this.eventRepository = eventRepository;
    }


    public boolean isPermalinkToDestroy(Permalink permalink) {
        if (isExpiredDate(permalink)) {
            eventRepository.deleteExpiredEvents(permalink.getId());
            permalinkRepository.deleteExpiredPermalink(permalink.getId());
            logger.info("Successful delete expired permlinks");
            return true;
        } else {
            logger.info("Permlink is not expired");
            return false;
        }
    }

    private boolean isExpiredDate(Permalink permalink) {
        return permalink.getExpiryDateTime().isBefore(LocalDateTime.now());
    }

}
