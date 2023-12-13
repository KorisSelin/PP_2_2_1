package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//      userService.add();

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("model_1", 11));
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user22@mail.ru");
      user2.setCar(new Car("model_2", 22));
      userService.add(user2);

      User user3 = new User("User3", "Lastname3", "user333@mail.ru");
      userService.add(user3);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         try {
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         } catch (NullPointerException e){
            System.out.println("Нет машины");
         }
         System.out.println();
      }
      try {
         System.out.println(" \n Владелец машины model_2 22: " + userService.getUserByCar("model_2", 22));
         System.out.println(" \n Владелец машины model_2 12345: " + userService.getUserByCar("model_2", 12345));
      } catch (NoResultException n){
         System.err.println("Машина отсутствует");
      }
      context.close();
   }
}
