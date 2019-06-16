package jp.utsushiiro.springsandbox.jpa;

import jp.utsushiiro.springsandbox.jpa.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sandbox implements CommandLineRunner {
    private DirectoryService directoryService;

    @Autowired
    public Sandbox(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Sandbox.class, args);
    }

    /**
     * TODO データのセットアップをServiceに移す
     */
    @Override
    public void run(String... args){
        directoryService.findByIdDemo();
//        Directory node = directoryRepository.findByNameDemo("root");
//        Directory node0 = new Directory();
//        node0.setName("root");
//        directoryRepository.saveAndFlush(node0);
//
//
//        Directory node1_from_0 = new Directory();
//        node1_from_0.setName("node1_from_0");
//
//        Directory node2_from_0 = new Directory();
//        node2_from_0.setName("node2_from_0");
//
//        node0.getChildren().add(node1_from_0);
//        node0.getChildren().add(node2_from_0);
//        node0 = directoryRepository.saveAndFlush(node0);

        System.out.println("exit");

//        Directory node3_from_1 = new Directory();
//        node3_from_1.setName("node3_from_1");
//
//        Directory node4_from_1 = new Directory();
//        node4_from_1.setName("node4_from_1");
//
//
//        node1_from_0.getChildren().add(node3_from_1);
//        node1_from_0.getChildren().add(node4_from_1);
    }
}
