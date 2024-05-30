package controlhub.elearning.adapter.repository;

import controlhub.elearning.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
