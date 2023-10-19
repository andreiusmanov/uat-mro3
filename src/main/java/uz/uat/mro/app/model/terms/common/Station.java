package uz.uat.mro.app.model.terms.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("stations")
public class Station {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    @Ref(lazy = false)
    private Country country;
}
