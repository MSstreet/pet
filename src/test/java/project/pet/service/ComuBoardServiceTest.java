package project.pet.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import project.pet.dto.ComuBoardDTO;
import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;

@SpringBootTest
@Log4j2
public class ComuBoardServiceTest {

    @Autowired
    private ComuBoardService comuBoardService;


    @Test
    public void testlist(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ComuBoardDTO> responseDTO = comuBoardService.list1(pageRequestDTO);

        log.info(responseDTO);
    }
}
