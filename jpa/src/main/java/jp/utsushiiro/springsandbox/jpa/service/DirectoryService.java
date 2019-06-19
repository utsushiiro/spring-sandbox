package jp.utsushiiro.springsandbox.jpa.service;

import jp.utsushiiro.springsandbox.jpa.entity.Directory;
import jp.utsushiiro.springsandbox.jpa.repository.DirectoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectoryService {
    private DirectoryRepository directoryRepository;

    private Logger logger = LoggerFactory.getLogger(DirectoryService.class);

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

    @Transactional
    public void setUp() {
        if (directoryRepository.findByName("root") != null) {
            logger.warn("A root node already exist. Please drop table data.");
            return;
        }

        Directory node0 = new Directory();
        node0.setName("root");

        Directory node1_from_0 = new Directory();
        node1_from_0.setName("node1_from_0");

        Directory node2_from_0 = new Directory();
        node2_from_0.setName("node2_from_0");

        node0.addSubDirectory(node1_from_0);
        node0.addSubDirectory(node2_from_0);

        Directory node3_from_1 = new Directory();
        node3_from_1.setName("node3_from_1");

        Directory node4_from_1 = new Directory();
        node4_from_1.setName("node4_from_1");

        node1_from_0.addSubDirectory(node3_from_1);
        node1_from_0.addSubDirectory(node4_from_1);

        directoryRepository.save(node0);
    }
}
