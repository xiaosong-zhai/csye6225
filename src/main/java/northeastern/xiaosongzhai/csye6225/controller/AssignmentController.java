package northeastern.xiaosongzhai.csye6225.controller;

import jakarta.validation.Valid;
import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import northeastern.xiaosongzhai.csye6225.entity.AssignmentDTO;
import northeastern.xiaosongzhai.csye6225.repository.AccountRepository;
import northeastern.xiaosongzhai.csye6225.response.Response;
import northeastern.xiaosongzhai.csye6225.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/4 12:10
 * @Description: Assignment controller
 */
@RestController
@RequestMapping("/v1/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get all assignments
     * @return Response<List<Assignment>>
     */
    @GetMapping
    public Response<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return Response.success(assignments);
    }

    /**
     * create an assignment
     * @param assignmentDTO assignmentDTO
     * @return Response<Assignment>
     */
    @PostMapping
    public Response<Assignment> createAssignment(@Valid @RequestBody AssignmentDTO assignmentDTO,
                                                 Principal principal) {
        Account creator = accountRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Assignment assignment = assignmentService.createAssignment(assignmentDTO, creator);
        return Response.created(assignment);
    }

    /**
     * get an assignment by id
     * @param id id
     * @return Response<Assignment>
     */
    @GetMapping("/{id}")
    public Response<Assignment> getAssignmentById(@PathVariable String id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return Response.success(assignment);
    }

    /**
     * update an assignment by id
     * @param id id
     * @param assignmentDTO assignmentDTO
     * @return Response<null>
     */
    @PutMapping("/{id}")
    public Response updateAssignmentById(@PathVariable String id,
                                                     @Valid
                                                     @RequestBody AssignmentDTO assignmentDTO,
                                                     Principal principal) {
        Account creator = accountRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Assignment assignment = assignmentService.getAssignmentById(id);
        if (assignment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        if (!assignment.getCreated_by().getId().equals(creator.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this assignment");
        }
        assignmentService.updateAssignmentById(id, assignmentDTO);
        return Response.noContent();
    }

    /**
     * delete an assignment by id
     * @param id id
     * @return Response<null>
     */
    @DeleteMapping("/{id}")
    public Response deleteAssignmentById(@PathVariable String id,
                                         Principal principal) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        if (assignment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        Account creator = accountRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!assignment.getCreated_by().getId().equals(creator.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this assignment");
        }
        assignmentService.deleteAssignmentById(id);
        return Response.noContent();
    }
}
