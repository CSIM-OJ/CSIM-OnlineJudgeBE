package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.common.exception.*;
import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;
import csim.scu.onlinejudge.dao.domain.copy.Copy;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.judge.HistoryCode;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.problem.TestCase;
import csim.scu.onlinejudge.dao.domain.student.*;
import csim.scu.onlinejudge.dao.domain.student.CorrectRank;
import csim.scu.onlinejudge.dao.domain.student.CorrectRank;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.lib.judge.Judger;
import csim.scu.onlinejudge.lib.judge.JudgerFactory;
import csim.scu.onlinejudge.lib.model.*;
import csim.scu.onlinejudge.service.*;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class CommonServiceImpl implements CommonService {

    private StudentService studentService;
    private TeacherService teacherService;
    private AssistantService assistantService;
    private AdminService adminService;
    private CourseService courseService;
    private ProblemService problemService;
    private JudgeService judgeService;
    private CopyService copyService;
    private FeedbackService feedbackService;

    @Autowired
    public CommonServiceImpl(StudentService studentService,
                             TeacherService teacherService,
                             AssistantService assistantService,
                             AdminService adminService,
                             CourseService courseService,
                             ProblemService problemService,
                             JudgeService judgeService,
                             CopyService copyService,
                             FeedbackService feedbackService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.assistantService = assistantService;
        this.adminService = adminService;
        this.courseService = courseService;
        this.problemService = problemService;
        this.judgeService = judgeService;
        this.copyService = copyService;
        this.feedbackService = feedbackService;
    }

    @Override
    public String findUserAuthority(String account, String password) throws StudentNotFoundException, TeacherNotFoundException, AssistantNotFoundException, AdminNotFoundException {
        boolean existStudent = studentService.existByAccount(account);
        boolean existTeacher = teacherService.existByAccount(account);
        boolean existAssistant = assistantService.existByAccount(account);
        boolean existAdmin = adminService.existByAccount(account);

        String authority = "";
        if (existStudent) {
            Student student = studentService.findByAccount(account);
            if (comparePassword(password, student.getPassword())) {
                authority = "student";
            }
        }
        else if (existTeacher) {
            Teacher teacher = teacherService.findByAccount(account);
            if (comparePassword(password, teacher.getPassword())) {
                authority = "teacher";
            }
        }
        else if (existAssistant) {
            Assistant assistant = assistantService.findByAccount(account);
            if (comparePassword(password, assistant.getPassword())) {
                authority = "assistant";
            }
        }
        else if (existAdmin) {
            Admin admin = adminService.findByAccount(account);
            if (comparePassword(password, admin.getPassword())) {
                authority = "admin";
            }
        }
        return authority;
    }

    @Override
    public int updateUserPassword(String account, String oriPassword,
                                   String newPassword, String userType) {
        switch (userType) {
            case "student":
                return studentService.updatePasswordByAccount(account,
                        oriPassword, newPassword);
            case "teacher":
                return teacherService.updatePasswordByAccount(account, oriPassword, newPassword);
            case "assistant":
                return assistantService.updatePasswordByAccount(account, oriPassword, newPassword);
            case "admin":
                return adminService.updatePasswordByAccount(account, oriPassword, newPassword);
            default:
                return -1;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Course createCourse(String account, String courseName, String semester) throws TeacherNotFoundException {
        Teacher teacher = teacherService.findByAccount(account);
        Course course = new Course(teacher, courseName, semester, null, null, null, null, null);
        return courseService.save(course);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void mapStudentListToCourse(Long courseId, List<String> accounts) throws StudentNotFoundException, CourseNotFoundException {
        Course course = courseService.findById(courseId);
        for (int i = 0; i < accounts.size(); i++) {
            String account = accounts.get(i);
            Student student = studentService.findByAccount(account);
            List<Course> courses = student.getCourses();
            courses.add(course);
            student.setCourses(courses);
            studentService.save(student);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStudentListFromCourse(Long courseId, List<String> accounts) throws StudentNotFoundException, CourseNotFoundException, JudgeNotFoundException {
        Course course = courseService.findById(courseId);
        List<Problem> problems = problemService.findByCourse(course);
            for (int i = 0; i < accounts.size(); i++) {
                String account = accounts.get(i);
                Student student = studentService.findByAccount(account);
                List<Course> courses = student.getCourses();
                courses.remove(course);
                student.setCourses(courses);
                for (Problem problem : problems) {
                    if (problem.getBestStudentAccount().equals(account)) {
                        List<Judge> judges = judgeService.findByProblem(problem);
                        double bestRunTime = Double.MAX_VALUE;
                        String bestAccount = "";
                        for (Judge judge : judges) {
                            // 找出下一位最佳代碼持有者
                            if (!judge.getStudent().getAccount().equals(account)
                                    && judge.getHistoryCodes().get(judge.getHistoryCodes().size() - 1).getScore() == 100) {
                                double tempRunTime = judge.getHistoryCodes().get(judge.getHistoryCodes().size() - 1).getRunTime();
                                if (tempRunTime < bestRunTime) {
                                    bestRunTime = tempRunTime;
                                    bestAccount = judge.getStudent().getAccount();
                                }
                            }
                        }
                        problem.setBestStudentAccount(bestAccount);
                        problemService.save(problem);
                    }
                }
                studentService.save(student);
            }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void mapAssistantListToCourse(Long courseId, List<String> accounts) throws AssistantNotFoundException, CourseNotFoundException {
        Course course = courseService.findById(courseId);
        for (int i = 0; i < accounts.size(); i++) {
            String account = accounts.get(i);
            Assistant assistant = assistantService.findByAccount(account);
            List<Course> courses = assistant.getCourses();
            courses.add(course);
            assistant.setCourses(courses);
            assistantService.save(assistant);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAssistantListFromCourse(Long courseId, List<String> accounts) throws AssistantNotFoundException, CourseNotFoundException {
        Course course = courseService.findById(courseId);
        for (int i = 0; i < accounts.size(); i++) {
            String account = accounts.get(i);
            Assistant assistant = assistantService.findByAccount(account);
            List<Course> courses = assistant.getCourses();
            courses.remove(course);
            assistant.setCourses(courses);
            assistantService.save(assistant);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createProblem(Long courseId, String name, String type,
                                 String category, String[] tag,
                                 String description, String inputDesc,
                                 String outputDesc, List<TestCase> testCases,
                                 Date deadline) throws CourseNotFoundException {
        Course course = courseService.findById(courseId);
        Problem problem = new Problem(course, name, type, category, tag, 0,  description, inputDesc, outputDesc, testCases, deadline, 0, 0, 0, "", null, null);
        problemService.save(problem);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Problem> findByCourseId(Long courseId) throws CourseNotFoundException {
        Course course = courseService.findById(courseId);
        List<Problem> problems = problemService.findByCourse(course);
        return problems;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, String> findStudentCourseInfo(Long courseId, String account) throws StudentNotFoundException, JudgeNotFoundException, CourseNotFoundException {
        Student student = studentService.findByAccount(account);
        String name = student.getName();
        String studentClass = student.getStudentClass();
        List<Problem> problems = findByCourseId(courseId);
        String undoNum = "";
        String doneNum = "";
        String bestCodeNum = "";
        String correctNum = "";
        String incorrectNum = "";
        int judgeCount = 0;
        int bestCount = 0;
        int correctCount = 0;
        int incorrectCount = 0;
        for (Problem problem : problems) {
            judgeCount += judgeService.countByProblemAndStudent(problem, student);
            if (problem.getBestStudentAccount().equals(account)) {
                bestCount++;
            }
            Judge judge = judgeService.findByProblemAndStudent(problem, student);
            if (judge != null) {
                List<HistoryCode> historyCodes = judge.getHistoryCodes();
                if (historyCodes.get(historyCodes.size() - 1).getScore() == 100) {
                    correctCount ++;
                }
                else {
                    incorrectCount++;
                }
            }
        }
        doneNum = String.valueOf(judgeCount);
        undoNum = String.valueOf(problems.size() - judgeCount);
        bestCodeNum = String.valueOf(bestCount);
        correctNum = String.valueOf(correctCount);
        incorrectNum = String.valueOf(incorrectCount);
        Map<String, String> map = new HashMap<>();
        map.put("account", account);
        map.put("name", name);
        map.put("studentClass", studentClass);
        map.put("undoNum", undoNum);
        map.put("doneNum", doneNum);
        map.put("bestCodeNum", bestCodeNum);
        map.put("correctNum", correctNum);
        map.put("incorrectNum", incorrectNum);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void judgeCode(Long problemId, String code, String language, String account) throws ProblemNotFoundException, StudentNotFoundException, JudgeNotFoundException {
        Problem problem = problemService.findById(problemId);
        Student student = studentService.findByAccount(account);
        // 取得輸入輸出範本
        List<TestCase> testCases = problem.getTestCases();
        List<ProblemCase> problemCases = new ArrayList<>();
        for (TestCase testCase : testCases) {
            String inputSample = testCase.getInputSample();
            String outputSample = testCase.getOutputSample();
            ProblemCase problemCase = new ProblemCase(inputSample, outputSample);
            problemCases.add(problemCase);
        }
        // 選擇程式語言
        Language programLanguage = chooseLanguage(language);
        // 選擇題目的批改方式
        String category = problem.getCategory();
        JudgeBehavior judgeBehavior = chooseBehavior(category);

        // 建立批改所需的data、judger進行批改，並取得report
        JudgeData judgeData = new JudgeData(account, code, problemCases, judgeBehavior);
        Judger judger = JudgerFactory.createJudger(programLanguage, judgeData);
        assert judger != null;
        JudgeReport report = judger.performJudge();

        double avgRunTime = report.getAverageJudgeTimeForRoundOff2nd();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String handDate = df.format(new Date());
        double avgScore = report.getAverageScore();
        double correctRate = 0;
        int correctNum = problem.getCorrectNum();
        int incorrectNum = problem.getIncorrectNum();

        // 確認是否是第一次judge，如果是，則新增一筆Judge to db，否則拿取舊的judge
        boolean isNotFirstJudge = judgeService.existByProblemAndStudent(problem, student);
        List<HistoryCode> historyCodes;
        Judge judge;
        double oriScore = 0;
        if (!isNotFirstJudge) {
            historyCodes = new ArrayList<>();
            judge = new Judge(problem, student, 0, historyCodes);
        }
        else {
            judge = judgeService.findByProblemAndStudent(problem, student);
            historyCodes = judge.getHistoryCodes();
            oriScore = judge.getHistoryCodes().get(judge.getHistoryCodes().size() - 1).getScore();
        }

        // 取得批改後的相關資訊，並加入到歷史代碼中
        List<String> output = new ArrayList<>();
        List<String> symbol = new ArrayList<>();
        List<String> errorMessage = new ArrayList<>();
        for (JudgeProblemResult problemResult : report.getResults()) {
            output.add(problemResult.getOutput());
            symbol.add(problemResult.getSymbol().getDescription());
            errorMessage.add(problemResult.getMessage());
        }
        HistoryCode historyCode = new HistoryCode(handDate, code, avgRunTime,
                output, symbol, errorMessage, avgScore);
        historyCodes.add(historyCode);
        judge.setHistoryCodes(historyCodes);

        // 更改題目的答對答錯率
        if (avgScore == 100) {
            if (!isNotFirstJudge) {
                correctNum++;
            }
            else {
                if (oriScore == 0) {
                    correctNum++;
                    incorrectNum--;
                }
            }
            // 比對最佳代碼是否需要更換，兩種情況:還沒有最佳代碼、已經有最佳代碼
            if (!problem.getBestStudentAccount().equals("")) {
                Student bestStudent = studentService.findByAccount(problem.getBestStudentAccount());
                Judge bestJudge = judgeService.findByProblemAndStudent(problem, bestStudent);
                double bestAvgRunTime = bestJudge.getHistoryCodes().get(bestJudge.getHistoryCodes().size() - 1).getRunTime();
                if (bestAvgRunTime > avgRunTime) {
                    problem.setBestStudentAccount(account);
                }
            }
            else {
                problem.setBestStudentAccount(account);
            }
        }
        else {
            if (!isNotFirstJudge) {
                incorrectNum++;
            }
            else {
                if (oriScore == 100) {
                    correctNum--;
                    incorrectNum++;
                }
            }
            // 比對最佳代碼是否需要更換，自己是最佳代碼的持有者的情況
            if (!problem.getBestStudentAccount().equals("")) {
                if (problem.getBestStudentAccount().equals(account)) {
                    List<Judge> judges = judgeService.findByProblem(problem);
                    double bestRunTime = Double.MAX_VALUE;
                    String anotherBestAccount = "";
                    for (Judge tempJudge : judges) {
                        // 找出下一位最佳代碼持有者
                        if (!tempJudge.getStudent().getAccount().equals(problem.getBestStudentAccount())
                                && tempJudge.getHistoryCodes().get(tempJudge.getHistoryCodes().size() - 1).getScore() == 100) {
                            double tempRunTime = tempJudge.getHistoryCodes().get(tempJudge.getHistoryCodes().size() - 1).getRunTime();
                            if (tempRunTime < bestRunTime) {
                                bestRunTime = tempRunTime;
                                anotherBestAccount = tempJudge.getStudent().getAccount();
                            }
                        }
                    }
                    problem.setBestStudentAccount(anotherBestAccount);
                }
            }
        }
        correctRate = (double) correctNum / (correctNum + incorrectNum);
        // 設定答對數、答錯數、答對率
        problem.setCorrectNum(correctNum);
        problem.setIncorrectNum(incorrectNum);
        problem.setCorrectRate(correctRate);
        // 儲存Problem、Judge，更新後的資訊
        problemService.save(problem);
        judgeService.save(judge);
    }

    @Override
    public Judge findByProblemIdAndStudentAccount(Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException, JudgeNotFoundException {
        Problem problem = problemService.findById(problemId);
        Student student = studentService.findByAccount(account);
        return judgeService.findByProblemAndStudent(problem, student);
    }

    @Override
    public boolean existByProblemIdAndStudentAccount(Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException {
        Problem problem = problemService.findById(problemId);
        Student student = studentService.findByAccount(account);
        return judgeService.existByProblemAndStudent(problem, student);
    }

    @Transactional
    @Override
    public void judgeCopy(Long problemId) throws ProblemNotFoundException {
        Problem problem = problemService.findById(problemId);
        Language language = chooseLanguage(problem.getTag()[0]);
        List<Judge> judges = judgeService.findByProblem(problem);
        int number = judges.size();
        List<Copy> copies = new ArrayList<>();
        // 每一位學生的代碼與其他所有學生進行比對，相似度>0.9的話，則視為抄襲
        for (int i = 0; i < number; i++) {
            Judge sourceJudge = judges.get(i);
            String sourceAccount = sourceJudge.getStudent().getAccount();
            String sourceCode = sourceJudge.getHistoryCodes().get(sourceJudge.getHistoryCodes().size() - 1).getCode();

            for (Judge destJudge : judges) {
                if (!sourceJudge.getStudent().getAccount().equals(destJudge.getStudent().getAccount())) {
                    String destAccount = destJudge.getStudent().getAccount();
                    String destCode = destJudge.getHistoryCodes().get(destJudge.getHistoryCodes().size() - 1).getCode();

                    CodeSimilarity codeSimilarity = new CodeSimilarity(language, new LCSAlgorithm());
                    double similarity = codeSimilarity.get(sourceCode, destCode);
                    if (similarity >= 0.9) {
                        Copy copy = new Copy(problem, sourceAccount, destAccount, similarity);
                        copies.add(copy);
                    }
                }
            }
        }
        copyService.saveAll(copies);
    }

    @Override
    public Feedback addFeedback(Long courseId, String account, String content) throws ParseException, CourseNotFoundException, StudentNotFoundException {
        Course course = courseService.findById(courseId);
        Student student = studentService.findByAccount(account);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(new Date());
        Date date = df.parse(str);
        Feedback feedback = new Feedback(course, student, date, content);
        feedbackService.save(feedback);
        return feedback;
    }

    @Override
    public List<Feedback> findFeedbacksByCourseId(Long courseId) throws CourseNotFoundException {
        Course course = courseService.findById(courseId);
        return feedbackService.findByCourse(course);
    }

    @Override
    public List<Map<String, String>> getStudentProblemInfo(Long courseId, String type, boolean isJudge, String account) throws CourseNotFoundException, StudentNotFoundException {
        Course course = courseService.findById(courseId);
        Student student = studentService.findByAccount(account);
        List<Problem> problems;
        List<Problem> realProblems = new ArrayList<>();
        if (type.equals("全部")) {
            problems = problemService.findByCourse(course);
        }
        else {
            problems = problemService.findByCourseAndType(course, type);
        }
        if (isJudge) {
            for (Problem problem : problems) {
                boolean isExist = judgeService.existByProblemAndStudent(problem, student);
                if (isExist) {
                    realProblems.add(problem);
                }
            }
        }
        else {
            for (Problem problem : problems) {
                boolean isExist = judgeService.existByProblemAndStudent(problem, student);
                if (!isExist) {
                    realProblems.add(problem);
                }
            }
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (Problem problem : realProblems) {
            Map<String, String> map = new HashMap<>();
            map.put("problemId", String.valueOf(problem.getProblemId()));
            map.put("name", problem.getName());
            map.put("type", problem.getType());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            map.put("deadline", df.format(problem.getDeadline()));
            map.put("rate", String.valueOf(problem.getRate()));
            results.add(map);
        }
        return results;
    }

    @Transactional
    @Override
    public int updateJudgeRateByProblemIdAndAccount(double rate, Long problemId, String account) throws ProblemNotFoundException, StudentNotFoundException {
        Problem problem = problemService.findById(problemId);
        Student student = studentService.findByAccount(account);
        int code = judgeService.updateRateByProblemAndStudent(rate, problem, student);
        if (code == 1) {
            double avgRate = judgeService.getAvgRateByProblem(problem);
            problemService.updateRateByProblemId(problemId, avgRate);
            return code;
        }
        else {
            return -1;
        }
    }

    @Override
    public List<Map<String, String>> getStudentCoursesInfo(String account) throws StudentNotFoundException {
        List<Course> courses = studentService.findCoursesByAccount(account);
        List<Map<String, String>> results = new ArrayList<>();
        for (Course course : courses) {
            Map<String, String> map = new HashMap<>();
            Teacher teacher = course.getTeacher();
            map.put("courseId", String.valueOf(course.getCourseId()));
            map.put("courseName", course.getCourseName());
            map.put("teacherName", teacher.getName());
            map.put("semester", course.getSemester());
            results.add(map);
        }
        return results;
    }

    @Override
    public List<Map<String, String>> getCoursesInfo() {
        List<Course> courses = courseService.findAll();
        List<Map<String, String>> results = new ArrayList<>();

        for (Course course : courses) {
            Map<String, String> map = new HashMap<>();
            Teacher teacher = course.getTeacher();
            map.put("courseId", String.valueOf(course.getCourseId()));
            map.put("courseName", course.getCourseName());
            map.put("teacherName", teacher.getName());
            map.put("semester", course.getSemester());
            results.add(map);
        }
        return results;
    }

    @Override
    public List<Map<String, Object>> getStudentHistoryScoreInfo(Long courseId, String account) throws CourseNotFoundException, JudgeNotFoundException, StudentNotFoundException {
        List<Problem> problems = findByCourseId(courseId);
        Student student = studentService.findByAccount(account);
        for (Problem problem : problems) {
            Map<String, Object> map = new HashMap<>();
            boolean isJudge = judgeService.existByProblemAndStudent(problem, student);
            if (isJudge) {
                Judge judge = judgeService.findByProblemAndStudent(problem, student);
                String problemId = String.valueOf(problem.getProblemId());
                String problemName = problem.getName();
                String type = problem.getType();
                List<HistoryCode> historyCodes = judge.getHistoryCodes();
                int lastIndex = historyCodes.size() - 1;
                String code = historyCodes.get(lastIndex).getCode();
                String score = String.valueOf(historyCodes.get(lastIndex).getScore());
                String handDate = historyCodes.get(lastIndex).getHandDate();
                String runTime = String.valueOf(historyCodes.get(lastIndex).getRunTime());
                String rate = String.valueOf(judge.getRate());
                String correctRate = String.valueOf(problem.getCorrectRate());
                boolean isBestCode = false;
                if (problem.getBestStudentAccount().equals(account)) {
                    isBestCode = true;
                }
            }
        }
        return null;
    }

    @Override
    public List<CorrectRank> getCorrectRank(Long courseId) throws CourseNotFoundException {
        Course course = courseService.findById(courseId);
        List<Student> students = course.getStudents();

        Map<Student, Integer> original = new HashMap<>();
        for (Student student : students) {
            List<Judge> judges = student.getJudges();
            int count = 0;
            for (Judge judge : judges) {
                int lastIndex = judge.getHistoryCodes().size() - 1;
                if (judge.getHistoryCodes().get(lastIndex).getScore() == 100) {
                    count++;
                }
            }
            original.put(student, count);
        }
        List<CorrectRank> sorted = original.entrySet().stream()
                .sorted(Map.Entry.<Student, Integer>comparingByValue().reversed())
                .filter(e -> e.getValue() != 0)
                .limit(5)
                .map(e -> new CorrectRank(e.getKey().getAccount(), e.getKey().getName(), String.valueOf(e.getValue()), "0"))
                .collect(toList());

        for (int i = 0; i < sorted.size(); i++) {
            if (i == 0) {
                CorrectRank CorrectRank = sorted.get(i);
                CorrectRank.setRank("1");
            }
            else {
                CorrectRank CorrectRank = sorted.get(i);
                if (CorrectRank.getCorrectNum().equals(sorted.get(i - 1).getCorrectNum())) {
                    CorrectRank.setRank(sorted.get(i - 1).getRank());
                }
                else {
                    CorrectRank.setRank(String.valueOf(i + 1));
                }
            }
        }
        return sorted;
    }

    @Override
    public List<BestCodeRank> getBestCodeRank(Long courseId) throws CourseNotFoundException {
        Course course = courseService.findById(courseId);
        List<Student> students = course.getStudents();

        Map<Student, Integer> original = new HashMap<>();
        for (Student student : students) {
            int count = problemService.countByBestStudentAccountAndCourse(student.getAccount(), course);
            original.put(student, count);
        }

        List<BestCodeRank> sorted = original.entrySet().stream()
                .sorted(Map.Entry.<Student, Integer>comparingByValue().reversed())
                .filter(e -> e.getValue() != 0)
                .limit(5)
                .map(e -> new BestCodeRank(e.getKey().getAccount(), e.getKey().getName(), String.valueOf(e.getValue()), "0"))
                .collect(toList());
        
        for (int i = 0; i < sorted.size(); i++) {
            if (i == 0) {
                BestCodeRank bestCodeRank = sorted.get(i);
                bestCodeRank.setRank("1");
            }
            else {
                BestCodeRank bestCodeRank = sorted.get(i);
                if (bestCodeRank.getBestCodeNum().equals(sorted.get(i - 1).getBestCodeNum())) {
                    bestCodeRank.setRank(sorted.get(i - 1).getRank());
                }
                else {
                    bestCodeRank.setRank(String.valueOf(i + 1));
                }
            }
        }
        return sorted;
    }


    private boolean comparePassword(String source, String destination) {
        return source.equals(destination);
    }

    private Language chooseLanguage(String language) {
        switch (language) {
            case "Java":
                return Language.JAVA;
            case "Python":
                return Language.PYTHON;
            default:
                return null;
        }
    }

    private JudgeBehavior chooseBehavior(String category) {
        switch (category) {
            case "輸入輸出":
                return JudgeBehavior.ReadAndPrint;
            case "輸入寫檔":
                return JudgeBehavior.ReadAndWriteFile;
            case "讀檔輸出":
                return JudgeBehavior.ReadFileAndPrint;
            case "讀檔寫檔":
                return JudgeBehavior.ReadFileAndWriteFile;
            default:
                return null;
        }
    }
    
}
