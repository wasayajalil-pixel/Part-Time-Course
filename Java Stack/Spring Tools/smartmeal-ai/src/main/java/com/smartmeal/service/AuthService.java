package com.smartmeal.service;
import com.smartmeal.dto.AuthDtos.*; import com.smartmeal.model.User; import com.smartmeal.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.stereotype.Service;
@Service
public class AuthService {
 private final UserRepository users; private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
 public AuthService(UserRepository users){this.users=users;}
 public User register(RegisterRequest r){ if(r.email()==null||r.password()==null||r.fullName()==null) throw new IllegalArgumentException("Name, email and password are required"); if(users.findByEmailIgnoreCase(r.email()).isPresent()) throw new IllegalArgumentException("Email already exists"); User u=new User(); u.setFullName(r.fullName());u.setEmail(r.email().trim().toLowerCase());u.setPasswordHash(encoder.encode(r.password()));u.setAge(r.age());u.setGender(r.gender());u.setHeightCm(r.heightCm());u.setWeightKg(r.weightKg());if(r.goal()!=null)u.setGoal(r.goal());return users.save(u); }
 public User login(LoginRequest r){ User u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid email or password")); if(!encoder.matches(r.password(),u.getPasswordHash())) throw new IllegalArgumentException("Invalid email or password"); return u; }
}
