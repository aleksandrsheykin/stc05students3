package main.services;

import main.models.pojo.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by admin on 20.04.2017.
 */

public interface UserService {

    User auth(String login, String password);
}
