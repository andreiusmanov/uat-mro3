package uz.uat.mro.app.model.terms.organization;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;
import uz.uat.mro.app.model.terms.common.Country;

@Document(value="addresses")
@Data
public class Address {
    private Country country;
    private String index;
    private String street;
}
