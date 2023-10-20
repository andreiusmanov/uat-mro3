package uz.uat.mro.app.model.terms.common.edges;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.serde.jackson.To;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;

import lombok.Data;
import uz.uat.mro.app.model.terms.common.Employee;
import uz.uat.mro.app.model.terms.common.Person;

@Data
@Edge(collection = "link_persons_employees")
public class LinkPersonEmployee {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private final Person person;
    @To
    private final Employee employee;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final boolean status;
    
}
