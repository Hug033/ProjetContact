package app;

import app.form.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
public class WebController implements WebMvcConfigurer {


    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @GetMapping("/add")
    public String showForm(ContactForm contactForm) {
        return "form";
    }

    @PostMapping("/add")
    public String checkPersonInfo(@Valid ContactForm contactForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }


        return "redirect:/results";
    }


}