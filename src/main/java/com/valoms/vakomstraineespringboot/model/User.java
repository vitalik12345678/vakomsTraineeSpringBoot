package com.valoms.vakomstraineespringboot.model;

import com.valoms.vakomstraineespringboot.type.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public", catalog = "vakomtrainee")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "sex_enum")
    @Type(type = "pgsql_enum")
    private SexEnum sex;

    @Column
    private LocalDate birthday;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userLikes")
    private List<Like> like;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "follower")
    private List<FollowersFollowings> followers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "following")
    private List<FollowersFollowings> followings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userComment")
    private List<Comment> comments;
}

