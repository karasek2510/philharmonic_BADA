package BADA_proj;

import BADA_proj.exceptions.PasswordMismatchException;
import BADA_proj.exceptions.PhoneNumberRegisteredException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLDataException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public String integrityConstraintViolationException(SQLIntegrityConstraintViolationException e, Model model) {
        System.out.println(e.getMessage());
        model.addAttribute("info", "Naruszono więzy spójności bazy danych!");
        return "/errors/error";
    }

    @ExceptionHandler(value = SQLDataException.class)
    public String exception(SQLDataException e, Model model) {
        e.printStackTrace();
        model.addAttribute("info", "Przekazane wartości są niepoprawne!");
        return "/errors/error";
    }

    @ExceptionHandler(value = PasswordMismatchException.class)
    public String passwordMismatchException(PasswordMismatchException e, Model model) {
        e.printStackTrace();
        model.addAttribute("info", "Hasła nie są zgodne!");
        return "/errors/error";
    }

    @ExceptionHandler(value = PhoneNumberRegisteredException.class)
    public String phoneNumberRegistered(PhoneNumberRegisteredException e, Model model) {
        e.getMessage();
        model.addAttribute("info", "Istnieje już konto zarejestrowane za pomocą podanego numeru telefonu!");
        return "/errors/error";
    }

}
