package uz.uat.mro.app.model.terms.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document(collection = "uom_types", allowUserKeys = true)
public class UomType {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name; // russian name
    private String description;
}
