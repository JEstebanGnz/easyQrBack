package easyqr.unir.easyqr.Controller;

import java.util.List;

import easyqr.unir.easyqr.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import easyqr.unir.easyqr.Entity.QrCode;
import easyqr.unir.easyqr.Service.QrCodeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class QrCodeController {
     @Autowired
    private QrCodeService qrCodeService;
    @GetMapping
    public ResponseEntity<List<QrCode>> getAllUsers() {
        List<QrCode> qrs = qrCodeService.findAll();
        return new ResponseEntity<>(qrs, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getQrCodeById(@PathVariable String id) throws ResourceNotFoundException {
        if (id == null || id.isEmpty()) {
            return new ResponseEntity<>("El campo id es requerido", HttpStatus.BAD_REQUEST);
        }
        QrCode qrCode = qrCodeService.getQrCodeById(id);
        if (qrCode != null) {
            return new ResponseEntity<>(qrCode, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createQrCode(@Valid @RequestBody QrCode qrCode) {

        if (qrCode.getDescription() == null || qrCode.getDescription().isEmpty()) {
            throw  new
        }
        QrCode createdQrCode = qrCodeService.save(qrCode);
        return new ResponseEntity<>(createdQrCode, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QrCode> updateQrCode(@PathVariable String id, @RequestBody QrCode qrCode) {
        qrCode.setId(id);
        QrCode updateQrCode = qrCodeService.save(qrCode);
        if (updateQrCode != null) {
            return new ResponseEntity<>(updateQrCode, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQrCode(@PathVariable String id) {
        boolean deleted = qrCodeService.deleteById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<QrCode>> searchQRCodesByParameter(@RequestParam("query") String query) throws ResourceNotFoundException {
        return new ResponseEntity<List<QrCode>>(qrCodeService.getQrCodesByDescriptionOrUrl(query), HttpStatus.OK);
    }


    
}
