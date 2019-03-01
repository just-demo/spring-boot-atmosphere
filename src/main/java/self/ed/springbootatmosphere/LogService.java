package self.ed.springbootatmosphere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singleton;

@Controller
public class LogService {
    private static final List<String> logs = new ArrayList<>(singleton("logs"));

    @GetMapping("/status")
    @ResponseBody
    public String status() {
        return String.join("<br/>", logs);
    }

    public static void log(String message) {
        System.out.println(message);
        logs.add(message);
    }
}
