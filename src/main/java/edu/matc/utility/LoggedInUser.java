package edu.matc.utility;

import edu.matc.entity.Users;
import edu.matc.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type Logged in user.
 */
public class LoggedInUser {
    private GenericDao userDao = new GenericDao(Users.class);

    /**
     * Gets logged in user.
     *
     * @param req the req
     * @return the logged in user
     */
    public Users getLoggedInUser (HttpServletRequest req) {
        Users loggedInUser = null;

        List<Users> usersResult = userDao.getByPropertyLike("username", req.getRemoteUser());
        if (usersResult.size() > 0) {
            loggedInUser = usersResult.get(0);
        }

        return loggedInUser;
    }
}
