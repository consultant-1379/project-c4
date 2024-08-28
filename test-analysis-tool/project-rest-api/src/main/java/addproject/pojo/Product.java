package addproject.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Getter
    @Setter
    @Id
    @Column(name = "productNumber")
    private String productNumber;

    @Getter
    @Setter
    @Column(name = "productName")
    private String productName;

    @Getter
    @Setter
    @Column(name = "programmeNumber")
    private String programmeNumber;

    @Getter
    @Setter
    @Transient
    private List<CNA> cnaList;

}
