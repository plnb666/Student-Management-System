package org.example.universitysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.universitysystem.model.Student;
import org.example.universitysystem.Service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        String validUsername = "LXM";
        String validPassword = "123456";

        if (validUsername.equals(username) && validPassword.equals(password)) {
            return "redirect:/students";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "index";
        }
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("studentCount", studentService.getStudentCount());
        return "students";
    }

    @GetMapping("/students/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/students/save")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable String id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit-student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/search")
    public String searchStudentsByName(@RequestParam("name") String name, Model model) {
        model.addAttribute("students", studentService.getStudentsByName(name));
        return "students";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}