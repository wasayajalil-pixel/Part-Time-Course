package com.smartmeal.dto;
public class AuthDtos {
 public record RegisterRequest(String fullName,String email,String password,Integer age,String gender,Double heightCm,Double weightKg,String goal){}
 public record LoginRequest(String email,String password){}
}
