import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginScreen extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		VBox lr = new VBox(10);
//		lr.setAlignment(Pos.TOP_CENTER);
		lr.getStyleClass().add("lr");
			
			Label heading = new Label("Login Page");
			heading.getStyleClass().add("heading");
			
			Label employeeidlbl = new Label("Employee ID");
			TextField employeeidf = new TextField();
			employeeidf.getStyleClass().add("logintf");
			Label employeepasslbl = new Label("Password");
			PasswordField employeepassf = new PasswordField();
			employeepassf.getStyleClass().add("logintf");
			final Label message = new Label("Wrong ID or Password Entered");
			message.setVisible(false);
			Label s = new Label("");
			Button Loginbtn  = new Button("Login");
			Loginbtn.setMinWidth(200);
			Loginbtn.setMaxWidth(500);
			Loginbtn.getStyleClass().add("loginbtn");
			Button cpbtn  = new Button("Change Password?");
			cpbtn.setAlignment(Pos.CENTER);
			cpbtn.getStyleClass().add("textbutton");
			
			BackEnd be = new BackEnd();
		
			lr.getChildren().addAll(heading,employeeidlbl,employeeidf,employeepasslbl,employeepassf,message,s,Loginbtn,cpbtn);
			
			Loginbtn.setOnAction(e->{
				be.createTables(employeeidf.getText(), employeepassf.getText());
				if (be.Loginacc(employeeidf.getText(), employeepassf.getText()) == 1) {
					ProjectX p = new ProjectX(employeeidf.getText());
					try {
						p.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (be.Loginacc(employeeidf.getText(), employeepassf.getText()) == 0) {
					
					EmployeeScreen ES = new EmployeeScreen(employeeidf.getText());
					try {
						ES.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					message.setVisible(true);
					message.setTextFill(Color.rgb(210, 39, 30));
					System.out.println("Wrong Passord");
				}
				
			});
			
			cpbtn.setOnAction(e->{
				changePass cp = new changePass();
				try {
					cp.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		


		stage.setTitle("Testing");
		Scene scene = new Scene(lr,420,500);
		scene.getStylesheets().add("style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	ImageView image(String address) throws FileNotFoundException {
		
		FileInputStream input = new FileInputStream(address);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
		return imageView;
		
	}

}
