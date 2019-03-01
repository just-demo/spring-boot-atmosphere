package self.ed.springbootatmosphere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LogService {
    private static final List<String> logs = new ArrayList<>();

    @GetMapping("/logs")
    @ResponseBody
    public String logs(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "clear") Optional<String> clear) throws IOException {
        String path = request.getRequestURI();
        if (clear.isPresent()) {
            logs.clear();
            response.sendRedirect(path);
            return "";
        }

        String refreshLink = "<a href=\"" + path + "\">Refresh</a>";
        String clearLink = "<a href=\"" + path + "?clear\">Clear</a>";
        return refreshLink + "&nbsp;" + clearLink + "<br/>" + String.join("<br/>", logs);
    }

    public static void log(String message) {
        System.out.println(message);
        if (logs.stream().mapToInt(String::length).sum() > 10000) {
            logs.clear();
        }
        logs.add(message);
    }
}
