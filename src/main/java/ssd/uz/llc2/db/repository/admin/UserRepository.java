package ssd.uz.llc2.db.repository.admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ssd.uz.llc2.db.domain.User;
import ssd.uz.llc2.db.domain.customDto.admin.UserCustomDtoForAdmin;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);


    @Query(value = "SELECT u.id, u.first_name as firstName, u.last_name as lastName, u.username  FROM users u JOIN group_student ug ON u.id = ug.student_id WHERE ug.group_id = :id and u.active", nativeQuery = true)
    Page<UserCustomDtoForAdmin> getStudentsOfGroup(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM group_student WHERE group_id = :id ", nativeQuery = true)
    Optional<Long> countStudentsByGroupId(@Param("id") Long id);


    @Query(value = "select a from users a where a.role.id=:roleId")
    Page<User> findAllByRoleId(@Param("roleId") Long roleId, Pageable pageable);

    @Query(value = "select * from users u where u.role_id=:roleId and u.active IS TRUE ", nativeQuery = true)
    Page<User> findAllByRoleIdAndActive(@Param("roleId") Long roleId, Pageable pageable);

    @Query(value = "select * from users u where u.role_id=:roleId and u.active IS FALSE ", nativeQuery = true)
    Page<User> findAllByRoleIdAndNotActive(@Param("roleId") Long roleId, Pageable pageable);


    @Query(value = "select new map(a.id as id, a.username as username) from users a where a.role.id=2 and a.active")
    List<Map<String, Object>> findTeachersForSelect();

    //STUDENT

    @Query("SELECT u FROM users u JOIN u.role r WHERE r.id = 3 AND " +
            "LOWER(CONCAT(u.id, u.firstName, u.lastName)) LIKE LOWER(CONCAT('%', :searching, '%'))")
    Page<User> search(@Param("searching") String searching, Pageable pageable);
//
//    @Query("SELECT (u.id, u.firstName, u.lastName, u.phone, u.email, u.birthDate, u.address, u.username, u.createAt, u.updateAt) " +
//            "FROM users u WHERE u.role.id = 3")
//    List<User> findAll();

/////////////////////////////////////////////////////////////////////

    //TEACHER

//    @Query("SELECT u FROM users u JOIN u.role r WHERE r.id = 2 AND " +
//            "LOWER(CONCAT(u.id, u.firstName, u.lastName)) LIKE LOWER(CONCAT('%', :searching, '%'))")
//    Page<User> search(@Param("searching") String searching, Pageable pageable);
//
//    @Query("SELECT (u.id, u.firstName, u.lastName, u.phone, u.email, u.birthDate, u.address, u.username, u.createAt, u.updateAt) " +
//            "FROM users u WHERE u.role.id = 2")
//    List<User>findAll();

/////////////////////////////////////////////////////////////////////


    @Query("SELECT c FROM users c WHERE LOWER(CONCAT(c.id, c.firstName, c.lastName,c.username,c.phone)) LIKE LOWER(CONCAT('%', :searching, '%')) and c.role.id=:roleId and c.active=:isActive")
    Page<User> searchInRoleId(@Param("searching") String searching, @Param("roleId") Long roleId, @Param("isActive") boolean isActive, Pageable pageable);

    @Query("SELECT c FROM users c WHERE LOWER(CONCAT(c.id, c.firstName, c.lastName,c.username,c.phone)) LIKE LOWER(CONCAT('%', :searching, '%')) and c.role.id=:roleId ")
    Page<User> searchInRoleIdAll(@Param("searching") String searching, @Param("roleId") Long roleId, Pageable pageable);


}