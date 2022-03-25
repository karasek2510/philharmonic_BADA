package BADA_proj;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == 404) {
                model.addAttribute("info", "Powyższa strona nie istnieje");
            }
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("info", "Nie posiadasz odpowiednich uprawnień aby uzyskać dostęp do tej strony");
            } else {
                model.addAttribute("info", "Wystąpił błąd \n Przepraszamy za utrudnienia");
            }
        }
        return "errors/error";
    }
}
