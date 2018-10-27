package tutorial.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.model.BlogEntry;
import tutorial.rest.mvc.BlogEntryController;
import tutorial.rest.resources.BlogEntryResource;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

  public BlogEntryResourceAsm() {
    super(BlogEntryController.class, BlogEntryResource.class);
  }

  @Override
  public BlogEntryResource toResource(BlogEntry blogEntry) {

    BlogEntryResource res = new BlogEntryResource();
    res.setTitle(blogEntry.getTitle());
    Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
    res.add(link.withSelfRel());
    return res;
  }
}
