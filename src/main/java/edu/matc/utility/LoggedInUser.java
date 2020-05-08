package edu.matc.utility;

import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoggedInUser {
    private GenericDao userDao = new GenericDao(Users.class);

    public Users getLoggedInUser (HttpServletRequest req) {
        Users loggedInUser = null;

        List<Users> usersResult = userDao.getByPropertyLike("username", req.getRemoteUser());
        if (usersResult.size() > 0) {
            loggedInUser = usersResult.get(0);
        }

        return loggedInUser;
    }
}
