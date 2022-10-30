package project.pet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewBoardListReplyCountDTO {

    private Long bno;

    private String title;

    private String writer;

    private LocalDateTime regDate;

    private Long replyCount;
}
