package project.pet.service;

import project.pet.dto.MemberJoinDTO;

public interface MemberService {

    static class MidExistException extends  Exception{


    }

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;
}
