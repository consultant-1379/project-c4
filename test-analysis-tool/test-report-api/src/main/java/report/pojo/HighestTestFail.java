package report.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class HighestTestFail {

    @Getter
    @Setter
    private String testName = "N\\A";

    @Getter
    @Setter
    private String nameOfClass = "N\\A";

    @Getter
    @Setter
    private String product = "N\\A";

    @Getter
    @Setter
    private int fails = 0;
}
