package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
   
    
    }
  
  @Scope("prototype")
  static class PrototypeBean {
    
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }
    
    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
