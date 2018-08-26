package pl.pycela.onlinecalendar.Services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class Encryption {

    private int workload = 12;


    public String hashPassword(String password_plaintext) {
        if (password_plaintext == null || password_plaintext.equals("")) {
            return "";
        } else {
            String salt = BCrypt.gensalt(workload);
            String hashed_password = BCrypt.hashpw(password_plaintext, salt);
            return (hashed_password);
        }
    }


    public boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;
        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return (password_verified);
    }

}
