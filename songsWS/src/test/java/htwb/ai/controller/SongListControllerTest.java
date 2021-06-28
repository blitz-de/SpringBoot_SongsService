package htwb.ai.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SongListControllerTest {


    @Test
    public void simpleTest(){

    }
}
