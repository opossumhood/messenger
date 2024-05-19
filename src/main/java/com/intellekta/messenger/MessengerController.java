package com.intellekta.messenger;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessengerController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name="login", required = false, defaultValue="Max")
                                String login, Model model, HttpSession session) {
        session.setAttribute("user", login);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String post(@RequestParam String text, Model model, HttpSession session) {
        messageRepository.save(new Message(String.valueOf(session.getAttribute("user")), text));
        return "redirect:/main";
    }

    @GetMapping("/{user}")
    public String usersmessagesPage(@PathVariable(value = "user") String user, Model model) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> messages = new ArrayList<>();
        for (Message mes : allMessages) {
            if (mes.getUser().equals(user)) messages.add(mes);
        }
        model.addAttribute("messages", messages);
        return "usersmessages";
    }

}
