package app;

import app.form.AdressForm;
import app.form.AssignForm;
import app.form.ContactForm;
import app.form.EmailForm;
import app.model.Adress;
import app.model.Contact;
import app.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController implements WebMvcConfigurer {

    // Les répertoires avec les données

    @Autowired
    private ContactRepository repo; // Contact

    @Autowired
    private EmailRepository repoEmail; // Email

    @Autowired
    private AdressRepository repoAdress; // Adresse


    // Traitement des requêtes GET

    @GetMapping("/")
    public String displayAllContact() {
        return "index";
    }

    // Gestion de l'API
    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody ContactList apiXML(@RequestParam String action, @RequestParam(required = false) String id) {

        if(action.equals("listContacts")) {
            ContactList list = new ContactList();
            for(Contact c : repo.findAll()) {
                list.getContacts().add(c);
            }
            return list;
        } else if (action.equals("getContact") && id != null) {
            ContactList list = new ContactList();
            list.getContacts().add(repo.findById(Long.parseLong(id)));
            return list;
        } else if(action.equals("delContact") && id != null) {
            Contact temp = repo.findById(Long.parseLong(id));
            if(temp != null)
            {
                for(Email e: temp.getEmails()) {
                    repoEmail.deleteAll();
                }
                temp.setEmails(null);
                repo.save(temp);
                repo.deleteById(Long.parseLong(id));
            }
        }
        return null;
    }

    @GetMapping("/email/delete/{id}")
    public String deleteEmail(@PathVariable long id) {
        Email temp = repoEmail.findById(id);
        repoEmail.deleteById(id);
        return "redirect:/contact/detail/" + temp.getContact().getId();
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        Contact temp = repo.findById(id);
        for(Email e: temp.getEmails()) {
            repoEmail.deleteAll();
        }
        temp.setEmails(null);
        repo.save(temp);
        repo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String editUser(@PathVariable long id, Model model, ContactForm contactForm) {
        Contact temp = repo.findById(id);
        contactForm.setFirstName(temp.getFirstName());
        contactForm.setLastName(temp.getLastName());
        contactForm.setCivility(temp.getCivility());
        contactForm.setLinkImage(temp.getLinkImage());
        contactForm.setPhone(temp.getPhone());
        contactForm.setId(temp.getId());
        return "contactForm";
    }

    @GetMapping("/contact/detail/{id}")
    public String getUserDetail(@PathVariable long id, Model model, EmailForm emailForm, AssignForm assignForm) {

        Contact temp = repo.findById(id);
        List<Adress> adresses = new ArrayList<Adress>();
        for(Adress adress : repoAdress.findAll())
        {
            if(!temp.getAdresses().contains(adress))
            {
                adresses.add(adress);
            }
        }
        model.addAttribute("contact", temp);
        model.addAttribute("adresses", adresses);

        return "contact";
    }

    @GetMapping("/add")
    public String addContact(ContactForm contactForm) {
        return "contactForm";
    }

    @GetMapping("/add/adress")
    public String addAdress(AdressForm adressForm) {
        return "adressForm";
    }


    // Traitement des requêtes POST

    // Permet d'ajouter un contact
    @PostMapping("/add")
    public String addUser(@Valid ContactForm contactForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "contactForm";
        }

        Contact temp = new Contact(contactForm.getFirstName(), contactForm.getLastName(), contactForm.getPhone(), contactForm.getCivility(), contactForm.getLinkImage());
        repo.save(temp);
        //repoEmail.save(new Email("hugo@hugo.com", temp));

        return "redirect:/contact/detail/" + temp.getId();
    }

    // Permet d'éditer un contact
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable long id, @Valid ContactForm contactForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contactForm";
        }

        Contact contactToEdit = repo.findById(contactForm.getId());
        contactToEdit.setFirstName(contactForm.getFirstName());
        contactToEdit.setLastName(contactForm.getLastName());
        contactToEdit.setCivility(contactForm.getCivility());
        contactToEdit.setLinkImage(contactForm.getLinkImage());
        contactToEdit.setPhone(contactForm.getPhone());
        repo.save(contactToEdit);

        return "redirect:/contact/detail/" + id;
    }

    // Permet d'ajouter une adresse
    @PostMapping("/add/adress")
    public String addAdress(@Valid AdressForm adressForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "adressForm";
        }

        repoAdress.save(new Adress(adressForm.getLine_1(), adressForm.getLine_2(), adressForm.getPostalCode(), adressForm.getCity()));

        return "redirect:/add/adress";
    }

    // Permet d'ajouter une adresse mail à un contact
    @PostMapping("/add/email/{id}")
    public String addEmail(@PathVariable long id, @Valid EmailForm emailForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contact", repo.findById(id));
            return "contact";
        }

        Contact temp = repo.findById(id);
        if (repoEmail.findByEmail(emailForm.getEmail()) == null) {
            repoEmail.save(new Email(emailForm.getEmail(), temp));
        }

        return "redirect:/contact/detail/" + temp.getId();
    }

    // Permet d'assigner une adresse à un contact
    @PostMapping("/assign/adress/{id}")
    public String assignAdress(@PathVariable long id, @Valid AssignForm assignForm, BindingResult bindingResult, Model model) {

        System.out.println(assignForm.getAdressId());

        if (bindingResult.hasErrors()) {
            model.addAttribute("contact", repo.findById(id));
            model.addAttribute("emailForm", new EmailForm());
            return "contact";
        }

        Contact temp = repo.findById(id);
        Long test = assignForm.getAdressIdLong();
        temp.getAdresses().add(repoAdress.findById(assignForm.getAdressIdLong()));
        repo.save(temp);

        model.addAttribute("emailForm", new EmailForm());
        return "redirect:/contact/detail/" + temp.getId();
    }


    // Gestion des attributs

    // Permet de récupérer tous les contacts
    @ModelAttribute("allContactList")
    public List<Contact> getAllContactList() {
        List<Contact> result = new ArrayList<Contact>();
        for (Contact c : repo.findAll()) {
            result.add(c);
        }
        return result;
    }

    // Permet de récupérer toutes les adresses
    @ModelAttribute("allAdressList")
    public List<Adress> getAllAdressList() {
        List<Adress> result = new ArrayList<Adress>();
        for (Adress adress : repoAdress.findAll()) {
            result.add(adress);
        }
        return result;
    }

    // Permet de savoir si 'lutilisateur est connecté
    @ModelAttribute("isConnected")
    public boolean isConnected(Principal prc)
    {
        return prc != null;
    }

}