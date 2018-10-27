package tutorial.core.service.util;

import java.util.ArrayList;
import java.util.List;
import tutorial.core.model.Blog;

public class BlogList {

  private List<Blog> blogs = new ArrayList<>();

  public List<Blog> getBlogs() {
    return blogs;
  }

  public void setBlogs(List<Blog> blogs) {
    this.blogs = blogs;
  }
}
