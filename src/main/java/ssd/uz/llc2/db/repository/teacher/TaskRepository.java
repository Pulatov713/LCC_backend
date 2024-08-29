package ssd.uz.llc2.db.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssd.uz.llc2.db.domain.customDto.teacher.TaskList;
import ssd.uz.llc2.db.domain.tasks.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = " select t.id, t.name  from tasks t  " +
            " where group_id=:groupId", nativeQuery = true)
    List<TaskList> getTasks(@Param("groupId") Long groupId);

//    @Query(value = "SELECT t.id, t.name, g.teacher_id " +
//            "FROM tasks t " +
//            "JOIN groups g ON t.group_id = g.id " +
//            "WHERE t.group_id = :groupId", nativeQuery = true)
//    List<TaskList> getTasks(@Param("groupId") Long groupId);

}
