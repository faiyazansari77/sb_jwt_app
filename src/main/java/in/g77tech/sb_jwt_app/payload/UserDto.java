package in.g77tech.sb_jwt_app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;
    private String uname;
    private String upwd;
    private Long phno;
}
