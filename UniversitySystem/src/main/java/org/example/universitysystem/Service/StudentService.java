package org.example.universitysystem.Service;

import org.example.universitysystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 获取所有学生
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Student(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("major")
        ));
    }

    // 添加学生
    public void addStudent(Student student) {
        String sql = "INSERT INTO student (id, name, gender, address, phone, major) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getId() ,student.getName(), student.getGender(), student.getAddress(), student.getPhone(), student.getMajor());
    }

    // 根据 ID 获取学生
    public Student getStudentById(String id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Student(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("major")
        ), id);
    }

    // 更新学生信息
    public void updateStudent(String id, Student student) {
        String sql = "UPDATE student SET name = ?, gender = ?, address = ?, phone = ?, major = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAddress(), student.getPhone(), student.getMajor(), id);
    }

    // 删除学生
    public void deleteStudent(String id) {
        String sql = "DELETE FROM student WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 按名字查询学生
    public List<Student> getStudentsByName(String name) {
        String sql = "SELECT * FROM student WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, (rs, rowNum) -> new Student(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("major")
        ));
    }

    // 获取学生数量
    public int getStudentCount() {
        String sql = "SELECT COUNT(*) FROM student";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}