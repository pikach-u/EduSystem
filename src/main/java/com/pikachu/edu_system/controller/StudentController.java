package com.pikachu.edu_system.controller;

import com.pikachu.edu_system.repository.StudentRepository;
import com.pikachu.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
}
