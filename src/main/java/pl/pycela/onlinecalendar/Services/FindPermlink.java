package pl.pycela.onlinecalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pycela.onlinecalendar.Models.Permalink;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;

@Service
public class FindPermlink {

    private static PermalinkRepository permalinkRepository;

    @Autowired
    public FindPermlink(PermalinkRepository permalinkRepository) {
        FindPermlink.permalinkRepository = permalinkRepository;
    }

    public static Permalink findPermlinkByName(String perm) {
        return permalinkRepository.findByName(perm);
    }
}
