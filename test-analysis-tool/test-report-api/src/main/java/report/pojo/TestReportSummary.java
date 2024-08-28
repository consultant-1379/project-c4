package report.pojo;

import lombok.*;

@Generated
@NoArgsConstructor
@AllArgsConstructor
public class TestReportSummary {

    @Getter
    @Setter
    private String reportName;
    @Getter
    @Setter
    private int totalTests;
    @Getter
    @Setter
    private int totalPasses;
    @Getter
    @Setter
    private int totalFails;
    @Getter
    @Setter
    private int totalSkips;
    @Getter
    @Setter
    private Long lastTestTime = 0L;

    @Getter
    @Setter
    private HighestTestFail highestTestFail = new HighestTestFail();
}
