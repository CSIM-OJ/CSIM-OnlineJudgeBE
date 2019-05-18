package csim.scu.onlinejudge.common.message;

public enum ApiMessageCode {

    SUCCESS_STATUS("200", "請求成功"),
    // CommonApi
    LOGIN_ERROR("404", "登入失敗"),
    LOGOUT_ERROR("404", "登出失敗"),
    CHECK_LOGIN_ERROR("404", "檢查登入失敗"),
    CHANGE_PASSWORD_ERROR("404", "更改密碼失敗"),
    // StudentApi
    GET_COURSE_LIST_ERROR("404", "取得課程列表失敗"),
    GET_INFO_ERROR("404", "取得學生資訊失敗"),
    GET_HISTORY_SCORE_ERROR("404", "取得歷史成績失敗"),
    GET_STUDENT_PROBLEM_INFO_ERROR("404", "取得題目資訊失敗"),
    UPDATE_STUDENT_PROBLEM_RATE_ERROR("404", "更新題目的難易度失敗"),
    // TeacherApi
    CREATE_COURSE_ERROR("404", "創建課程失敗"),
    DELETE_COURSE_ERROR("404", "刪除課程失敗"),
    MAP_STUDENTLIST_COURSE_ERROR("404", "學生列表配對課程失敗"),
    DELETE_STUDENTLIST_COURSE_ERROR("404", "學生列表退出課程失敗"),
    MAP_ASSISTANTLIST_COURSE_ERROR("404", "助教列表配對課程失敗"),
    DELETE_ASSISTANTLIST_COURSE_ERROR("404", "助教列表退出課程失敗"),
    GET_COURSE_INFO_ERROR("404", "取得課程列表失敗"),
    GET_STUDENT_CLASS_LIST_ERROR("404", "取得學生班級列表失敗"),
    GET_ASSISTANT_LIST_ERROR("404", "取得助教名單失敗"),
    // ProblemApi
    GET_PROBLEM_INFO_ERROR("404", "取得題目資訊失敗"),
    GET_PROBLEMS_INFO_ERROR("404", "取得課程下的所有題目資訊失敗"),
    ADD_PROBLEM_ERROR("404", "新增題目失敗"),
    EDIT_PROBLEM_ERROR("404", "編輯題目失敗"),
    DELETE_PROBLEM_ERROR("404", "刪除題目失敗"),
    // JudgeApi
    JUDGE_CODE_ERROR("404", "批改代碼失敗"),
    GET_JUDGED_INFO_ERROR("404", "取得已批改資訊失敗"),
    CHECK_JUDGE_ERROR("404", "檢查此題是否被批改失敗"),
    JUDGE_COPY_ERROR("404", "批改抄襲失敗"),
    // CourseApi
    GET_COURSES_INFO_ERROR("404", "取得課程資訊失敗"),
    GET_STUDENT_DATA_ERROR("404", "取得課程的所有學生成績失敗"),
    // FeedbackApi
    ADD_FEEDBACK_ERROR("404", "新增回饋失敗"),
    GET_COURSE_FEEDBACK_ERROR("404", "取得課程下的所有回饋失敗"),
    // RankApi
    GET_CORRECT_RANK_ERROR("404", "取得正確解題學生排行失敗"),
    GET_BEST_CODE_RANK_ERROR("404", "取得最佳解答學生排行失敗"),
    // ProblemBankApi
    ADD_PROBLEMBANK_ERROR("404", "新增題目失敗"),
    DELETE_PROBLEMBANK_ERROR("404", "刪除題目失敗");
    
    private String code;
    private String desc;

    ApiMessageCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
