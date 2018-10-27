package tutorial.core.service;

import tutorial.core.model.Account;
import tutorial.core.model.Blog;

public interface AccountService {

  Account findAccount(Long id);

  Account createAccount(Account data);

  Blog createBlog(Long accountId, Blog data);
}
