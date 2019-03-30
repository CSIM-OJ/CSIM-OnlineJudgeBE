package csim.scu.onlinejudge.lib.judge;

import csim.scu.onlinejudge.lib.model.JudgeData;
import csim.scu.onlinejudge.lib.model.JudgeReport;

public abstract class Judger {

    Executor executor;
    JudgeData judgeData;

    public abstract JudgeReport performJudge();
}
