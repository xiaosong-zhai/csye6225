package northeastern.xiaosongzhai.csye6225.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/24 00:22
 * @Description: Submission DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6103902533679096106L;

    private String submission_url;
}
