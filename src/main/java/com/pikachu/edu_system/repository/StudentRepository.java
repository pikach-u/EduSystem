package com.pikachu.edu_system.repository;

import com.pikachu.edu_system.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> mapper = (resultSet, rowNum) ->Student.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .score(resultSet.getInt("score"))
            .teacherId(resultSet.getInt("teacher_id"))
            .teacherName(resultSet.getString("teacher_name"))
            .build();

    public int save(Student student){
        return jdbcTemplate.update(
                "INSERT INTO student (name, score, teacher_id) VALUES (?, ?, ?)",
                student.getName(), student.getScore(), student.getTeacherName()
        );
    }
}
