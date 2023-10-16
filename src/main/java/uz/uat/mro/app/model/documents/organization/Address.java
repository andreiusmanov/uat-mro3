package uz.uat.mro.app.model.documents.organization;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.common.Country;

@Document("addresses")
@Data
public class Address {

    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private Country country;
    private String index;
    private String street;
}
