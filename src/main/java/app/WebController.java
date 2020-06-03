package app;

import app.form.ContactForm;
import app.form.EmailForm;
import app.model.Contact;
import app.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController implements WebMvcConfigurer {

    @Autowired
    private ContactRepository repo;

    @Autowired
    private EmailRepository repoEmail;


    // Les routes
    @GetMapping("/")
    public String displayAllContact() {
        return "index";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id) {
        repo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable long id, Model model, EmailForm emailForm) {
        model.addAttribute("contact", repo.findById(id));
        return "user";
    }

    @GetMapping("/add")
    public String addContact(ContactForm contactForm) {
        return "userForm";
    }

    @PostMapping("/add")
    public String addContact(@Valid ContactForm contactForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "userForm";
        }

        Contact temp = new Contact(contactForm.getFirstName(), contactForm.getLastName(), contactForm.getPhone(), contactForm.getCivility(), contactForm.getLinkImage());
        repo.save(temp);
        //repoEmail.save(new Email("hugo@hugo.com", temp));

        return "redirect:/user/" + temp.getId();
    }

    @GetMapping("/results")
    public String showResult() {
        return "results";
    }

    // Les attributs
    @ModelAttribute("allContactList")
    public List<Contact> getAllContactList() {
        List<Contact> result = new ArrayList<Contact>();
        for (Contact c : repo.findAll()) {
            result.add(c);
        }
        return result;
    }


}