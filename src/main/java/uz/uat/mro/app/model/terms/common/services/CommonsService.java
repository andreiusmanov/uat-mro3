package uz.uat.mro.app.model.terms.common.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.staff.repositories.EmployeesRepo;
import uz.uat.mro.app.model.terms.common.repositories.CountriesRepo;
import uz.uat.mro.app.model.terms.common.repositories.CurrencyRepo;
import uz.uat.mro.app.model.terms.common.repositories.MaintenanceRepo;
import uz.uat.mro.app.model.terms.common.repositories.StationsRepo;
import uz.uat.mro.app.model.terms.common.repositories.UomRepo;
import uz.uat.mro.app.model.terms.common.repositories.WorkdayRepo;

@Service
@AllArgsConstructor
public class CommonsService {
    private CountriesRepo countriesRepo;
    private CurrencyRepo currencyRepo;
    private EmployeesRepo employeesRepo;
    private MaintenanceRepo maintenanceRepo;
    private StationsRepo stationsRepo;
    private UomRepo uomRepo;
    private WorkdayRepo workdayRepo;

}
