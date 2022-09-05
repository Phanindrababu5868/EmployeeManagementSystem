package com.Quess.FinalProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
//@Table
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organization_id;

    @NotEmpty(message = "organizationName name Should not empty")
    private String organizationName;

    @NotEmpty(message = "Address Should not empty")
    private String address;

    @Pattern(regexp = "^\\d{10}$",message = "enter correct phone number")
    private String phoneNo;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "OrganizationId",referencedColumnName = "organization_id")
    private Set<Employee> Employee=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "organizationId",referencedColumnName = "organization_id")
    private Set<Assets> Assets =new HashSet<>();

}