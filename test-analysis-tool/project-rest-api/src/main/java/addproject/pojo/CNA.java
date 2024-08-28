package addproject.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CNA")
public class CNA {

    @Getter
    @Setter
    @Id
    @Column(name = "cnaNumber")
    private String cnaNumber;

    @Getter
    @Setter
    @Column(name = "cnaName")
    private String cnaName;

    @Getter
    @Setter
    @Column(name = "productNumber")
    private String productNumber;

    @Getter
    @Setter
    @Transient
    private List<CXP> cxpList;

}
