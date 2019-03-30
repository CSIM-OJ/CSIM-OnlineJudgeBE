package csim.scu.onlinejudge.lib.model;

import java.text.DecimalFormat;
import java.util.List;

public class JudgeReport {

    private boolean isCompileSuccess;
    private String compileErrorMessage;
    private List<JudgeProblemResult> results;

    public boolean isCompileSuccess() {
        return isCompileSuccess;
    }

    public void setCompileSuccess(boolean compileSuccess) {
        isCompileSuccess = compileSuccess;
    }

    public List<JudgeProblemResult> getResults() {
        return results;
    }

    public void setResults(List<JudgeProblemResult> results) {
        this.results = results;
    }

    public String getCompileErrorMessage() {
        return compileErrorMessage;
    }

    public void setCompileErrorMessage(String compileErrorMessage) {
        this.compileErrorMessage = compileErrorMessage;
    }

    public double getAverageJudgeTimeForRoundOff2nd() {
        double averageJudgeTime = 0;
        for (JudgeProblemResult problemResult : getResults()) {
            averageJudgeTime += problemResult.getRunTime();
        }
        DecimalFormat format = new DecimalFormat("#.##");
        String result = format.format(averageJudgeTime / getResults().size());
        return Double.parseDouble(result);
    }

    public double getAverageScore() {
        double result = 0;
        for (JudgeProblemResult problemResult : getResults()) {
            result += problemResult.getGrade();
        }
        return result / getResults().size();
    }
}
