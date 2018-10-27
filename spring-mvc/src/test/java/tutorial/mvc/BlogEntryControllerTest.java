package tutorial.mvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tutorial.core.model.BlogEntry;
import tutorial.core.service.BlogEntryService;
import tutorial.rest.mvc.BlogEntryController;

public class BlogEntryControllerTest {

  @InjectMocks
  private BlogEntryController blogEntryController;

  @Mock
  private BlogEntryService blogEntryService;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();

  }

  @Test
  public void getExistingBLogEntry() throws Exception {
    BlogEntry entry = new BlogEntry();
    entry.setId(1L);
    entry.setTitle("Test Title");

    when(blogEntryService.find(1L)).thenReturn(entry);

    mockMvc.perform(get("/rest/blog-entries/1"))
        .andExpect(jsonPath("$.title", is(entry.getTitle())))
        .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
        .andExpect(status().isOk());

  }

  @Test
  public void getNonExistingBLogEntry() throws Exception {
    when(blogEntryService.find(1L)).thenReturn(null);
    mockMvc.perform(get("/rest/blog-entries/1")).andDo(print())
        .andExpect(status().isNotFound());

  }
}
