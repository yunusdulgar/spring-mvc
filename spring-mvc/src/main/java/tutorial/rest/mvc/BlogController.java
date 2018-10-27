package tutorial.rest.mvc;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.service.BlogService;
import tutorial.core.service.exceptions.BlogNotFoundException;
import tutorial.core.service.util.BlogEntryList;
import tutorial.core.service.util.BlogList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.BlogListResource;
import tutorial.rest.resources.BlogResource;
import tutorial.rest.resources.asm.BlogEntryListResourceAsm;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;
import tutorial.rest.resources.asm.BlogListResourceAsm;
import tutorial.rest.resources.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

  private BlogService blogService;

  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<BlogListResource> findAllBlogs() {
    BlogList blogList = blogService.findAllBlogs();
    BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
    return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
  }

  @RequestMapping(value = "/{blogId}",
      method = RequestMethod.GET)
  public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
    Blog blog = blogService.findBlog(blogId);
    BlogResource res = new BlogResourceAsm().toResource(blog);
    return new ResponseEntity<BlogResource>(res, HttpStatus.OK);
  }

  @RequestMapping(value = "/{blogId}/blog-entries",
      method = RequestMethod.POST)
  public ResponseEntity<BlogEntryResource> createBlogEntry(
      @PathVariable Long blogId,
      @RequestBody BlogEntryResource sentBlogEntry
  ) {
    BlogEntry createdBlogEntry = null;
    try {
      createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
      BlogEntryResource createdResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
      return new ResponseEntity<BlogEntryResource>(createdResource, headers, HttpStatus.CREATED);
    } catch (BlogNotFoundException e) {
      throw new NotFoundException(e);
    }
  }

  @RequestMapping(value = "/{blogId}/blog-entries")
  public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
      @PathVariable Long blogId) {
    try {
      BlogEntryList list = blogService.findAllBlogEntries(blogId);
      BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
      return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
    } catch (BlogNotFoundException exception) {
      throw new NotFoundException(exception);
    }
  }

}
