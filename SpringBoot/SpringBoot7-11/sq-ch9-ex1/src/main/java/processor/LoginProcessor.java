package processor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope
@Getter
@Setter
public class LoginProcessor {

    private String username;
    private String password;

    public boolean login(String username, String password) {

        if("natalie".equals(username) && "password".equals(password))
        {
            return true;
        } else {
            return false;
        }


    }

}
