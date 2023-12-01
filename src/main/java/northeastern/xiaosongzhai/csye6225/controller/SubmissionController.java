package northeastern.xiaosongzhai.csye6225.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.google.gson.JsonObject;
import jakarta.validation.Valid;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import northeastern.xiaosongzhai.csye6225.entity.Submission;
import northeastern.xiaosongzhai.csye6225.entity.SubmissionDTO;
import northeastern.xiaosongzhai.csye6225.repository.SubmissionRepository;
import northeastern.xiaosongzhai.csye6225.response.Response;
import northeastern.xiaosongzhai.csye6225.service.AssignmentService;
import northeastern.xiaosongzhai.csye6225.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/23 19:19
 * @Description: Submission controller
 */
@RestController
@RequestMapping("/v1/assignments")
public class SubmissionController {

    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionService submissionService;

    /**
     * create a submission
     * @param id id
     * @param submissionDTO submissionDTO
     */
    @PostMapping("/{id}/submission")
    public Response<Submission> createSubmission(@PathVariable String id,
                                                 @Valid
                                                 @RequestBody SubmissionDTO submissionDTO,
                                                 Principal principal) {
        Assignment assignment = assignmentService.getAssignmentById(id);

        if (assignment == null) {
            return Response.notFound("Assignment not found");
        }

        LocalDateTime deadline = LocalDateTime.parse(assignment.getDeadline(), DateTimeFormatter.ISO_DATE_TIME);
        if (LocalDateTime.now().isAfter(deadline)) {
            return Response.forbidden("The deadline has passed");
        }

        int countSubmission = submissionRepository.countSubmissionsByIdAndEmail(id, principal.getName());
        if (countSubmission > assignment.getNum_of_attempts()) {
            return Response.forbidden("You have exceeded the number of attempts");
        }

        Submission submission = submissionService.createSubmission(id, submissionDTO);

        publishToSns(submissionDTO.getSubmission_url(), principal.getName());

        return Response.success(submission);

    }

    private void publishToSns(String submissionUrl, String userEmail) {
        // create a new SNS client and set endpoint
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        // get the topic arn
        String topicArn = snsClient.listTopics().getTopics().get(0).getTopicArn();

        // publish to the topic
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("submissionUrl", submissionUrl);
        messageJson.addProperty("userEmail", userEmail);

        PublishRequest publishRequest = new PublishRequest(topicArn, messageJson.toString());

        snsClient.publish(publishRequest);

    }
}
