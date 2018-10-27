package tutorial.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.model.Account;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.resources.AccountResource;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
  public AccountResourceAsm() {
    super(AccountController.class, AccountResource.class);
  }

  @Override
  public AccountResource toResource(Account account) {
    AccountResource res = new AccountResource();
    res.setName(account.getName());
    res.setPassword(account.getPassword());
    res.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
    return res;
  }
}
