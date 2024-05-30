package controlhub.elearning.adapter.mapper;

import controlhub.elearning.adapter.dto.QuestionDto;
import controlhub.elearning.domain.Question;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionMapper {

    public static QuestionDto toDTO(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setScore(question.getScore());
        dto.setType(question.getType());
        dto.setLessonId(question.getLesson().getId());
        dto.setOptions(question.getOptions());
        dto.setCorrectAnswer(question.getCorrectAnswer());
        return dto;
    }

    public static Question toEntity(QuestionDto dto) {
        Question question = new Question();
        question.setId(dto.getId());
        question.setText(dto.getText());
        question.setScore(dto.getScore());
        question.setType(dto.getType());
        // lesson should be set by service layer to avoid loading issues
        question.setOptions(dto.getOptions());
        question.setCorrectAnswer(dto.getCorrectAnswer());
        return question;
    }

}
