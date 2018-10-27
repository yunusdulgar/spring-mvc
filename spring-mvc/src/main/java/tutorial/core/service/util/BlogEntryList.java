package tutorial.core.service.util;

import java.util.ArrayList;
import java.util.List;
import tutorial.core.model.BlogEntry;

public class BlogEntryList {

  private List<BlogEntry> entries = new ArrayList<BlogEntry>();
  private Long blogId;

  public List<BlogEntry> getEntries() {
    return entries;
  }

  public void setEntries(List<BlogEntry> entries) {
    this.entries = entries;
  }

  public Long getBlogId() {
    return blogId;
  }

  public void setBlogId(Long blogId) {
    this.blogId = blogId;
  }
}
