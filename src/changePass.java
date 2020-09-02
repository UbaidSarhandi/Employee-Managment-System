import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class changePass extends Application{
	boolean returnAlert= false;


	@Override
	public void start(Stage stage) throws Exception {
		
		VBox lr = new VBox(10);
//		lr.setAlignment(Pos.TOP_CENTER);
		lr.getStyleClass().add("lr");
			
			Label heading = new Label("Change Password");
			heading.getStyleClass().add("heading");
			
			Label employeeidlbl = new Label("Employee ID");
			TextField employeeidf = new TextField();
			employeeidf.getStyleClass().add("logintf");
			Label oldp = new Label("Old Password");
			PasswordField oldpf = new PasswordField();
			oldpf.getStyleClass().add("logintf");
			Label newl = new Label("New Password");
			PasswordField newlp = new PasswordField();
			newlp.getStyleClass().add("logintf");
			Label s = new Label("");
			Label message = new Label();
			Button Changebtn  = new Button("Change");
			Changebtn.setMinWidth(200);
			Changebtn.setMaxWidth(500);
			Changebtn.getStyleClass().add("loginbtn");
			Button l  = new Button("Back to Login");
			l.setAlignment(Pos.CENTER);
			l.getStyleClass().add("textbutton");
			
			Changebtn.setOnAction(e->{
				if (Alertb("Are you sure you want to change the passowrd?")) {
				BackEnd be = new BackEnd();
				if (!be.checkAcc(employeeidf.getText(), oldpf.getText(), newlp.getText())) {
					message.setText("Wrong ID or Password Entered");
					message.setVisible(true);
					message.setTextFill(Color.rgb(210, 39, 30));
				}else {
					message.setText("Password Changed");
					message.setVisible(true);
					message.setTextFill(Color.rgb(21, 117, 84));
				}
				}
			});
		
			lr.getChildren().addAll(heading,employeeidlbl,employeeidf,oldp,oldpf,newl,newlp,message,s,Changebtn,l);
			l.setOnAction(e->{
				LoginScreen cp = new LoginScreen();
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
	boolean Alertb(String msg) {
		Stage s = new Stage();
		Label msglbl = new Label(msg);
		returnAlert = false;
		Button confirm  = new Button("Confirm");
		confirm.getStyleClass().add("updatebtn");
		Button cancel  = new Button("Cancel");
		cancel.getStyleClass().add("deletebtn");
		HBox controls = new HBox(20,confirm,cancel);
		HBox.setHgrow(controls, Priority.ALWAYS);
		controls.setAlignment(Pos.BOTTOM_RIGHT);
		VBox root  = new VBox(30,msglbl,controls);
		root.getStyleClass().add("leftpane");
		
		s.setTitle("Alert");
		Scene scene = new Scene(root,420,130);
		scene.getStylesheets().add("style.css");
		s.setScene(scene);
		s.setResizable(false);
		
	
		confirm.setOnAction(e->{
			returnAlert =  true;
			s.close();
		});
		
		cancel.setOnAction(e->{
			returnAlert = false;
			s.close();
		});
		
		s.showAndWait();
		
		return returnAlert;
	}
	
}
