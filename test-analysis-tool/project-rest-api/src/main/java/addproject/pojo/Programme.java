package addproject.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Programme")
public class Programme {

    @Getter
    @Setter
    @Id
    @Column(name = "programmeNumber")
    private String programmeNumber;

    @Getter
    @Setter
    @Column(name = "programmeName")
    private String programmeName;


    @Getter
    @Setter
    @Transient
    private List<Product> productList;
}
