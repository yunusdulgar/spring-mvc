package tutorial.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.service.util.BlogEntryList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResource;

public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {
  public BlogEntryListResourceAsm() {
    super(BlogController.class, BlogEntryListResource.class);
  }

  @Override
  public BlogEntryListResource toResource(BlogEntryList list) {
    List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(list.getEntries());
    BlogEntryListResource listResource = new BlogEntryListResource();
    listResource.setEntries(resources);
    listResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
    return listResource;
  }
}