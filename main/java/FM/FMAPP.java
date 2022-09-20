/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FM;

import FMDAOExceptions.FMExceptions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author magicman
 */
public class FMAPP {
    
    // This is where we are running our Application, We are creating our applicationContexts obejct
    // and passing our applicationContexts.xml, the xml has all our beans 
    // we create our controller object and get the bean from our controller class and call our run method
    public static void main(String[] args) throws FMExceptions {
        
          ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContexts.xml");
        Controller controller
                = ctx.getBean("controller", Controller.class);
        controller.run();
    }
}
