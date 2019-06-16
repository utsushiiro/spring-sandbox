package jp.utsushiiro.springsandbox.jpa.repository;

import jp.utsushiiro.springsandbox.jpa.entity.Directory;
import jp.utsushiiro.springsandbox.jpa.service.DirectoryService;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Integer> {
    /**
     * directory.graph を使うと初段がSUBSELECT でなく JOIN Fetchになる
     */
    @EntityGraph(value = "directory.graph")
    Directory findById(int id);

    /**
     * PK以外のfindだと一意性を保証できないから(?), ManyToOneであってもJOIN FetchでなくSQLがもう一回走る
     * @see DirectoryService#findByNameDemo()
     */
    Directory findByName(String name);
}
