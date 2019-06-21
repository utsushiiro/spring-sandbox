package jp.utsushiiro.springsandbox.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * children の FetchType を　FetchType.EAGER にすると SUBSELECT + 再帰的 となり N+1 が起きてしまう
 * そこで directory.graph のように一段の EntityGraph で EntityGraphType.FETCH とすると JOIN FETCH + 一段 となる
 * ただ FetchType.EAGER だとデフォルトの挙動は JOIN っぽいのに SUBSELECT な挙動なのなんでだろう, Self Referencingだから？
 * @link https://www.baeldung.com/spring-data-jpa-named-entity-graphs
 * @link http://shengwangi.blogspot.com/2015/05/all-about-hibernate-fetch-when-and-how.html
 *
 *
 * 追記:
 * FetchType.LAZY でも SUBSELECT の挙動になっており, これで十分かもしれない
 * どうやら@FetchはSpring Data JPAでは無視されている？
 * @link https://stackoverflow.com/questions/29602386/how-does-the-fetchmode-work-in-spring-data-jpa
 *
 * まとめると, @OneToMany で FetchType.LAZY だと 都度SUBSELECT で FetchType.EAGER だと 再帰的に初手SUBSELECT
 *
 * TODO このあたりの挙動を自己参照(再起)な構造じゃない単純なケースでも調べる
 */
@Entity
@Table(name = "directories")
@Data
@NamedEntityGraph(name="directory.graph",
        attributeNodes = {
                @NamedAttributeNode(value="children")
        }
)
// 下記のNamedEntityGraph は EntityGraphType.FETCH だと MultipleBagFetchException
@NamedEntityGraph(name="directory.graph.invalid",
        attributeNodes = {
                @NamedAttributeNode(value="children", subgraph="subdirectory.graph")
        },
        subgraphs = {
                @NamedSubgraph(name="subdirectory.graph",
                        attributeNodes = {
                                @NamedAttributeNode(value="children")
                        }
                )
        }
)
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_directory_id")
    private Directory parent;

    /**
     * Directory新規作成時等に子と一緒に作成をするために初期化しておく必要あり
     *
     * JoinColumnを使わずにOneToManyのmappedByを使ったほうが効率が良い
     * @link https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    // @JoinColumn(name = "parent_directory_id")
    private List<Directory> children = new ArrayList<>();

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    /**
     * JoinColumnを使わずにOneToManyのmappedByを使う場合に必要
     */
    public void addSubDirectory(Directory directory) {
        children.add(directory);
        directory.setParent(this);
    }

    /**
     *　JoinColumnを使わずにOneToManyのmappedByを使う場合に必要
     */
    public void removeSubDirectory(Directory directory) {
        children.add(directory);
        directory.setParent(this);
    }

    public void addFile(File file) {
        files.add(file);
        file.setParentDirectory(this);
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
