package com.ideas.saintpatricks.Controllers;

import com.ideas.saintpatricks.dto.ClienteDTO;
import com.ideas.saintpatricks.services.ClienteService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
@Tag(name = "Cliente", description = "Operations for Cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO>save(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDTO));
    }
    @GetMapping
    public ResponseEntity<List<ClienteDTO>>findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{clienteid}")
    public ResponseEntity<ClienteDTO>findById(@PathVariable (value = "clienteid") String id) throws NotFoundException {
        ClienteDTO dto= clienteService.findById(id);
        if(dto == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(clienteService.findById(id));

    }


}
