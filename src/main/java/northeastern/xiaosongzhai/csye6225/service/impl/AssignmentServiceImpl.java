package northeastern.xiaosongzhai.csye6225.service.impl;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import northeastern.xiaosongzhai.csye6225.entity.AssignmentDTO;
import northeastern.xiaosongzhai.csye6225.repository.AssignmentRepository;
import northeastern.xiaosongzhai.csye6225.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/4 12:16
 * @Description: Assignment service implementation
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    /**
     * Get all assignments
     * @return List<Assignment>
     */
    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    /**
     * Create an assignment
     * @param assignmentDTO assignmentDTO
     * @return Assignment
     */
    @Override
    public Assignment createAssignment(AssignmentDTO assignmentDTO, Account account) {
        Assignment assignment = new Assignment();
        assignment.setId(UUID.randomUUID().toString());
        assignment.setName(assignmentDTO.getName());
        assignment.setPoints(assignmentDTO.getPoints());
        assignment.setNum_of_attempts(assignmentDTO.getNum_of_attempts());
        assignment.setDeadline(assignmentDTO.getDeadline());
        assignment.setAssignment_created(LocalDateTime.now().toString());
        assignment.setAssignment_updated(LocalDateTime.now().toString());
        assignment.setCreated_by(account);
        return assignmentRepository.save(assignment);
    }

    /**
     * Get an assignment by id
     * @param id uuid
     * @return Assignment
     */
    @Override
    public Assignment getAssignmentById(String id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAssignmentById(String id, AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment != null) {
            assignment.setName(assignmentDTO.getName());
            assignment.setPoints(assignmentDTO.getPoints());
            assignment.setNum_of_attempts(assignmentDTO.getNum_of_attempts());
            assignment.setDeadline(assignmentDTO.getDeadline());
            assignment.setAssignment_updated(LocalDateTime.now().toString());
            assignmentRepository.save(assignment);
        }

    }

    @Override
    public void deleteAssignmentById(String id) {
        assignmentRepository.deleteById(id);
    }
}
