package easyqr.unir.easyqr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
