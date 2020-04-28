package zy.dao;

import zy.domain.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
}
