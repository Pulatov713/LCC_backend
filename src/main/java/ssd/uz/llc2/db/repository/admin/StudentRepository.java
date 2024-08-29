//package ssd.uz.llc2.db.repository.admin;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import ssd.uz.llc2.db.domain.User;
//import ssd.uz.llc2.db.domain.students.Student;
//
//import java.util.List;
//
//import java.util.UUID;
//
//@Repository
//public interface StudentRepository extends JpaRepository<User, UUID> {
//
//
//    @Query("SELECT u FROM users u WHERE u.role.id = 3 AND " +
//            "LOWER(CONCAT(u.id, u.firstName, u.lastName)) LIKE LOWER(CONCAT('%', :searching, '%'))")
//    Page<Student> search(@Param("searching") String searching, Pageable pageable);
//
//
//    @Query("SELECT User(u.id, u.firstName, u.lastName, u.email, u.birthDate, u.username, u.createAt, u.updateAt ) FROM users u WHERE u.role.id = 3")
//    List<User> findAll();
//
//
//
////    @Query(value = "SELECT * FROM students WHERE first_name || ' ' || last_name ILIKE %:searching% OR email ILIKE %:searching%", nativeQuery = true)
////    Page<Student> search(@Param("searching") String searching, Pageable pageable);
//
//    // Qidiruv metodini yozish
////    @Query("SELECT s FROM Student s WHERE " +
////            "LOWER(CONCAT(s.id, ' ', s.firstName, ' ', s.lastName)) " +
////            "LIKE LOWER(CONCAT('%', :searching, '%'))")
////    Page<Student> search(@Param("searching") String searching, Pageable pageable);
//
////    @Query(value = "SELECT new map(c.id as id, c.firstName as firstName) FROM Student c")
////    List<Map<String, Object>> findStudentForSelect();
//
////    @Query("SELECT new map(u.first_name as firstName, u.last_name as lastName, u.phone as phone, u.email as email, " +
////            "u.birth_date as birthDate, u.address as address, u.username as username, u.create_at as createdAt, " +
////            "u.update_at as updatedAt) " +
////            "FROM Users u WHERE u.role_id = 3")
////    List<Map<String, Object>> findStudentForSelect();
//
//
//    //    search students
////@Query(value = "SELECT id, first_name AS firstName, last_name AS lastName " +
////        "FROM students " +
////        "WHERE id = :id " +
////        "OR first_name ILIKE CONCAT('%', :keyword, '%') " +
////        "OR last_name ILIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
////List<StudentDto> search(@Param("id") UUID id, @Param("keyword") String keyword);
//
////    @Query(value = "SELECT id, first_name AS firstName, last_name AS lastName " +
////            "FROM students " +
////            "WHERE id = CAST(:id AS UUID) " +
////            "OR first_name ILIKE CONCAT('%', :keyword, '%') " +
////            "OR last_name ILIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
////    List<Student> search(@Param("id") Long id, @Param("keyword") String keyword);
//}
