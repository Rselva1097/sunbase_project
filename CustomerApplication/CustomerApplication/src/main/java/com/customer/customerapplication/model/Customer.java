package com.customer.customerapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class   Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String street;

    private String address;

    private String city;

    private String state;

    private String email;

    private String phone;

    private String password;
    private String username;

    @ManyToMany(fetch =FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name ="user_roles",
            joinColumns= @JoinColumn(name="user_id", referencedColumnName ="id"),
            inverseJoinColumns =@JoinColumn(name="role_id", referencedColumnName ="id"))
    private Set<Role> role;

}
