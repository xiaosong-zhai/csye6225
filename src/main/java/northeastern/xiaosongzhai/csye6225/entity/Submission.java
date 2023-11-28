package northeastern.xiaosongzhai.csye6225.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/23 18:57
 * @Description: Submission entity
 */
@Entity
@Data
public class Submission implements Serializable {

    @Serial
    private static final long serialVersionUID = 5739335812530017263L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private String assignment_id;

    @Column(nullable = false)
    private String submission_url;

    private String submission_date;

    private String submission_updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Assignment assignment;

}
