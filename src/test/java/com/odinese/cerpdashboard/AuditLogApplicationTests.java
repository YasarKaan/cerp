package com.odinese.cerpdashboard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class cerpdashboardApplicationTests {

    @Test
    void contextLoads() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
        byte[] bytes = fis.readAllBytes();
        String content = new String(bytes);


        Yaml yaml = new Yaml();
        Object obj = yaml.load(content);
        System.out.println(obj);

    }

}
