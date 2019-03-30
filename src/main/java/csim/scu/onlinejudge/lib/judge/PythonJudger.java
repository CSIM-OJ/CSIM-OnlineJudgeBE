package csim.scu.onlinejudge.lib.judge;

import csim.scu.onlinejudge.lib.model.JudgeData;
import csim.scu.onlinejudge.lib.model.JudgeReport;
import csim.scu.onlinejudge.lib.model.Language;
import csim.scu.onlinejudge.lib.model.PythonCommand;

public class PythonJudger extends Judger {

    public PythonJudger(JudgeData judgeData) {
        this.judgeData = judgeData;
        judgeData.setCommand(new PythonCommand());
    }

    @Override
    public JudgeReport performJudge() {
        executor = new Executor(judgeData, Language.PYTHON);
        return executor.execute();
    }
}
