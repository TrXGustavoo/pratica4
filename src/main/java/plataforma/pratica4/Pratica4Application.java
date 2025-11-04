package plataforma.pratica4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Pratica4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pratica4Application.class, args);
	}
	
	@RequestMapping ("/")
	@ResponseBody
	String home()
	{
	    return "Hello World";
	}

}
