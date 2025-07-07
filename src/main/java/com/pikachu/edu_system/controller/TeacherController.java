package com.pikachu.edu_system.controller;

import com.pikachu.edu_system.model.Teacher;
import com.pikachu.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model) {   // /teachers로 접속하면 DB에서 모든 교사 목록을 가져옴
        model.addAttribute("teachers", teacherRepository.findAll());

        return "teacher-list";  // teacher-list.html에 teachers라는 이름으로 전달
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("teacher", new Teacher());

        return "teacher-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);

        return "redirect:/teachers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            int affected = teacherRepository.deleteById(id);

            if (affected == 0) {
                System.out.println("해당 교사를 찾을 수 없습니다.");
            }
        } catch (Exception e) { //외래키로 참조중인 Teacher를 삭제할때
//          model.addAttribute("error", "에러 발생:" + e.getMessage());
            System.out.println(e.getMessage()); //참조중인 키는 삭제되지 않고 콘솔에 에러메세지 출력
        }

        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherRepository.findById(id));

        return "teacher-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Teacher teacher) {
        teacherRepository.update(teacher);

        return "redirect:/teachers";
    }
}
