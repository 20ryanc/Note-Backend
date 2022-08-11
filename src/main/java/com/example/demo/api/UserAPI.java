package com.example.demo.api;
import com.example.demo.dao.EntryRepository;
import com.example.demo.model.Entry;
import com.example.demo.model.Entrytmp;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UserAPI {
    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAPI(UserService userService,
                   PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/info")
    public String getUser(Principal principal){
        return principal.getName();
    }

    @PostMapping("/insertEntry")
    public void insertEntry(Principal principal, @RequestBody List<Entrytmp> entryList){
        for(int i = 0; i < entryList.size(); i++){
            Entrytmp etmp = entryList.get(i);
            Entry tmp = new Entry(principal.getName(), etmp.getTitle(), etmp.getContent(), i);
            userService.insertEntry(tmp);
        }
    }

    @GetMapping("/getEntry")
    public List<Entrytmp> getEntry(Principal principal){
        List<Entrytmp> ret = new ArrayList<Entrytmp>();
        for(Entry tmp : userService.getAllEntry(principal.getName())){
            ret.add(new Entrytmp(tmp.getTitle(),tmp.getContent()));
        }
        return ret;
    }

    @DeleteMapping("/deleteEntry")
    public void deleteEntry(Principal principal){
        userService.deleteAllEntry(principal.getName());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }

    @PostMapping("/login")
    public void login(@RequestBody Map<String, String> body) throws AuthenticationException, NoSuchElementException {
        String email = body.get("email");
        String pass = body.get("password");
        Optional<User> tmp = userService.getUser(email);
        User usr = tmp.get();
        if(tmp.isEmpty()){
            throw new BadCredentialsException("Incorrect Email/Password!");
        }
        if(passwordEncoder.matches(pass,usr.getPassword())){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usr.getUsername(),
                            usr.getPassword(), usr.getAuthorities()));
        }else {
            throw new BadCredentialsException("Incorrect Email/Password!");
        }
    }

    @PostMapping("/register")
    public void addUser(@RequestBody Map<String, String> body){
        User user = new User(body.get("email"),passwordEncoder.encode(body.get("password")));
        userService.addUser(user);
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable("email") String email){
        System.out.println(email);
        userService.removeUser(email);
    }

}
