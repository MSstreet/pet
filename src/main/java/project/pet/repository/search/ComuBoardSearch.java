package project.pet.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.pet.domain.ComuBoard;

public interface ComuBoardSearch {

    Page<ComuBoard> search1(Pageable pageable);

    Page<ComuBoard> searchAll(String[] types, String keyword, Pageable pageable);



}
