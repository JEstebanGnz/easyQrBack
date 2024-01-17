package easyqr.unir.easyqr.Controller;

import java.util.List;

import easyqr.unir.easyqr.Exceptions.InvalidParametersException;
import easyqr.unir.easyqr.Exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import easyqr.unir.easyqr.Entity.QrCode;
import easyqr.unir.easyqr.Service.QrCodeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/codes")
@CrossOrigin("*")
public class QrCodeController {
    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<List<QrCode>> getAllQrCodes() throws ResourceNotFoundException {
        List<QrCode> qrs = qrCodeService.findAll();
        return new ResponseEntity<>(qrs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<QrCode> getQrCodeById(@PathVariable String id) throws ResourceNotFoundException {
        QrCode qrCode = qrCodeService.getQrCodeById(id);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }

    @PostMapping
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<QrCode> createQrCode(@RequestBody QrCode qrCode) throws InvalidParametersException {
        QrCode createdQrCode = qrCodeService.save(qrCode);
        return new ResponseEntity<>(createdQrCode, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<QrCode> updateQrCode(@PathVariable String id, @RequestBody QrCode qrCode) throws InvalidParametersException, ResourceNotFoundException {
        qrCode.setId(id);
        QrCode updateQrCode = qrCodeService.update(qrCode);
        if (updateQrCode != null) {
            return new ResponseEntity<>(updateQrCode, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<Void> deleteQrCode(@PathVariable String id) throws ResourceNotFoundException {
        qrCodeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    @Parameter(in = ParameterIn.HEADER, name = "token", schema = @Schema(type = "string", defaultValue = "d8bf714a8a0821d1e4ca9ee4c514f271"))
    public ResponseEntity<List<QrCode>> searchQRCodesByParameter(@RequestParam("query") String query)
            throws ResourceNotFoundException {
        return new ResponseEntity<List<QrCode>>(qrCodeService.getQrCodesByDescriptionOrUrl(query), HttpStatus.OK);
    }


}
