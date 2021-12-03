package hello.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  //@PersistenceContext
  //  private EntityManager em;

  //  private DataSource dataSource;

  //  public SpringConfig(DataSource dataSource, EntityManager em) {
  //    this.dataSource = dataSource;
  //    this.em = em;
  //  }

  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

  //  @Bean
  //  public TimeTraceAop TimeTraceAop() {
  //    return new TimeTraceAop();
  //  }

  //  @Bean
  //  public MemberRepository memberRepository() {
  //    return new MemoryMemberRepository();
  //return new JdbcMemberRepository(dataSource);
  //return new JdbcTemplateMemberRepository(dataSource);
  // return new JpaMemberRepository(em);
  //}
}
