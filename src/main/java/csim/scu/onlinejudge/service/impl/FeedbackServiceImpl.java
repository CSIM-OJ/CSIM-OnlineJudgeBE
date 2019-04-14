package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.feedback.Feedback;
import csim.scu.onlinejudge.dao.repository.FeedbackRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.FeedbackService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Long> implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public BaseRepository<Feedback, Long> getBaseRepository() {
        return feedbackRepository;
    }

    @Override
    public List<Feedback> findByCourse(Course course) {
        return feedbackRepository.findByCourse(course);
    }

}
