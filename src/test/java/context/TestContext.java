package context;

import lombok.Getter;
import lombok.Setter;
import model.User;


@Setter
@Getter
public class TestContext {
    private User currentUser;
}
