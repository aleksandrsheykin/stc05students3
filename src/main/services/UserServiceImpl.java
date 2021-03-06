package main.services;

import main.models.dao.UserDao;
import main.models.pojo.User;
import main.utils.Benchmark;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 20.04.2017.
 */
@Component
@Benchmark
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDAO;

    public UserDao getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);

        if (user != null && user.isIs_block()) {
            return null;
        }

        return user;
    }

}
