package northeastern.xiaosongzhai.csye6225.service;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import northeastern.xiaosongzhai.csye6225.entity.AssignmentDTO;

import java.util.List;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/4 12:15
 * @Description: Assignment service
 */
public interface AssignmentService {

    List<Assignment> getAllAssignments();

    Assignment createAssignment(AssignmentDTO assignmentDTO, Account account);

    Assignment getAssignmentById(String id);

    void updateAssignmentById(String id, AssignmentDTO assignmentDTO);

    void deleteAssignmentById(String id);
}
