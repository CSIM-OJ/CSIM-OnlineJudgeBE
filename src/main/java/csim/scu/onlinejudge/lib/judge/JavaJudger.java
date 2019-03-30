package csim.scu.onlinejudge.lib.judge;

import csim.scu.onlinejudge.lib.model.JavaCommand;
import csim.scu.onlinejudge.lib.model.JudgeData;
import csim.scu.onlinejudge.lib.model.JudgeReport;
import csim.scu.onlinejudge.lib.model.Language;

public class JavaJudger extends Judger {

    public JavaJudger(JudgeData judgeData) {
        this.judgeData = judgeData;
        judgeData.setCommand(new JavaCommand());
    }

    @Override
    public JudgeReport performJudge() {
        executor = new Executor(judgeData, Language.JAVA);
        return executor.compileAndExecute();
    }
}
