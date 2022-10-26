package project.pet.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.pet.domain.ComuBoard;

public interface ComuBoardSearch {

    Page<ComuBoard> search2(Pageable pageable);
//
    Page<ComuBoard> searchAll1(String[] types, String keyword, Pageable pageable);



}
