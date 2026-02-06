package am.example.librarymanagementsystem.service.security;

import am.example.librarymanagementsystem.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class SpringUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public SpringUser(User user) {
        super(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

}
