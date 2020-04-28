package zy.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import zy.domain.User;
import zy.utils.JdbcUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JdbcUtils.getDataSource());

    public UserDaoImpl() throws SQLException {
    }

    @Override
    public List<User> findAll() {
        jdbcTemplate.execute("set global time_zone ='+8:00'");
        List<User> users = jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
