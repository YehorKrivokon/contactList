package contactList.app.service.security;

/**
 * Created by комп on 05.04.2017.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
