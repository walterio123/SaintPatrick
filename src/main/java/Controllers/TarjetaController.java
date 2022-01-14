package Controllers;

import dto.TarjetaDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.TarjetaService;

import java.util.List;

@RestController
@RequestMapping("/tarjeta")
@AllArgsConstructor
public class TarjetaController {
    private final TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<TarjetaDTO> save(@RequestBody TarjetaDTO tarjetaDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaService.save(tarjetaDTO));
    }

    @GetMapping
    public ResponseEntity<List<TarjetaDTO>> findAll(){

        return ResponseEntity.ok().body(tarjetaService.findAll());
    }

    @GetMapping("/{tarjetaid}")
    public ResponseEntity<TarjetaDTO>findById(@RequestParam(value = "tarjetaid") String id) throws NotFoundException {
        return ResponseEntity.ok(tarjetaService.findById(id));
    }
}
