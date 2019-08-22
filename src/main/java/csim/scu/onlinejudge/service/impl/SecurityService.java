package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.admin.Admin;
import csim.scu.onlinejudge.dao.domain.assistant.Assistant;
import csim.scu.onlinejudge.dao.domain.student.Student;
import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import csim.scu.onlinejudge.dao.repository.AdminRepository;
import csim.scu.onlinejudge.dao.repository.AssistantRepository;
import csim.scu.onlinejudge.dao.repository.StudentRepository;
import csim.scu.onlinejudge.dao.repository.TeacherRepository;
import csim.scu.onlinejudge.service.AdminService;
import csim.scu.onlinejudge.service.AssistantService;
import csim.scu.onlinejudge.service.StudentService;
import csim.scu.onlinejudge.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private AssistantRepository assistantRepository;
    private AdminRepository adminRepository;

    @Autowired
    public SecurityService(StudentRepository studentRepository,
                           TeacherRepository teacherRepository,
                           AssistantRepository assistantRepository,
                           AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.assistantRepository = assistantRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        boolean existStudent = studentRepository.existsByAccount(account);
        boolean existTeacher = teacherRepository.existsByAccount(account);
        boolean existAssistant = assistantRepository.existsByAccount(account);
        boolean existAdmin = adminRepository.existsByAccount(account);
        if (existStudent) {
            Student student = studentRepository.findByAccount(account).get();
            return student;
        }
        else if (existTeacher) {
            Teacher teacher = teacherRepository.findByAccount(account).get();
            return teacher;
        }
        else if (existAssistant) {
            Assistant assistant = assistantRepository.findByAccount(account).get();
            return assistant;
        }
        else if (existAdmin) {
            Admin admin = adminRepository.findByAccount(account).get();
            return admin;
        }
        else {
            throw new UsernameNotFoundException("帳戶不存在!");
        }
    }
}
