package com.ideas.saintpatricks.Controllers;

import com.ideas.saintpatricks.dto.TransDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ideas.saintpatricks.services.TransServices;

import java.util.List;

@RestController
@RequestMapping(path = "/trans")
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

    @GetMapping(path = "/{transid}")
    public ResponseEntity<TransDTO> findById(@PathVariable(value = "transid")String id) throws NotFoundException {
        TransDTO dto=transServices.findById(id);
        if(dto == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok(transServices.findById(id));
    }

}
