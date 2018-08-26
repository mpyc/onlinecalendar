package pl.pycela.onlinecalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;

import java.util.Random;

@Service
public class GeneretingPermlink {

    @Autowired
    private PermalinkRepository permalinkRepository;


    public String alphanumericString() {
        final int lenghtOfAlfanumericString = 24;
        final Random random = new Random();
        final String alphanumeric = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder sb = null;
        do {
            sb = new StringBuilder();
            for (int i = 0; i < lenghtOfAlfanumericString; i++) {
                int randomInt = random.nextInt(alphanumeric.length());
                sb.append(alphanumeric.charAt(randomInt));
            }
        } while (isPermalinkExists(sb.toString()));
        return sb.toString();
    }

    private boolean isPermalinkExists(String sb) {
        return permalinkRepository.findByName(sb) != null;
    }
}
