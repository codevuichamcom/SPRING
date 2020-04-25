package com.hongquan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.Model.User;
import com.hongquan.dao.UserDao;

@Repository
@Transactional //sử dung Transaction cho toàn class(Nếu muốn cho 1 hàm thì đặt trên hàm đó)
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addUser(User user) {
		String sql = "INSERT INTO ban_hang.user (TEN_KH, SO_DT) VALUES (?,?)";
		jdbcTemplate.update(sql, user.getName(), user.getPhone());

	}

	public void updateUser(User user) {
		String sql = "UPDATE ban_hang.user SET TEN_KH = ?, SO_DT = ? WHERE (ID = ?)";
		jdbcTemplate.update(sql, user.getName(), user.getPhone(), user.getId());

	}

	public void deleteUser(int id) {
		String sql = "DELETE FROM ban_hang.user WHERE (ID = ?)";
		jdbcTemplate.update(sql, id);

	}

	public User getUserById(int id) {
		String sql = "SELECT * FROM ban_hang.user WHERE (ID = ?)";

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("TEN_KH"));
				user.setPhone(rs.getString("SO_DT"));
				return user;
			}

		});
	}

	public List<User> getAllUsers() {
		String sql = "SELECT * FROM ban_hang.user";
		
		return jdbcTemplate.query(sql, new Object[] {}, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("TEN_KH"));
				user.setPhone(rs.getString("SO_DT"));

				return user;
			}

		});
	}

}
