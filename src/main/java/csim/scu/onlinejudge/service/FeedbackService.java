package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.service.base.BaseService;

import java.util.List;

public interface FeedbackService extends BaseService<Feedback, Long> {

    List<Feedback> findByCourse(Course course);
}
