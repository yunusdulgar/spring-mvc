package tutorial.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.model.Blog;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogResource;

public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource> {
  public BlogResourceAsm() {
    super(BlogController.class, BlogResource.class);
  }

  @Override
  public BlogResource toResource(Blog blog) {
    BlogResource resource = new BlogResource();
    resource.setTitle(blog.getTitle());
    resource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
    resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("entries").withRel("entries"));
    if(blog.getOwner() != null)
      resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
    return resource;
  }
}