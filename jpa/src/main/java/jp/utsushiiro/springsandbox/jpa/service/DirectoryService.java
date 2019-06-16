package jp.utsushiiro.springsandbox.jpa.service;

import jp.utsushiiro.springsandbox.jpa.entity.Directory;
import jp.utsushiiro.springsandbox.jpa.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectoryService {
    private DirectoryRepository directoryRepository;

    @Autowired
    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @Transactional
    public void findByIdDemo() {
        Directory node = directoryRepository.findById(1);
        System.out.println(node.getChildren().get(0).getChildren());
    }

    @Transactional
    public void findByNameDemo() {
        Directory node = directoryRepository.findByName("root");
        System.out.println(node.getParent());
    }
}
