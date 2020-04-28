package zy.servic;

import zy.dao.UserDao;
import zy.dao.UserDaoImpl;
import zy.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    public UserServiceImpl() throws SQLException {
    }

    /**
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
