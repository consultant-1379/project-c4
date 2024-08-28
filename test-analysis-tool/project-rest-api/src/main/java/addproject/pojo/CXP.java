package addproject.pojo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CXP")
public class CXP {

    @Getter
    @Setter
    @Id
    @Column(name = "cxpNumber")
    private String cxpNumber;

    @Getter
    @Setter
    @Column(name = "cxpName")
    private String cxpName;

    @Getter
    @Setter
    @Column(name = "cxpRepo")
    private String cxpRepo;

    @Getter
    @Setter
    @Column(name = "jenkinsJobURL")
    private String jenkinsJobURL;

    @Getter
    @Setter
    @Column(name = "cnaNumber")
    private String cnaNumber;

}
