package com.valoms.vakomstraineespringboot.model;



import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import com.valoms.vakomstraineespringboot.type.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.util.List;

@Data
@TypeDef(name = "JsonType",typeClass = JsonType.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post", schema = "public", catalog = "vakomtrainee")
public class Post implements Convertable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    @Type(type = "JsonType")
    private LocationJSON location;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postLikes")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postComment")
    private List<Comment> comments;

}