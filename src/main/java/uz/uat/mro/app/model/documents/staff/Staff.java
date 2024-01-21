package uz.uat.mro.app.model.documents.staff;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document(collection = "staff")
public class Staff {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
}