package com.example.educationalplatformsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name should be not null")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotNull(message = "Password should be not null")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @Column()
    private String role;


    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Trainee trainee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
