package in.g77tech.sb_jwt_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.g77tech.sb_jwt_app.entity.UserEntity;
// import in.g77tech.sb_jwt_app.payload.UserDto;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByUname(String uname);

}
