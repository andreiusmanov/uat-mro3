package uz.uat.mro.app.model.terms.organization;

import lombok.Data;
import uz.uat.mro.app.model.terms.common.Country;

@Data
public class Address {
    private Country country;
    private String index;
    private String street;
}
