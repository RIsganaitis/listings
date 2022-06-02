package com.company;

import org.hibernate.type.EnumType;

import javax.persistence.*;

import static com.company.UserType.REGULAR;


@Entity
@Table(name = "Usertable")
public class Usertable {
  @Id
  public String nickname;
  public String password;
  public String name;
  public String surname;
//  @Enumerated(EnumType.STRING)
@Convert(converter = TypeConverter.class)
  public UserType usertype;

    public Usertable() {
    }

    public Usertable(String nickname, String password, String name, String surname, UserType usertype) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.usertype = usertype;
    }
    public Usertable(String nickname, String password, String name, String surname) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.usertype = REGULAR;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }
}
