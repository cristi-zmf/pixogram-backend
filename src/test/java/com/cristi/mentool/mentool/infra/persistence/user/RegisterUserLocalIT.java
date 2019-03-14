package com.cristi.mentool.mentool.infra.persistence.user;

import com.cristi.mentool.mentool.domain.user.RegisterUser;
import com.cristi.mentool.mentool.domain.user.User;
import com.cristi.mentool.mentool.exposition.user.UserRegistrationCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.cristi.mentool.mentool.infra.persistence.user.ValidUserGenerator.gigiUser;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterUserLocalIT {
    @Autowired private RegisterUser sut;
    @Autowired private UsersSdj usersSdj;
    private User gigiUser;
    private UserRegistrationCommand command;

    @Before
    public void setup() {
        gigiUser = gigiUser();
        command = new UserRegistrationCommand(gigiUser, "password");
        usersSdj.deleteAll();
    }


    @Test
    public void registerUser() {
        sut.registerUser(gigiUser);
        List<User> foundUsers = usersSdj.findAll();
        assertThat(foundUsers).hasSize(1);
        User actualUser = foundUsers.get(0);
        User expectedUser = new User(
            actualUser.getId(), actualUser.getFirstName(), actualUser.getLastName(), actualUser.getPhoneNumber()
        );
        checkThatSutLivedUpToTheExpectations(command, actualUser, expectedUser);
    }

    private void checkThatSutLivedUpToTheExpectations(UserRegistrationCommand command, User actualUser, User expectedUser) {
        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(expectedUser);
    }
}