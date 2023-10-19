package uz.uat.mro.app.model.terms.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("uoms")

public class Uom {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String description;
    @Ref(lazy = true)
    private UomType type;
}
