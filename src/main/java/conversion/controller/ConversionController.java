package conversion.controller;

import conversion.model.ConversionRepository;
import conversion.model.Conversion;
import conversion.service.ConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConversionController {

    private final ConversionRepository conversionRepository;
    private final ConService conService;


    @Autowired
    public ConversionController(ConversionRepository conversionRepository, ConService conService) {
        this.conversionRepository = conversionRepository;
        this.conService = conService;
    }

    @PostMapping("/conversion/")
    public ResponseEntity<?> conversionOp(Conversion conversion) {
        if(conversion.getOriginalAmountName().length() > 0 & conversion.getOriginalAmount().length() > 0 & conversion.getTargetAmountName().length() > 0){
            conService.conversionMethod(conversion);
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/conversion/out")
    public ResponseEntity<String> getTargetAm() {
        return conService.getTargetAmount();
    }


}
