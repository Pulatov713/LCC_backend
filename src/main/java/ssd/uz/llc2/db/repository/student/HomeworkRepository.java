package ssd.uz.llc2.db.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssd.uz.llc2.db.domain.tasks.Homework;

import java.util.UUID;
@Repository
public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
}
