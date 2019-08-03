package csim.scu.onlinejudge.manager.impl;

import csim.scu.onlinejudge.common.exception.EntityNotFoundException;
import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.judge.Judge;
import csim.scu.onlinejudge.dao.domain.problem.Problem;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.domain.team.CommentResult;
import csim.scu.onlinejudge.dao.domain.team.Team;
import csim.scu.onlinejudge.manager.TeamManager;
import csim.scu.onlinejudge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamManagerImpl implements TeamManager {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CourseService courseService;

    @Override
    public List<Map<String, String>> correctStuds(String problemId, String account) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        Team team = teamService.findByProblemAndAccount(problem, account);

        List<Map<String, String>> result = new ArrayList<>();

        if (team.getAccount().equals(account)) {
            List<String> correctedAccountList = team.getCorrectedAccount();
            for (String correctedAccount : correctedAccountList) {
                Map<String, String> correctedMap = new HashMap<>();
                Student student = studentService.findByAccount(correctedAccount);
                boolean isJudge = judgeService.existByProblemAndStudent(problem, student);
                if (isJudge) {
                    Judge judge = judgeService.findByProblemAndStudent(problem, student);
                    String code = judge.getHistoryCodes().get(judge.getHistoryCodes().size() - 1).getCode();
                    correctedMap.put("studentAccount", correctedAccount);
                    correctedMap.put("code", code);
                    result.add(correctedMap);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Boolean> checkCorrectStatus(String problemId, String account) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        Team team = teamService.findByProblemAndAccount(problem, account);

        List<String> correctedAccountList = team.getCorrectedAccount();
        Map<String, Boolean> result = new HashMap<>();
        for (String correctedAccount : correctedAccountList) {
            boolean correctStatus = false;
            Team correctedTeam = teamService.findByProblemAndAccount(problem, correctedAccount);
            List<CommentResult> commentResults = correctedTeam.getCommentResult();
            for (CommentResult commentResult : commentResults) {
                if (commentResult.getAccount().equals(account)) {
                    correctStatus = true;
                }
            }
            if (!correctStatus) {
                result.put("status", false);
                return result;
            }
        }
        result.put("status", true);
        return result;
    }

    @Override
    public Map<String, Boolean> checkCorrectedStatus(String problemId, String account) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        List<Team> correctTeam = teamService.findByProblem(problem);
        int count = 0;
        for (Team team : correctTeam) {
            if (team.getCorrectedAccount().contains(account)) {
                count++;
            }
        }
        Team team = teamService.findByProblemAndAccount(problem, account);
        int length = team.getCommentResult().size();

        Map<String, Boolean> result = new HashMap<>();
        boolean status = false;
        if (count == length) {
            status = true;
        }
        result.put("status", status);
        return result;
    }

    @Override
    public List<Map<String, Object>> correctInfo(String problemId, String account) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        Team team = teamService.findByProblemAndAccount(problem, account);

        List<String> correctedAccountList = team.getCorrectedAccount();
        List<Map<String, Object>> result = new ArrayList<>();
        for (String correctedAccount : correctedAccountList) {
            Team correctedTeam = teamService.findByProblemAndAccount(problem, correctedAccount);
            List<CommentResult> commentResults = correctedTeam.getCommentResult();
            for (CommentResult commentResult : commentResults) {
                if (commentResult.getAccount().equals(account)) {
                    Student correctedStudent = studentService.findByAccount(correctedAccount);
                    Judge correctedJudge = judgeService.findByProblemAndStudent(problem, correctedStudent);
                    String code = correctedJudge.getHistoryCodes().get(correctedJudge.getHistoryCodes().size() - 1).getCode();

                    Map<String, Object> commentResultMap = new HashMap<>();
                    commentResultMap.put("studentAccount", correctedAccount);
                    commentResultMap.put("code", code);
                    commentResultMap.put("score", commentResult.getScore());
                    commentResultMap.put("correctValue", commentResult.getCorrectValue());
                    commentResultMap.put("readValue", commentResult.getReadValue());
                    commentResultMap.put("skillValue", commentResult.getSkillValue());
                    commentResultMap.put("completeValue", commentResult.getCompleteValue());
                    commentResultMap.put("wholeValue", commentResult.getWholeValue());
                    result.add(commentResultMap);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> correctedInfo(String problemId, String account) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        Team team = teamService.findByProblemAndAccount(problem, account);
        List<Map<String, Object>> result = new ArrayList<>();
        for (CommentResult commentResult : team.getCommentResult()) {
            Map<String, Object> commentResultMap = new HashMap<>();
            commentResultMap.put("score", commentResult.getScore());
            commentResultMap.put("correctValue", commentResult.getCorrectValue());
            commentResultMap.put("readValue", commentResult.getReadValue());
            commentResultMap.put("skillValue", commentResult.getSkillValue());
            commentResultMap.put("completeValue", commentResult.getCompleteValue());
            commentResultMap.put("wholeValue", commentResult.getWholeValue());
            result.add(commentResultMap);
        }
        return result;
    }

    @Override
    public void submitCorrect(String problemId, String account, List<Map<String, Object>> commentList) throws EntityNotFoundException {
        List<Team> teams = new ArrayList<>();
        for (Map<String, Object> map : commentList) {
            String correctedAccount = (String) map.get("correctedAccount");
            Problem problem = problemService.findById(Long.parseLong(problemId));
            Team team = teamService.findByProblemAndAccount(problem, correctedAccount);
            List<CommentResult> commentResults = team.getCommentResult();

            int score = (int) map.get("score");
            int correctValue = (int) map.get("correctValue");
            int readValue = (int) map.get("readValue");
            int skillValue = (int) map.get("skillValue");
            int completeValue = (int) map.get("completeValue");
            int wholeValue = (int) map.get("wholeValue");
            CommentResult commentResult = new CommentResult(account, score,
                    correctValue, readValue, skillValue, completeValue, wholeValue);
            commentResults.add(commentResult);
            team.setCommentResult(commentResults);
            teams.add(team);
        }
        teamService.saveAll(teams);
    }

    @Override
    public List<Map<String, Object>> discussScore(String problemId) throws EntityNotFoundException {
        Problem problem = problemService.findById(Long.parseLong(problemId));
        List<Team> teams = teamService.findByProblem(problem);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Team team : teams) {
            String account = team.getAccount();
            Student student = studentService.findByAccount(account);
            String name = student.getName();
            String studentClass = student.getStudentClass();
            Course course = problem.getCourse();
            String courseName = course.getName();
            boolean existJudge = judgeService.existByProblemAndStudent(problem, student);
            String score = "未作答";
            if (existJudge) {
                Judge judge = judgeService.findByProblemAndStudent(problem, student);
                score = String.valueOf(judge.getHistoryCodes().get(judge.getHistoryCodes().size() - 1).getScore());
            }

            List<CommentResult> commentResults = team.getCommentResult();
            List<Map<String, Object>> commentResultList = new ArrayList<>();
            for (CommentResult commentResult : commentResults) {
                String studentAccount = commentResult.getAccount();
                double correctValue = commentResult.getCorrectValue();
                double readValue = commentResult.getReadValue();
                double skillValue = commentResult.getSkillValue();
                double completeValue = commentResult.getCompleteValue();
                double wholeValue = commentResult.getWholeValue();

                Map<String, Object> commentResultMap = new HashMap<>();
                commentResultMap.put("studentAccount", studentAccount);
                commentResultMap.put("correctValue", correctValue);
                commentResultMap.put("readValue", readValue);
                commentResultMap.put("skillValue", skillValue);
                commentResultMap.put("completeValue", completeValue);
                commentResultMap.put("wholeValue", wholeValue);
                commentResultList.add(commentResultMap);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("account", account);
            map.put("name", name);
            map.put("studentClass", studentClass);
            map.put("courseName", courseName);
            map.put("score", score);
            map.put("discussedScore", commentResultList);
            result.add(map);
        }
        return result;
    }
}
