package uz.uat.mro.app.model.terms.common;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.Organization;

@Data
@Document("departments")
public class Department {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String shortName;
    @Ref
    private Organization firm;
    //@Relations(edges = ForeignKey.class, lazy = false)
    private Collection<Sector> sectors;


}
