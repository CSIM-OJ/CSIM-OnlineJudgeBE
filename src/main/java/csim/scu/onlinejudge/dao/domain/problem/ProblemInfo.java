package csim.scu.onlinejudge.dao.domain.problem;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface ProblemInfo {

    String getName();
    String getType();
    String getCategory();
    String[] getTag();
    double getRate();
    String getDescription();
    String getInputDesc();
    String getOutputDesc();
    List<TestCase> getTestCases();
    Date getDeadline();
    int getCorrectNum();
    int getIncorrectNum();
    double getCorrectRate();
    String getBestStudentAccount();
}
