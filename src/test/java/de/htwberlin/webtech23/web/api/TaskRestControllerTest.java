package de.htwberlin.webtech23.web.api;

import de.htwberlin.webtech23.service.TaskService;
import de.htwberlin.webtech23.web.api.Task;
import de.htwberlin.webtech23.service.TaskService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskRestController.class)
class TaskRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    @DisplayName("should return found tasks from task service")
     void should_return_found_tasks_from_task_service() throws Exception {
    // given
    var tasks = List.of(
            new Task(1, "sort", "sort your books"),
            new Task(2, "clean", "clean your room"));
    doReturn(tasks).when(taskService).findAll();

    // when
    mockMvc.perform(get("/api/v1/tasks"))
    // then
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.size()").value(2))
    .andExpect(jsonPath("$[0].id").value(1))
    .andExpect(jsonPath("$[0].taskName").value("sort"))
    .andExpect(jsonPath("$[0].taskDescription").value("sort your books"))
    .andExpect(jsonPath("$[0].taskDone").value(false))
    .andExpect(jsonPath("$[1].id").value(2))
    .andExpect(jsonPath("$[1].taskName").value("clean"))
    .andExpect(jsonPath("$[1].taskDescription").value("clean your room"))
    .andExpect(jsonPath("$[1].taskDone").value(false));
    }

    @Test
    @DisplayName("should return 404 if task is not found")
    void should_return_404_if_task_is_not_found() throws Exception {
    // given
    doReturn(null).when(taskService).findById(anyLong());

    // when
    mockMvc.perform(get("/api/v1/tasks/123"))
    // then
    .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a task")
    void should_return_201_http_status_and_location_header_when_creating_a_task() throws Exception {
    // given
    String taskToCreateAsJson = "{\"taskName\": \"clean\", \"taskDescription\":\"clean your room\", \"taskDone\": false}";
    var task = new Task(123, null, null);
    doReturn(task).when(taskService).create(any());

    // when
    mockMvc.perform(
    post("/api/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskToCreateAsJson))
    // then
    .andExpect(status().isCreated())
    .andExpect(header().exists("Location"))
    .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/tasks/" + task.getId()))));
//  .andExpect(header().string("Location", Matchers.containsString(Long.toString(task.getId()))));

    }

    @Test
    @DisplayName("should validate create task request")
    void should_validate_create_task_request() throws Exception {
    // given
    String taskToCreateAsJson = "{\"taskName\": \"a\", \"description\":\"\", \"taskDone\": false}";

    // when
    mockMvc.perform(
    post("/api/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(taskToCreateAsJson))
    // then
    .andExpect(status().isBadRequest());
    }
}
