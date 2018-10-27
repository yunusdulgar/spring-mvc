package tutorial.rest.resources;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class BlogListResource extends ResourceSupport {

  private List<BlogResource> blogs = new ArrayList<>();

  public List<BlogResource> getBlogs() {
    return blogs;
  }

  public void setBlogs(List<BlogResource> blogs) {
    this.blogs = blogs;
  }
}