package com.lmartino.todolist.repository;

import com.lmartino.todolist.configuration.JpaConfiguration;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.repository.model.UserTask;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static com.lmartino.todolist.repository.model.User.builder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { JpaConfiguration.class },
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class UserTaskRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void givenUser_whenSaveTask_thenGetOk() {
        final User user = builder().username("test").password("psw123").build();

        final User savedUser = userRepository.save(user);

        final UserTask userTask = UserTask.builder().user(savedUser).checked(false).description("Test description").lastUpdate(new Date()).build();

        final UserTask savedUserTask = repository.save(userTask);
        assertNotNull(savedUserTask);
        assertThat(savedUserTask.getUser(), is(savedUser));
        assertThat(savedUserTask.getDescription(), is(userTask.getDescription()));
        assertThat(savedUserTask.getLastUpdate(), is(userTask.getLastUpdate()));
        assertThat(savedUserTask.isChecked(), is(userTask.isChecked()));
    }

    @Test
    public void givenUserAndTask_whenDeleteTask_thenResultIsOk() {
        final User user = builder().username("test").password("psw123").build();

        final User savedUser = userRepository.save(user);

        final UserTask userTask = UserTask.builder().user(savedUser).checked(false).description("Test description").lastUpdate(new Date()).build();

        final UserTask savedUserTask = repository.save(userTask);
        assertNotNull(savedUserTask);

        repository.deleteById(savedUserTask.getId());

        final Optional<UserTask> getUserTask = repository.findById(savedUserTask.getId());
        assertFalse(getUserTask.isPresent());
    }


    @Test
    public void givenUserAndTask_whenCheckedTask_thenTaskIsUpdated() throws InterruptedException {
        final User user = builder().username("test").password("psw123").build();

        final User savedUser = userRepository.save(user);

        final Date firstDateTime = new Date();
        final UserTask userTask = UserTask.builder().user(savedUser).checked(false).description("Test description").lastUpdate(firstDateTime).build();

        System.out.println(userTask);

        final UserTask savedUserTask = repository.save(userTask);
        assertNotNull(savedUserTask);

        Thread.sleep(2000); // sleep to check that last update datetime has changed

        savedUserTask.setChecked(true);
        final Date secondDateTime = new Date();
        savedUserTask.setLastUpdate(secondDateTime);
        System.out.println(savedUserTask);
        repository.save(savedUserTask);

        final Optional<UserTask> getUserTask = repository.findById(savedUserTask.getId());
        assertTrue(getUserTask.isPresent());
        assertThat(getUserTask.get().isChecked(), is(true));
        assertThat(getUserTask.get().getLastUpdate(), is(greaterThan(firstDateTime)));
    }



}