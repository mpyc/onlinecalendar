package pl.pycela.onlinecalendar.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckingDigit {
    final static Logger logger = LoggerFactory.getLogger(CheckingDigit.class);


    public static boolean CheckingIsDigit(String expiry_date) {
        try {
            int digit = Integer.valueOf(expiry_date);
            logger.info("Expiry date is digit");
            return true;
        } catch (NumberFormatException ex) {
            logger.error("Expiry date is not digit" + ex);
            return false;
        } catch (NullPointerException ex) {
            logger.error("Expiry date is null" + ex);
            return false;
        }
    }

}
