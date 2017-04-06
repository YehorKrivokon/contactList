package contactList.app.action.user;

import contactList.app.model.User;
import contactList.app.service.user.UserService;

/**
 * Created by комп on 06.04.2017.
 */
public class CreateUserAction extends UserAction {
    public CreateUserAction(UserService userService, User user) {
        super(userService, user);
    }

    @Override
    public void execute() {
        userService.save(user);
    }
}
