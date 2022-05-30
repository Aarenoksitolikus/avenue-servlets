package ru.avenue.dev.servlets.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private Timestamp createTime;
    private Role role;
}
