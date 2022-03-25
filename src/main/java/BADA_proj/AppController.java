package BADA_proj;

import BADA_proj.adresy.Adres;
import BADA_proj.adresy.AdresyDAO;
import BADA_proj.bilety.Bilet;
import BADA_proj.bilety.BiletyDAO;
import BADA_proj.exceptions.PasswordMismatchException;
import BADA_proj.exceptions.PhoneNumberRegisteredException;
import BADA_proj.filharmonie.FilharmonieDAO;
import BADA_proj.klienci.KlienciDAO;
import BADA_proj.klienci.Klient;
import BADA_proj.rodzaje_wydarzenia.Rodzaj_Wydarzenia;
import BADA_proj.rodzaje_wydarzenia.Rodzaje_WydarzeniaDAO;
import BADA_proj.sale.Sala;
import BADA_proj.sale.SaleDAO;
import BADA_proj.wydarzenia.WydarzeniaDAO;
import BADA_proj.wydarzenia.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private WydarzeniaDAO wydarzeniaDAO;
    @Autowired
    private Rodzaje_WydarzeniaDAO rodzaje_wydarzeniaDAO;
    @Autowired
    private SaleDAO saleDAO;
    @Autowired
    private FilharmonieDAO filharmonieDAO; // Mamy tylko jedna filharmonie!
    @Autowired
    private KlienciDAO klienciDAO;
    @Autowired
    private AdresyDAO adresyDAO;
    @Autowired
    private BiletyDAO biletyDAO;


    @RequestMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        return "index";
    }

    @RequestMapping("/onas")
    public String viewONasPage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        return "login";
    }

    @RequestMapping("/logout")
    public String viewLogoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @RequestMapping("/register")
    public String viewRegistrationPage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/wydarzenia";
        }
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/user/wydarzenia"; //TODO: Do poprawki
        }

        model.addAttribute("klient", new Klient());
        model.addAttribute("adres", new Adres());
        model.addAttribute("haslo1", "");
        model.addAttribute("haslo2", "");
        return "register";
    }

    @PostMapping("/register")
    public String saveNewUser(@ModelAttribute("klient") Klient klient,
                              @ModelAttribute("adres") Adres adres,
                              @ModelAttribute("haslo1") String haslo1,
                              @ModelAttribute("haslo2") String haslo2) throws PasswordMismatchException, PhoneNumberRegisteredException {
        if (!haslo1.equals(haslo2)) {
            throw new PasswordMismatchException("Hasła nie są zgodne!");
        }
        Klient klientDB = null;
        try {
            klientDB = klienciDAO.findByPhoneNumber(klient.getNr_telefonu());
        } catch (EmptyResultDataAccessException e) {

        }
        if (klientDB != null) {
            throw new PhoneNumberRegisteredException("Istnieje już konto zarejestrowane za pomocą podanego numeru telefonu!");
        }
        klient.setHaslo(new BCryptPasswordEncoder().encode(haslo1));
        if (adres.getNr_lokalu().equals("")) {
            adres.setNr_lokalu(null);
        }
        int idAdres = 0;
        List<Adres> adresy = adresyDAO.list();
        for (Adres dbAdres : adresy) {
            if (adres.equals(dbAdres)) {
                idAdres = dbAdres.getId_adresu();
                break;
            }
        }
        if(idAdres==0){
            adresyDAO.save(adres);
            adresy = adresyDAO.list();
            for(Adres dbAdres : adresy){
                if(adres.equals(dbAdres)){
                    idAdres=dbAdres.getId_adresu();
                    break;
                }
            }
            klient.setId_adresu(idAdres);
            klienciDAO.save(klient);
        }else{
            klient.setId_adresu(idAdres);
            klienciDAO.save(klient);
        }
        return "redirect:/";
    }
    @RequestMapping("/wydarzenia")
    public String viewWydarzeniaPage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("listaWydarzen", wydarzeniaDAO.getCurrentWydarzenia());
        return "wydarzenia";
    }
    @RequestMapping("/admin/rodzaje")
    public String viewRodzajePage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("rodzajList", rodzaje_wydarzeniaDAO.list());
        return "admin/rodzaje/rodzaje";
    }
    @RequestMapping("/admin/usunRodzaj/{id}")
    public String deleteRodzaj(@PathVariable(name="id") int id){
        rodzaje_wydarzeniaDAO.delete(id);
        return "redirect:/admin/rodzaje";
    }
    @RequestMapping("/admin/nowyRodzaj")
    public String viewNowyRodzajPage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        Rodzaj_Wydarzenia rodzaj_wydarzenia = new Rodzaj_Wydarzenia();
        model.addAttribute("rodzaj", rodzaj_wydarzenia);
        return "admin/rodzaje/nowyRodzaj";
    }
    @PostMapping("admin/nowyRodzaj/save")
    public String saveNewRodzajPage(@ModelAttribute("rodzaj") Rodzaj_Wydarzenia rodzaj_wydarzenia){
        rodzaje_wydarzeniaDAO.save(rodzaj_wydarzenia);
        return "redirect:/admin/rodzaje";
    }
    @RequestMapping("/admin/edytujRodzaj/{id}")
    public String viewEdytujRodzajPage(@PathVariable(name="id") int id, Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("oldRodzaj", rodzaje_wydarzeniaDAO.get(id));
        Rodzaj_Wydarzenia newRodzaj = new Rodzaj_Wydarzenia();
        model.addAttribute("newRodzaj", newRodzaj);
        return "admin/rodzaje/edytujRodzaj";
    }
    @PostMapping("/admin/edytujRodzaj/{id}/update")
    public String updateRodzaj(@PathVariable(name = "id") int id,
            @ModelAttribute("newRodzaj") Rodzaj_Wydarzenia rodzaj_wydarzenia){
        rodzaj_wydarzenia.setId_rodzaju(id);
        rodzaje_wydarzeniaDAO.update(rodzaj_wydarzenia);
        return "redirect:/admin/rodzaje";
    }
    @RequestMapping("/admin/sale")
    public String viewSalePage(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        List<Sala> list = saleDAO.list();
        model.addAttribute("saleList", list);
        return "admin/sale/sale";
    }
    @RequestMapping("/admin/usunSala/{id}")
    public String deleteSala(@PathVariable(name="id") int id){
        saleDAO.delete(id);
        return "redirect:/admin/sale";
    }
    @RequestMapping("/admin/nowaSala")
    public String getNowaSala(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        Sala sala = new Sala();
        model.addAttribute("sala", sala);
        return "admin/sale/nowaSala";
    }
    @PostMapping("admin/nowaSala/save")
    public String saveNewSala(@ModelAttribute("sala") Sala sala){
        sala.setId_filharmonii(filharmonieDAO.list().get(0).getId_filharmonii()); // Druciarstwo alert
        saleDAO.save(sala);
        return "redirect:/admin/sale";
    }
    @RequestMapping("/admin/edytujSala/{id}")
    public String getEdytujSala(@PathVariable(name="id") int id, Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("oldSala", saleDAO.get(id));
        Sala newSala = new Sala();
        model.addAttribute("newSala", newSala);
        return "admin/sale/edytujSale";
    }
    @PostMapping("/admin/edytujSala/{id}/update")
    public String updateSala(@PathVariable(name = "id") int id,
                               @ModelAttribute("newSala") Sala sala){
        sala.setId_sali(id);
        sala.setId_filharmonii(filharmonieDAO.list().get(0).getId_filharmonii()); // Druciarstwo alert
        saleDAO.update(sala);
        return "redirect:/admin/sale";
    }

    @RequestMapping("/admin/wydarzenia")
    public String getWydarzeniaAdmin(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("listaWydarzen", wydarzeniaDAO.getCurrentWydarzenia());
        return "admin/wydarzenia/wydarzenia";
    }
    @RequestMapping("/admin/usunWydarzenie/{id}")
    public String deleteWydarzenie(@PathVariable(name="id") int id){
        wydarzeniaDAO.delete(id);
        return "redirect:/admin/wydarzenia";
    }
    @RequestMapping("/admin/noweWydarzenie")
    public String getNewWydarzenie(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        Wydarzenie wydarzenie = new Wydarzenie();
        model.addAttribute("wydarzenie", wydarzenie);
        model.addAttribute("sale", saleDAO.list());
        model.addAttribute("rodzaje", rodzaje_wydarzeniaDAO.list());
        return "admin/wydarzenia/noweWydarzenie";
    }
    @PostMapping("admin/noweWydarzenie/save")
    public String saveNewWydarzenie(@ModelAttribute("wydarzenie") Wydarzenie wydarzenie){
        wydarzenie.setId_filharmonii(filharmonieDAO.list().get(0).getId_filharmonii()); // Druciarstwo alert
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        wydarzenie.setCzas_rozpoczecia(LocalDateTime.parse(wydarzenie.getCzas_rozpoczecia_str(),formatter));
        wydarzeniaDAO.save(wydarzenie);
        return "redirect:/admin/wydarzenia";
    }
    @RequestMapping("/admin/edytujWydarzenie/{id}")
    public String getEdytujWydarzenie(@PathVariable(name="id") int id, Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("oldWydarzenie", wydarzeniaDAO.get(id));
        model.addAttribute("newWydarzenie", new Wydarzenie());
        model.addAttribute("sale", saleDAO.list());
        model.addAttribute("rodzaje", rodzaje_wydarzeniaDAO.list());
        return "admin/wydarzenia/edytujWydarzenie";
    }
    @PostMapping("/admin/edytujWydarzenie/{id}/update")
    public String updateWydarzenie(@PathVariable(name = "id") int id,
                            @ModelAttribute("newWydarzenie") Wydarzenie wydarzenie){
        wydarzenie.setId_wydarzenia(id);
        wydarzenie.setId_filharmonii(filharmonieDAO.list().get(0).getId_filharmonii()); // Druciarstwo alert
        if(wydarzenie.getCzas_rozpoczecia_str().equals("")){
            wydarzenie.setCzas_rozpoczecia(wydarzeniaDAO.get(id).getCzas_rozpoczecia());
        }else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            wydarzenie.setCzas_rozpoczecia(LocalDateTime.parse(wydarzenie.getCzas_rozpoczecia_str(),formatter));
        }
        wydarzeniaDAO.update(wydarzenie);
        return "redirect:/admin/wydarzenia";
    }
    @RequestMapping("/user/wydarzenia")
    public String getWydarzeniaUser(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("listaWydarzen", wydarzeniaDAO.getCurrentWydarzenia());
        return "user/wydarzeniaUser";
    }
    @RequestMapping("/user/rezerwuj/{id}")
    public String getRezerwuj(@PathVariable(name="id") int id, Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("wydarzenie", wydarzeniaDAO.get(id));
        Bilet bilet = new Bilet();
        model.addAttribute("bilet", bilet);
        return "user/nowaRezerwacja";
    }
    @PostMapping("/user/rezerwuj/{id}/save")
    public String makeRezerwacje(@PathVariable(name="id") int id,
                                 @ModelAttribute("bilet") Bilet bilet){
        bilet.setId_wydarzenia(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Klient klient = klienciDAO.findByPhoneNumber(username);
        bilet.setId_klienta(klient.getId_klienta());
        for(int i =0;i<bilet.getIlosc();i++){
            biletyDAO.save(bilet);
        }
        return "redirect:/user/rezerwacje";
    }
    @RequestMapping("/user/rezerwacje")
    public String getRezerwacje(Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Klient klient = klienciDAO.findByPhoneNumber(username);
        List<Bilet> bilety = biletyDAO.findByKlientId(klient.getId_klienta());
        List<Bilet> mappedBilety = new ArrayList<>();
        for(Bilet bilet : bilety){
            if(mappedBilety.contains(bilet)){
                int index = mappedBilety.indexOf(bilet);
                Bilet tempBilet = mappedBilety.get(index);
                tempBilet.setIlosc(tempBilet.getIlosc()+1);
            }else{

                bilet.setIlosc(bilet.getIlosc()+1);
                mappedBilety.add(bilet);
            }
        }
        model.addAttribute("bilety",  mappedBilety);
        return "user/rezerwacje";
    }
    @RequestMapping("/user/rezerwacje/{id}/delete")
    public String deleteRezerwacje(@PathVariable(name="id") int id){
        biletyDAO.delete(id);
        return "redirect:/user/rezerwacje";
    }
    @RequestMapping("/wydarzenia/{id}")
    public String showWydarzeniePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("filharmonia", filharmonieDAO.list().get(0));
        model.addAttribute("nextWydarzenie", wydarzeniaDAO.getNextEvent());
        model.addAttribute("wydarzenie", wydarzeniaDAO.get(id));
        return "wydarzenie";
    }
}
