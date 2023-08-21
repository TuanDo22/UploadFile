package com.example.javaspringfram;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("SessionDemo")
public class SessionDemo {
    @GetMapping("/likesession")
    public String setSesson(HttpSession httpSession){
        User user = new User();
        user.setName("tuan");
        user.setAge(19);
        user.setAddress("hanoi");
        httpSession.setAttribute("user",user);
        return "setsession";
    }
    /*@GetMapping("/getsession")
    public String getSession(HttpSession httpSession, Model model){
        User user = (User) htpSession.getAttribute("user");
        model.addAttribute("user",user);
        return "getsession";
    }*/
}
