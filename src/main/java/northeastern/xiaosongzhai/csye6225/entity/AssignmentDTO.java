package northeastern.xiaosongzhai.csye6225.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/4 12:25
 * @Description: AccountsVO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -704009862108901953L;

    private String name;
    private Integer points;
    private Integer num_of_attempts;
    private String deadline;

}
