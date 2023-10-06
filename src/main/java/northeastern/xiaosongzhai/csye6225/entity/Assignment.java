package northeastern.xiaosongzhai.csye6225.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 16:40
 * @Description: Assignment entity
 */
@Entity
@Data
public class Assignment implements Serializable {
    @Serial
    private static final long serialVersionUID = 5739335812530097263L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private Integer points;

    @Column(nullable = false)
    @Min(1)
    @Max(100)
    private Integer num_of_attempts;

    @Column(nullable = false)
    private String deadline;

    private String assignment_created;

    private String assignment_updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account created_by;
}
