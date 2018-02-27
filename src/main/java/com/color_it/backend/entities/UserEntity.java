package com.color_it.backend.entities;


import javax.persistence.*;

@Entity
@Table(name="\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nickname"}, name = "nickname_constraint"),
        @UniqueConstraint(columnNames = {"email"}, name = "email_constraint")
})
public class UserEntity extends AbstractEntity {
    @Id
    @GeneratedValue()
    private Integer id;
    @Column(name="nickname")
    private String nickname;
    @Column(name="email")
    private String email;
    @Column(name="passwordHash")
    private String passwordHash;
    @Column(name="avatarPath")
    private String avatarPath;
    @Column(name="countGames")
    private Integer countGames;
    @Column(name="countWins")
    private Integer countWins;

    public UserEntity() {}
    public UserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
    }

    public UserEntity(String nickname, String password) {
        this.nickname = nickname;
        this.passwordHash = password;
    }


    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getCountGames() {
        return countGames;
    }

    public void setCountGames(Integer countGames) {
        this.countGames = countGames;
    }

    public Integer getCountWins() {
        return countWins;
    }

    public void setCountWins(Integer countWins) {
        this.countWins = countWins;
    }
}
