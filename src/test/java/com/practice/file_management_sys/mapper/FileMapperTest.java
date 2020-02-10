package com.practice.file_management_sys.mapper;

import com.practice.file_management_sys.domain.FMSFile;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class FileMapperTest {

    @Autowired
    private FileMapper mapper;

    @Test
    void addFile() {

    }

    @Test
    void uploadHistory() {
        List<FMSFile> list = mapper.uploadHistory("shuaimou77@gmail.com");
        for (FMSFile file : list){
            System.out.println(file.toString());
        }
    }

    @Test
    void downloadHistory() {
        List<FMSFile> list = mapper.downloadHistory("shuaimou77@gmail.com");
        for (FMSFile file : list){
            System.out.println(file.toString());
        }
    }
}