package contactList.app.service.user;

import contactList.app.model.Contact;
import contactList.app.model.User;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
public interface UserService {
    void save(User user);
    void update (User user);
    User findByUsernameWithService(String username);

}
