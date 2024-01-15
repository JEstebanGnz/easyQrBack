package easyqr.unir.easyqr.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import easyqr.unir.easyqr.Entity.QrCode;


public interface QrCodeRepository  extends CrudRepository<QrCode,String>{

    public Optional<QrCode> findById(String id);


    public List<QrCode> findByDescriptionContainingIgnoreCaseOrUrlContainingIgnoreCase(String description, String url);
}
