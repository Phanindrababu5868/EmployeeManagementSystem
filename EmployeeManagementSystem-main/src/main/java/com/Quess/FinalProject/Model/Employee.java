package com.Quess.FinalProject.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Employee_id;
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 4,message = "Name should be min 4 char")
    private String name;


    @Min(18)
    @Max(60)
    private int age;

    @NotEmpty(message = "Gender should not blank")
    private String gender;

    @NotEmpty(message = "address should not blank")
    private String address;

    @NotEmpty
    @Pattern(regexp = "^\\d{10}$",message = "entre correct phone number")
    private String phoneNo;

    @Min(value = 10000)
    private long salary;

    @Email(message = "Entre valid Email")
    @Column(unique = true)
    private String email;

    @Column
    @Min(value = 1,message = "Entre  organization id")
    private int organizationId;

    @Pattern(message="password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit with atleast 6 characters",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EmployeeRole", joinColumns = @JoinColumn(name="employeeid", referencedColumnName = "Employee_id"),inverseJoinColumns = @JoinColumn(name="roleid",referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return  authorities;
    }


    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
