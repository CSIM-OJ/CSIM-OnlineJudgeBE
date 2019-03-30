package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);
    List<Feedback> findByCourse(Course course);
}
