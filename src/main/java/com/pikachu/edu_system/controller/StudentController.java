package com.pikachu.edu_system.controller;

import com.pikachu.edu_system.model.Student;
import com.pikachu.edu_system.model.Teacher;
import com.pikachu.edu_system.repository.StudentRepository;
import com.pikachu.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "student-list";
    }


    @GetMapping("/add")
    public String addForm(Model model) {    //빈 객체를 하나 보내서 거기에 add
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("teachers", teachers);
        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/students";
    }
}
