package in.g77tech.sb_jwt_app.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import in.g77tech.sb_jwt_app.binding.AuthRequest;
import in.g77tech.sb_jwt_app.entity.UserEntity;
import in.g77tech.sb_jwt_app.services.JwtService;
import in.g77tech.sb_jwt_app.services.MyUserDetailsService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private MyUserDetailsService service;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtService jwt;

    // register user
    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user){

        String encodedPwd = encoder.encode(user.getUpwd());
        user.setUpwd(encodedPwd);

        boolean saveUser = service.saveUser(user);
        if (saveUser) {
            return "User Registered Success";
        }
        else{
            return "Registration Failed";
        }
    }


    // login user
    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUname(), request.getPwd());

        try{
            Authentication auth = authManager.authenticate(token);
            if (auth.isAuthenticated()) {
                
                // TODO: Generate jwt token and send it to the user
                String jwtToken = jwt.generateToken(request.getUname());
                return "User Logged In: "+jwtToken;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Invalid Credentials";
        }
        
        return "Invalid Credentials";
        
    }



    //welcome user
    @GetMapping("/welcome")
    public String welcomeMsg(){
        return "Welcome to SB JWT App";
    }

    @GetMapping("/greet")
    public String greetMsg(){
        return "Good Morning ";
    }
}
