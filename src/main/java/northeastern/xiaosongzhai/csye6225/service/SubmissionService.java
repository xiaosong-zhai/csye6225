package northeastern.xiaosongzhai.csye6225.service;

import northeastern.xiaosongzhai.csye6225.entity.Submission;
import northeastern.xiaosongzhai.csye6225.entity.SubmissionDTO;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/26 18:49
 * @Description: submission service
 */
public interface SubmissionService {

    Submission createSubmission(String id, SubmissionDTO submissionDTO);

}
