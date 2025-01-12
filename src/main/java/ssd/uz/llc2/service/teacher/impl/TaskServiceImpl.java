package ssd.uz.llc2.service.teacher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssd.uz.llc2.db.domain.File;
import ssd.uz.llc2.db.domain.Group;
import ssd.uz.llc2.db.domain.User;
import ssd.uz.llc2.db.domain.tasks.Task;
import ssd.uz.llc2.db.repository.admin.GroupRepository;
import ssd.uz.llc2.db.repository.admin.UserRepository;
import ssd.uz.llc2.db.repository.teacher.TaskRepository;
import ssd.uz.llc2.db.repository.teacher.TeacherRepository;
import ssd.uz.llc2.dto.ResponseDto;
import ssd.uz.llc2.dto.form.CreateTaskForm;
import ssd.uz.llc2.helper.SecurityHelper;
import ssd.uz.llc2.service.file.FileService;
import ssd.uz.llc2.service.teacher.TaskService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FileService fileService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ResponseDto saveTask(MultipartFile multipartFile, CreateTaskForm form) throws Exception {
        try {
            User currentUser = SecurityHelper.getCurrentUser();
            List<Long> groupIds;
            if (currentUser != null) {
                groupIds = teacherRepository.getGroupsOfTeacher(currentUser.getId());
            }else {
                throw new Exception("current user not found");
            }
            if (form.getDeadline().before(new Date())) {
                throw new Exception("Talabalarni qiynama!");
            }
            if (!multipartFile.isEmpty() && groupIds.contains(form.getGroupId()) && form.getDeadline() != null && form.getMaxBall() != null && form.getName() != null) {

                File file= (File) fileService.save(multipartFile).getData();

                Optional<Group> optionalGroup = groupRepository.findById(form.getGroupId());
                if (optionalGroup.isEmpty()) {
                    return new ResponseDto<>(false, "Group topilmadi!!!");
                }

                Task task = new Task();
                Group group = optionalGroup.get();
                task.setName(form.getName());
                task.setGroup(group);

                task.setDeadline(form.getDeadline());
                task.setMaxBall(form.getMaxBall());
                task.setFile(file);
                task=taskRepository.save(task);
                if (task.getId() != null && task.getFile() != null) {
                    return new ResponseDto<>(true, "Task yaratildi");
                }
                return new ResponseDto<>(false, "saqlashda xatolik yuz berdi");
            }
            return new ResponseDto<>(false, "Ma'lumotlar to'liq emas!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto editTask(UUID taskId, CreateTaskForm form, MultipartFile multipartFile) throws Exception {


        try {

            User correntUser = SecurityHelper.getCurrentUser();
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (correntUser == null) {
                return new ResponseDto<>(false, "User topilmadi!!!");
            }
            if (optionalTask.isEmpty()) {
                return new ResponseDto<>(false, "Task topilmadi!!!");
            }

            if (optionalTask.get().getFile().getPkey() != null) {
                fileService.delete(optionalTask.get().getFile().getPkey(),optionalTask.get().getFile().getName());
            }
            File file;
            if (!multipartFile.isEmpty()) {
                file = (File) fileService.save(multipartFile).getData();
            } else {
                return new ResponseDto<>(false, "Fayl yuklanmadi");
            }

            Optional<User> optionalTeacher = userRepository.findById(correntUser.getId());
            if (optionalTeacher.isEmpty()) {
                return new ResponseDto<>(false, "O'qtuvchi topilmadi!!!");
            }

            Optional<Group> optionalGroup = groupRepository.findById(form.getGroupId());
            if (optionalGroup.isEmpty()) {
                return new ResponseDto<>(false, "Group topilmadi!!!");
            }

            Task task = new Task();
            task.setName(form.getName());
            task.setFile(file);

            task.setDeadline(form.getDeadline());
            task.setMaxBall(form.getMaxBall());

            taskRepository.save(task);
            return new ResponseDto<>(true, "Task o'zgartirildi");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto deleteTask(UUID taskId) throws Exception {
        try {
            User correntUser = SecurityHelper.getCurrentUser();
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (correntUser == null) {
                return new ResponseDto<>(false, "User topilmadi!!!");
            }
            if (optionalTask.isEmpty()) {
                return new ResponseDto<>(false, "Task topilmadi!!!");
            }

            if (optionalTask.get().getFile().getPkey() != null) {
                fileService.delete(optionalTask.get().getFile().getPkey(),optionalTask.get().getFile().getName())   ;
            }
            taskRepository.delete(optionalTask.get());
            return new ResponseDto(true, "Task o'chirildi");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto findAllWithFiles(Long groupId) throws Exception {
        return new ResponseDto<>(true, "ok", taskRepository.getTasks(groupId));
    }
}
