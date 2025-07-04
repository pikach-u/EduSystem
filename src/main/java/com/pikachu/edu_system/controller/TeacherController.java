package com.pikachu.edu_system.controller;

import com.pikachu.edu_system.model.Teacher;
import com.pikachu.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @GetMapping     //  /teachers
    public String list(){
        return "teacher-list";
    }

    @GetMapping("/add")
    public String addForm(){
        return "teacher-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher){
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }

}
