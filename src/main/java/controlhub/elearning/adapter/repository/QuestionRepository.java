package controlhub.elearning.adapter.repository;

import controlhub.elearning.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByLessonId(Long lessonId);

}
