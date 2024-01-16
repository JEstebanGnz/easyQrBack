package easyqr.unir.easyqr.Service;

import java.util.List;
import java.util.Optional;

import easyqr.unir.easyqr.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import easyqr.unir.easyqr.Entity.QrCode;
import easyqr.unir.easyqr.Repository.QrCodeRepository;

@Service
public class QrCodeService {

    @Autowired
    QrCodeRepository qrCodeRepository;
    public QrCode save(QrCode user) {
        return qrCodeRepository.save(user);
    }
    public List<QrCode> findAll() {
        return (List<QrCode>) qrCodeRepository.findAll();
    }
    public QrCode getQrCodeById(String id) {
        return qrCodeRepository.findById(id).get();
    }
    public Boolean deleteById(String id) {
        try {
            qrCodeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<QrCode> getQrCodesByDescriptionOrUrl(String searchKeyword) throws ResourceNotFoundException {

        var response = qrCodeRepository.findByDescriptionContainingIgnoreCaseOrUrlContainingIgnoreCase(searchKeyword, searchKeyword);

        if (response.isEmpty()) {
            throw new ResourceNotFoundException("There were no results for your query, try with other words");
        }

        return response;
    }

}