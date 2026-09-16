package org.example.practicespring.controller;

import org.example.practicespring.entity.EmployeeEntity;
import org.example.practicespring.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // メインメニュー
    @GetMapping("/")
    public String menu() {
        return "index";
    }

    // 一覧
    @GetMapping("/employeelist")
    public String list(Model model) {
        List<EmployeeEntity> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    // 登録画面遷移
    @GetMapping("/new")
    public String form() {
        return "employee-form";
    }

    // 登録確認画面遷移
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute EmployeeEntity employee, Model model) {
        model.addAttribute("employee", employee);
        return "reg-confirm";
    }

    // 登録処理し、登録完了画面にリダイレクト（二重登録防止）
    @PostMapping("/register")
    public String reg(@ModelAttribute EmployeeEntity employee) {
        repository.save(employee);
        return "redirect:/reg-complete";
    }

    // 登録完了画面
    @GetMapping("/reg-complete")
    public String complete() {
        return "reg-complete";
    }

    // 検索画面表示
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String department,
                         Model model) {

        boolean searched = (name != null && !name.isEmpty()) || (department != null && !department.isEmpty());

        if (searched) {
            List<EmployeeEntity> employees = repository.findByNameContainingAndDepartmentContaining(name, department);

            model.addAttribute("employees", employees);
            model.addAttribute("searched", true);
        }
        model.addAttribute("name", name);
        model.addAttribute("department", department);

        return "employee-search";
    }
}