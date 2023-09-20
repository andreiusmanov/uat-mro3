package uz.uat.mro.app.model.terms.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("countries")
public class Country {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String shortName;
    private String code3;
    private String numeric;
    private String name;

}
