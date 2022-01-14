package Controllers;

import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Tag(name = "Cliente", description = "Operations for Cliente")

public class ClienteController {


}
