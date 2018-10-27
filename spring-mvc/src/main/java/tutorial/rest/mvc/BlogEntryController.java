package tutorial.rest.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tutorial.core.model.BlogEntry;
import tutorial.core.service.BlogEntryService;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;

@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {


  private BlogEntryService blogEntryService;

  public BlogEntryController(BlogEntryService blogEntryService){
    this.blogEntryService=blogEntryService;
  }

  @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.GET)
  public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId) {
    BlogEntry entry = blogEntryService.find(blogEntryId);

    if (entry == null)
    {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);

    return new ResponseEntity<>(res, HttpStatus.OK);

  }

}
