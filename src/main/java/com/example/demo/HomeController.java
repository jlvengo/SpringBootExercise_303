package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDo(Model model){
        model.addAttribute("ToDo", toDoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("toDo", new TODO());
        return "toDoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid TODO toDo,
                              BindingResult result){
        if (result.hasErrors()){
            return "toDoform";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("toDo", toDoRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") long id,
                               Model model){
        model.addAttribute("toDo", toDoRepository.findById(id).get());
        return "toDoform";
    }

    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable ("id") long id){
        toDoRepository.deleteById(id);
        return "redirect:/";
    }
}
