package contactList.app.action.user;

import contactList.app.model.User;
import contactList.app.service.user.UserService;

/**
 * Created by комп on 06.04.2017.
 */
public class UpdateUserAction extends UserAction {
    public UpdateUserAction(UserService userService, User user) {
        super(userService, user);
    }

    @Override
    public void execute() {
        userService.update(user);
    }
}
