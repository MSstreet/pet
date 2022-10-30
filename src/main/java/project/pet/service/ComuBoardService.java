package project.pet.service;

import project.pet.dto.*;

public interface ComuBoardService {

    Long register(ComuBoardDTO comuBoardDTO);

    ComuBoardDTO readOne(Long bno);

    void modify(ComuBoardDTO comuBoardDTO);

    void remove(Long bno);

    PageResponseDTO<ComuBoardDTO> list1(PageRequestDTO pageRequestDTO);

    PageResponseDTO<ComuBoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
