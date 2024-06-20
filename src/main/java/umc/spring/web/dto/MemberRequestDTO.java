package umc.spring.web.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        Integer age;
        String address;
        String spec_address;
        String status;
        List<Long> category;
    }
}