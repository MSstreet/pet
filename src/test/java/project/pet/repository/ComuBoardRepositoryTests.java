package project.pet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ComuBoardRepositoryTests {

    @Autowired
    private ComuBoardRepository comuBoardRepository;

    @Test
    public void testSearch1(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("bnum").descending());

        comuBoardRepository.search1(pageable);
    }
}
