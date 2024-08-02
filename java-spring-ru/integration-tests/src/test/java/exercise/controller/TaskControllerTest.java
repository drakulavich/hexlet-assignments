package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testCreateTask() throws Exception {
        var task = getTask();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        var response = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
        var body = response.getResponse().getContentAsString();

        assertThatJson(body).isObject().containsEntry("title", task.getTitle());
        assertThatJson(body).isObject().containsEntry("description", task.getDescription());
    }

    @Test
    public void testGetTask() throws Exception {
        var task = taskRepository.save(getTask());

        var response = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var body = response.getResponse().getContentAsString();

        assertThatJson(body).isObject().containsEntry("title", task.getTitle());
        assertThatJson(body).isObject().containsEntry("description", task.getDescription());
    }

    @Test
    public void testUpdateTask() throws Exception {
        var task = taskRepository.save(getTask());
        var updatedTask = getTask();

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updatedTask));

        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = response.getResponse().getContentAsString();

        assertThatJson(body).isObject().containsEntry("title", updatedTask.getTitle());
        assertThatJson(body).isObject().containsEntry("description", updatedTask.getDescription());
    }

    @Test
    public void testDeleteTask() throws Exception {
        var task = taskRepository.save(getTask());

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var deletedTask = taskRepository.findById(task.getId());
        assertThat(deletedTask).isNotPresent();
    }

    private Task getTask() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().sentence())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        return task;
    }
    // END
}
