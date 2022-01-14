package services;

import dto.TransDTO;
import entities.Trans;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;


import mappers.TransMappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TransRepository;

import java.util.List;



@Service
@AllArgsConstructor
public class TransServices {

    private final TransRepository transRepository;
    private final TransMappers transMappers;

    @Transactional
    public TransDTO save(TransDTO transDTO) {
        Trans trans=transMappers.toTrans(transDTO);

        return transMappers.toTransDto(transRepository.save(trans));
    }

    @Transactional(readOnly = false)
    public List<TransDTO> findAll() {
        return transMappers.toListTransDTO(transRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TransDTO findById(String transId) throws NotFoundException {
        var respOptional = transRepository.findById(transId);

        if (respOptional.isPresent()) {
            return transMappers.toTransDto(respOptional.get());
        } else {
            throw new NotFoundException("Trans Not Found with: "+transId+" id.");
        }

    }
}