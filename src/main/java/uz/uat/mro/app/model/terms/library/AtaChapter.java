package uz.uat.mro.app.model.terms.library;


import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document(collection = "ata_chapters", allowUserKeys = true)

public class AtaChapter {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String general;
    private String name;

}
