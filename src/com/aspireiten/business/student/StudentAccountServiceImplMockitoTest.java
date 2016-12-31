package com.aspireiten.business.student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aspireiten.business.common.enums.QuickJobHiringType;
import com.aspireiten.business.student.dto.StudentRegisterationReqDTO;
import com.aspireiten.business.student.dto.StudentRegisterationRespDTO;
import com.aspireiten.enterprise.common.RoleDaoIF;
import com.aspireiten.enterprise.student.StudentAccountDaoIF;
import com.aspireiten.model.common.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextMockito.xml")
public class StudentAccountServiceImplMockitoTest {

    @InjectMocks
    @Spy
    private StudentAccountServiceIF studentAccountServiceIF = new StudentAccountServiceImpl();

    @Mock
    private StudentAccountDaoIF     studentAccountDaoIF;

    @Mock
    private RoleDaoIF               roleDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setProperty("app-mail-host", "");
        System.setProperty("app-mail-username", "");
        System.setProperty("app-mail-password", "");

        System.setProperty("datasource.url", "");
        System.setProperty("datasource.username", "");
        System.setProperty("datasource.password", "");

    }

    @Test
    public void testRegisterStudentSuccess() throws Exception {
        StudentRegisterationReqDTO studentRegisterationReqDTO = new StudentRegisterationReqDTO();
        studentRegisterationReqDTO.setEmailId("kumar.vimal0503@gmail.com");
        studentRegisterationReqDTO.setFirstName("Vimal");
        studentRegisterationReqDTO.setMobileNumber("982738479237");
        studentRegisterationReqDTO.setPassword("9834834");
        studentRegisterationReqDTO.setConfirmPassword("9834834");
        studentRegisterationReqDTO.setHiringType(QuickJobHiringType.FRESHER.toString());
        Role role = new Role(Role.ROLE_STUDENT);
        Mockito.when(roleDao.get(Mockito.anyString())).thenReturn(role);
        Mockito.doNothing().when(studentAccountDaoIF).registerStudent(Mockito.any(StudentRegisterationReqDTO.class));
        StudentRegisterationRespDTO studentRegisterationRespDTO = studentAccountServiceIF
                .registerStudent(studentRegisterationReqDTO);
        Assert.assertNull(studentRegisterationRespDTO);
    }
}
