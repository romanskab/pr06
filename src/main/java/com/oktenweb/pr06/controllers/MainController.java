package com.oktenweb.pr06.controllers;

import com.oktenweb.pr06.dao.ApplicationDAO;
import com.oktenweb.pr06.dao.ClientDAO;
import com.oktenweb.pr06.entity.Application;
import com.oktenweb.pr06.entity.Client;
import com.oktenweb.pr06.entity.Course;
import com.oktenweb.pr06.utils.ClientValidate;
import com.oktenweb.pr06.utils.LocalDateCustomEditor;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MainController {

    private ClientDAO clientDAO;

    private ApplicationDAO applicationDAO;

    private LocalDateCustomEditor localDateCustomEditor;

    @InitBinder("application")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, localDateCustomEditor);
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("application", Application.builder().date(LocalDate.now()).title(Course.JAVA).build());
        model.addAttribute("courses", Course.values());
        return "home";
    }

    @PostMapping("/save")
    public String save(Client client, Application application){
        List<Client> clientList = clientDAO.findAll();

//        boolean anyMatchPhone = clientList.stream().anyMatch(client1 -> client1.getPhone().equals(client.getPhone()));
//        boolean anyMatchEmail = clientList.stream().anyMatch(client1 -> client1.getEmail().equals(client.getEmail()));
//        if (anyMatchPhone){
//            Client clientSave = clientList.stream().filter(client1 -> client1.getPhone().equals(client.getPhone())).findAny().get();
//            application.setClient(clientSave);
//            applicationDAO.save(application);
//        }else if (anyMatchEmail){
//            Client clientSave = clientList.stream().filter(client1 -> client1.getEmail().equals(client.getEmail())).findAny().get();
//            application.setClient(clientSave);
//            applicationDAO.save(application);
//        }else {
//            clientDAO.save(client);
//            application.setClient(client);
//            applicationDAO.save(application);
//        }

        if (ClientValidate.isPhoneInBase(client, clientList)){
            application.setClient(ClientValidate.findByPhone(client, clientList));
            applicationDAO.save(application);
        }else if (ClientValidate.isEmailInBase(client, clientList)){
            application.setClient(ClientValidate.findByEmail(client, clientList));
            applicationDAO.save(application);
        }else {
            clientDAO.save(client);
            application.setClient(client);
            applicationDAO.save(application);
        }

        return "redirect:/";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<Application> all = applicationDAO.findAll();
        model.addAttribute("all", all);
        return "findAll";
    }
}
