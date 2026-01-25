package com.pgh.todoapp.controller;

import com.pgh.todoapp.entity.TodoEntity;
import com.pgh.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping({"","/"})
    public String showHomePage(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title) {
        TodoEntity newTodo = TodoEntity.builder().title(title).completed(false).build();
        todoRepository.save(newTodo);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id) {
        TodoEntity existingTodo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("Todo not found: "+id));
        existingTodo.setCompleted(true);
        todoRepository.save(existingTodo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        TodoEntity existingTodo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("Todo not found: "+id));
        todoRepository.delete(existingTodo);
        return "redirect:/";
    }
}
