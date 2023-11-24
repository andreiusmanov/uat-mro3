package uz.uat.mro.app.model.terms.common;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("currencies")
public class Currency {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String numeric;
    private String name;
}
