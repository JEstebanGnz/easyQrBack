package easyqr.unir.easyqr.Service;

import java.util.List;
import java.util.Optional;
import easyqr.unir.easyqr.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import easyqr.unir.easyqr.Entity.QrCode;
import easyqr.unir.easyqr.Repository.QrCodeRepository;

@Service
public class QrCodeService {
    QrCodeRepository qrCodeRepository;

    @Autowired
    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public QrCode save(QrCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public List<QrCode> findAll() throws ResourceNotFoundException {
        var qrCodes = (List<QrCode>) qrCodeRepository.findAll();
        if (qrCodes.isEmpty()) {
            throw new ResourceNotFoundException("No QR codes to show, try creating a new one");
        }
        return qrCodes;
    }

    public QrCode getQrCodeById(String id) throws ResourceNotFoundException {
        Optional<QrCode> qrCode = qrCodeRepository.findById(id);
        if (qrCode.isEmpty()) {
            throw new ResourceNotFoundException("There is no QR code with the provided ID");
        }
        return qrCode.get();
    }

    public void deleteById(String id) throws ResourceNotFoundException {
        //Check the QR code existence
        this.getQrCodeById(id);

        //If any exception is throw, the qrCode exists, so if safe to delete it.
        qrCodeRepository.deleteById(id);
    }

    public List<QrCode> getQrCodesByDescriptionOrUrl(String searchKeyword)
            throws ResourceNotFoundException {

        var response = qrCodeRepository.findByDescriptionContainingIgnoreCaseOrUrlContainingIgnoreCase(searchKeyword, searchKeyword);

        if (response.isEmpty()) {
            throw new ResourceNotFoundException("There were no results for your query, try with other words");
        }

        return response;
    }

}