package jp.utsushiiro.springsandbox.jpa.repository;

import jp.utsushiiro.springsandbox.jpa.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
