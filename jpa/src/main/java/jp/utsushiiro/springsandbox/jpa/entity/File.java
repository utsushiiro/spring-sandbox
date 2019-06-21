package jp.utsushiiro.springsandbox.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory parentDirectory;
}
