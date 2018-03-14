package lt.devopspro.controller;


import lt.devopspro.model.LoginForm;
import lt.devopspro.model.SignUpResponse;
import lt.devopspro.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppRestController {

    public static List<User> usersList = new ArrayList<>();
    public static int ids = 0;

    @PostConstruct
    private void init()
    {
        User user1 = new User(ids++, "Tom1", "test123", "Tom1", "Don1", "tom@1don.com");
        User user2 = new User(ids++, "Tom2", "test123", "Tom2", "Don2", "tom@2don.com");
        User user3 = new User(ids++, "Tom3", "test123", "Tom3", "Don3", "tom@3don.com");
        User user4 = new User(ids++, "Tom4", "test123", "Tom4", "Don4", "tom@4don.com");

        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
    }



    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public SignUpResponse signup(@RequestBody User user) {

        if (user.getUserName().isEmpty()) {
            return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Fill username");
        }

        if (user.getPassword().isEmpty()) {
            return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Fill password");
        }

        if (user.getFirstName().isEmpty()) {
            return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Fill Firsat Name");
        }

        if (user.getLastName().isEmpty()) {
            return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Fill Last name");
        }

        if (user.getEmail().isEmpty()) {
            return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Fill email");
        }

        for (User u: usersList) {
            if (u.getUserName().equals(user.getUserName())) {
                return new SignUpResponse(SignUpResponse.ResponseStatus.ERROR, "Username allready in use");
            }
        }

        usersList.add(new User(ids++, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail()));

        return new SignUpResponse(SignUpResponse.ResponseStatus.OK, String.valueOf(ids));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody LoginForm loginForm) {
        User usr = null;
        for (User u: usersList) {
            if (u.getUserName().equals(loginForm.getUserName())) {
                if (u.getPassword().equals(loginForm.getPassword())) {
                    usr = new User(u.getId(), u.getUserName(), null, u.getFirstName(), u.getLastName(), u.getEmail());
                    break;
                }
            }
        }

        return usr;
    }

}
