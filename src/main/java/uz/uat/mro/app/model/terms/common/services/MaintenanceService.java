package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Maintenance;
import uz.uat.mro.app.model.terms.common.repositories.MaintenanceRepo;

@Service
@AllArgsConstructor
public class MaintenanceService {
    private MaintenanceRepo maintenanceRepo;

    public List<Maintenance> findAll() {
        return StreamSupport.stream(maintenanceRepo.findAll().spliterator(), false).toList();
    }

    public Maintenance save(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    public void delete(Maintenance maintenance) {
        maintenanceRepo.delete(maintenance);
    }

}
