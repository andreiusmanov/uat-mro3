package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Uom;
import uz.uat.mro.app.model.terms.common.UomType;
import uz.uat.mro.app.model.terms.common.repositories.UomRepo;
import uz.uat.mro.app.model.terms.common.repositories.UomTypeRepo;

@Service
@AllArgsConstructor
public class UomService {
    private UomTypeRepo uomTypeRepo;
    private UomRepo uomRepo;

    public List<UomType> findAllUomTypes() {
        return StreamSupport.stream(uomTypeRepo.findAll().spliterator(), false).toList();
    }

    public List<Uom> findAllUoms() {
        return StreamSupport.stream(uomRepo.findAll().spliterator(), false).toList();
    }

    public Uom saveUom(Uom uom) {
        return uomRepo.save(uom);
    }

    public void deleteUom(Uom uom) {
        uomRepo.delete(uom);
    }

    public UomType saveUomType(UomType uomType) {
        return uomTypeRepo.save(uomType);
    }

    public void deleteUomType(UomType uomType) {
        uomTypeRepo.delete(uomType);
    }

}
