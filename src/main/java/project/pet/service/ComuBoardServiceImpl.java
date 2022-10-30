package project.pet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.pet.domain.ComuBoard;
import project.pet.domain.ReviewBoard;
import project.pet.dto.*;
import project.pet.repository.ComuBoardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ComuBoardServiceImpl implements ComuBoardService {

    private final ModelMapper modelMapper;

    private final ComuBoardRepository comuBoardRepository;

    @Override
    public Long register(ComuBoardDTO comuBoardDTO){

        ComuBoard comuBoard = modelMapper.map(comuBoardDTO,ComuBoard.class);

        Long bno = comuBoardRepository.save(comuBoard).getBno();

        return bno;
    }

    @Override
    public ComuBoardDTO readOne(Long bno){
        Optional<ComuBoard> result = comuBoardRepository.findById(bno);

        ComuBoard comuBoard = result.orElseThrow();

        ComuBoardDTO comuBoardDTO = modelMapper.map(comuBoard, ComuBoardDTO.class);

        return comuBoardDTO;
    }

    @Override
    public void modify(ComuBoardDTO comuBoardDTO){

        Optional<ComuBoard> result = comuBoardRepository.findById(comuBoardDTO.getBno());

        ComuBoard comuBoard = result.orElseThrow();

        comuBoard.change(comuBoardDTO.getTitle(), comuBoardDTO.getContent());

        comuBoardRepository.save(comuBoard);
    }

    @Override
    public void remove(Long bno){
        comuBoardRepository.deleteById(bno);
    }


    @Override
    public PageResponseDTO<ComuBoardDTO> list1(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<ComuBoard> result = comuBoardRepository.searchAll1(types, keyword, pageable);

        List<ComuBoardDTO> dtoList = result.getContent().stream()
                .map(comuBoard -> modelMapper.map(comuBoard, ComuBoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<ComuBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<ComuBoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO){

        String[] types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<ComuBoardListReplyCountDTO> result = comuBoardRepository.searchWithReplyCount(types, keyword, pageable);

        return PageResponseDTO.<ComuBoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }
}
