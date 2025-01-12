package ssd.uz.llc2.db.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String address;

    private Date birthDate;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

///////////////////////////////////////////////////////////////

//    // Quyidagi konstruktorni qo'shing
//    public User(UUID id, String firstName, String lastName, String phone, String email, Date birthDate,
//                String address, String username, Date createAt, Date updateAt) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.email = email;
//        this.birthDate = birthDate;
//        this.address = address;
//        this.username = username;
//        this.createAt = createAt;
//        this.updateAt = updateAt;
//    }

/////////////////////////////////////////////////////////////////

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "group_student",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id")
//    )
//    @JsonIgnore
//    private List<Group> groups;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
    //    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;


    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean active = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
