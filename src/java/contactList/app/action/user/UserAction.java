package contactList.app.action.user;

import contactList.app.action.Action;
import contactList.app.model.User;
import contactList.app.service.user.UserService;

/**
 * Created by комп on 06.04.2017.
 */
public abstract class UserAction implements Action {
    protected UserService userService;
    protected User user;

    public UserAction(UserService userService, User user){
        this.user = user;
        this.userService = userService;
    }
}
