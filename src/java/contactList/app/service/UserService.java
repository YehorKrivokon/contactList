package contactList.app.service;

import contactList.app.model.User;

/**
 * Created by комп on 05.04.2017.
 */
public interface UserService {
    void save(User user);
    void update (User user);
    User findByUsernameWithService(String username);
}
