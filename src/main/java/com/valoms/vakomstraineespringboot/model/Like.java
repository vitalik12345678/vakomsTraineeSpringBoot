package com.valoms.vakomstraineespringboot.model;


import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_post_like", schema = "public", catalog = "vakomtrainee")
public class Like implements Convertable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postLikes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userLikes;

}