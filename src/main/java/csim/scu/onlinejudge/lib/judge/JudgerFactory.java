package csim.scu.onlinejudge.lib.judge;

import csim.scu.onlinejudge.lib.model.JudgeData;
import csim.scu.onlinejudge.lib.model.Language;

public class JudgerFactory {

    public static Judger createJudger(Language language, JudgeData judgeData) {
        switch (language) {
            case JAVA:
                return new JavaJudger(judgeData);
            case PYTHON:
                return new PythonJudger(judgeData);
            default:
                return null;
        }
    }
}
