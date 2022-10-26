package project.pet.service;

import project.pet.dto.ComuBoardDTO;
import project.pet.dto.PageRequestDTO;
import project.pet.dto.PageResponseDTO;
import project.pet.dto.ReviewBoardDTO;

public interface ComuBoardService {

    Long register(ComuBoardDTO comuBoardDTO);

    ComuBoardDTO readOne(Long bno);

    void modify(ComuBoardDTO comuBoardDTO);

    void remove(Long bno);

    PageResponseDTO<ComuBoardDTO> list1(PageRequestDTO pageRequestDTO);
}
