package Controllers;

import dto.TransDTO;
import entities.Trans;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import mappers.TransMappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.TransServices;

import java.util.List;

@RestController
@RequestMapping("/trans")
@AllArgsConstructor
public class TransController {

    private final TransServices transServices;

    @PostMapping
    public ResponseEntity<TransDTO> createTrans(@RequestBody TransDTO transDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body((transServices.save(transDTO)));
    }
    @GetMapping
    public ResponseEntity<List<TransDTO>> listOfTrans(){

        List<TransDTO> listTransDTO=(transServices.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(listTransDTO);
    }

    @GetMapping("/{transid}")
    public ResponseEntity<TransDTO> findById(@PathVariable(value = "transid")String id)throws NotFoundException {

        return ResponseEntity.ok(transServices.findById(id));
    }

}
