package accounts.frame;

import accounts.dev.Build2;
import accounts.hom.Build3;
import accounts.prd.Build4;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaFxAdapter extends Application {

    private ApplicationContext ctx;

    @Override
    public void init() {
        System.out.println("Starting Spring...");
        //ctx = new AnnotationConfigApplicationContext(Build2.class);
        //ctx = new AnnotationConfigApplicationContext(Build3.class);
        ctx = new AnnotationConfigApplicationContext(Build4.class);
    }

    @Override
    public void start(Stage stage) {
        var form = ctx.getBean(TransferForm.class);
        form.mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
