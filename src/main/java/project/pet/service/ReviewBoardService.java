package project.pet.service;

import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;
import project.pet.dto.ReviewBoardDTO;

public interface ReviewBoardService {

    Long resister(ReviewBoardDTO reviewBoardDTO);

    ReviewBoardDTO readOne(Long bnum);

    void modify(ReviewBoardDTO reviewBoardDTO);

    void remove(Long bnum);

    PageResponseDTO<ReviewBoardDTO> list(PageRequestDTO pageRequestDTO);
}
