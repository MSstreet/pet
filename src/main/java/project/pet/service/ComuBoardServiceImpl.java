package project.pet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.pet.domain.ComuBoard;
import project.pet.dto.ComuBoardDTO;
import project.pet.repository.ComuBoardRepository;

import javax.transaction.Transactional;

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

        Long bno = comuBoardRepository.save(comuBoard).get
    }
}
