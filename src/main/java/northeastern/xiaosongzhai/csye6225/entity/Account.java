package northeastern.xiaosongzhai.csye6225.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 15:57
 * @Description: Account entity
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 429694226791200996L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private String id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter(AccessLevel.NONE)
    private String account_created;

    @Setter(AccessLevel.NONE)
    private String account_updated;

}
