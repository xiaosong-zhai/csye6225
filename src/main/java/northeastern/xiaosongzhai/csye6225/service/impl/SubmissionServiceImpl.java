package northeastern.xiaosongzhai.csye6225.service.impl;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import northeastern.xiaosongzhai.csye6225.entity.Submission;
import northeastern.xiaosongzhai.csye6225.entity.SubmissionDTO;
import northeastern.xiaosongzhai.csye6225.repository.SubmissionRepository;
import northeastern.xiaosongzhai.csye6225.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/26 18:49
 * @Description: submission service implementation
 */
@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission createSubmission(String id, SubmissionDTO submissionDTO, Assignment assignment, Account account) {
        String submissionUrl = submissionDTO.getSubmission_url();
        Submission submission = new Submission();
        submission.setId(UUID.randomUUID().toString());
        submission.setSubmission_url(submissionUrl);
        submission.setSubmission_date(LocalDateTime.now().toString());
        submission.setSubmission_updated(LocalDateTime.now().toString());
        submission.setAssignment(assignment);
        submission.setAccount(account);
        return submissionRepository.save(submission);

    }
}
