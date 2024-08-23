package in.g77tech.sb_jwt_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="g77_users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer userId;
    private String uname;
    private String upwd;
    private Long phno;

}
