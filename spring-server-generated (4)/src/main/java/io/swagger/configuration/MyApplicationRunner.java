//package io.swagger.configuration;
//
//import io.swagger.dao.UserRepository;
//import io.swagger.model.User;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@ConditionalOnProperty(prefix = "guitarshop.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
//public class MyApplicationRunner implements ApplicationRunner {
//
//    private UserRepository userRepository;
//
//    public MyApplicationRunner(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        List<User> users =
//                Arrays.asList(
//                        new User((long) 1, "Bill", "Nye", "billnye@email.com", "test", "0612345678", "20-11-1990", "20-10-2019", User.RankEnum.EMPLOYEE, User.StatusEnum.ACTIVE),
//                        new User((long) 2, "Henk", "Anders", "henkanders@email.com", "test","0687654321", "25-10-1994", "05-02-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE),
//                        new User((long) 3, "Klaas", "Vaak", "klaasvaak@email.com", "test", "0600112233", "02-12-1993", "10-03-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE));
//
//        //users.forEach(userRepository::save);
//
//        //userRepository.findAll().forEach(System.out::println);
//
//    }
//}
