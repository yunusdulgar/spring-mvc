package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.service.util.BlogList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogListResource;

public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

  public BlogListResourceAsm()
  {
    super(BlogController.class, BlogListResource.class);
  }

  @Override
  public BlogListResource toResource(BlogList blogList) {
    BlogListResource res = new BlogListResource();
    res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
    return res;
  }
}