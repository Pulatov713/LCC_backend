//package ssd.uz.llc2.db.repository.admin;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import ssd.uz.llc2.db.domain.Teacher;
//import ssd.uz.llc2.db.domain.students.Student;
//
//@Repository
//public interface TeachersRepository extends JpaRepository<Teacher, Long> {
//
//    @Query(value = "SELECT * FROM students WHERE id || ' ' || first_name ILIKE %:searching% OR last_name ILIKE %:searching%",
//            nativeQuery = true)
//    Page<Student> search(@Param("searching") String searching, Pageable pageable);
//
//
//
//}
