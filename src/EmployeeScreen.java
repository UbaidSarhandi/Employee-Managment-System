import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EmployeeScreen extends Application{
	String Id;
	BackEnd be = new BackEnd();
	ArrayList<String> arr;
	String Attendance =  "Mark Attendance";
	public EmployeeScreen(String Id) {
	this.Id = Id;
	System.out.println(Id);
	arr = be.SelectOne(Id);
	if (be.checkTodaysAttendance(Id)) {
		this.Attendance = "Attendance Marked";
	}
	}
	
	
	
	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
//		********************  Left Pane			
		Group bg = new Group();
		bg.getStyleClass().add("bg");
		Label bgcolors = new Label("",image("Group-12-l.png"));
	
		VBox leftpane = new VBox(40);
		leftpane.getStyleClass().add("leftpane");
		HBox.setHgrow(leftpane, Priority.ALWAYS);
		leftpane.setMaxWidth(490);
		leftpane.setMinWidth(490);
		
//			Logout Button
			Button logout = new Button("  Logout",image("log-out.png"));
			logout.getStyleClass().add("textbutton");
			
//			Profile photo
			VBox dpbox = new VBox(7);
			dpbox.setAlignment(Pos.CENTER);
			Label dp = new Label("",image("Group 21.png"));
			Label username = new Label(arr.get(1));
			username.getStyleClass().add("username");
			Label post = new Label(arr.get(2));
			dpbox.getChildren().addAll(dp,username,post);
			
			Button markAttbtn = new Button(Attendance);
			markAttbtn.getStyleClass().add("updatebtn");
			markAttbtn.setMinWidth(200);
			markAttbtn.setMaxWidth(200);
			Button viewdetails = new Button("View Details");
			viewdetails.getStyleClass().add("salarybtn");
			viewdetails.setMinWidth(200);
			viewdetails.setMaxWidth(200);
			
			VBox btnbox = new VBox(20,markAttbtn,viewdetails);
			btnbox.setAlignment(Pos.CENTER);
			
//			Details view
			
			Label edatailsheading  = new Label("Your Employee Datails");
			edatailsheading.getStyleClass().add("username");
			
			Rectangle headingbar= new Rectangle(100, 100, 50,7);
			headingbar.setFill(Color.web("#18A8DF"));
			
			Label idlbl = new Label("Employee ID");
			Label namelbl = new Label("Name");
			Label designationlbl = new Label("Designation");
			Label doblbl = new Label("Date Of Birth");
			Label joindatelbl = new Label("Join Date");
			Label phonelbl = new Label("Phone");
			Label emaillbl = new Label("E-mail");
			Label basicsalarylbl = new Label("Basic Salary");
			Label allowancelbl = new Label("Allowance");
			Label taxlbl = new Label("Tax");
			Label leavelbl = new Label("Leave");
			VBox detailsdiv1 = new VBox(32,idlbl,namelbl,designationlbl,doblbl,joindatelbl,phonelbl,emaillbl,basicsalarylbl,allowancelbl,taxlbl,leavelbl);
			detailsdiv1.getStyleClass().add("detailsdiv1");
			detailsdiv1.setPrefWidth(150);
			
			Label idtxt = new Label("");
			Label nametxt = new Label("");
			Label designationtxt = new Label("");
			Label dobtxt = new Label("");
			Label joindatetxt = new Label("");
			Label phonetxt = new Label("");
			Label emailtxt = new Label("");
			Label basicsalarytxt = new Label("");
			Label allowancetxt = new Label("");
			Label taxtxt = new Label("");
			Label leavetxt = new Label("");
			
			VBox detailsdiv2 = new VBox(32,idtxt,nametxt,designationtxt,dobtxt,joindatetxt,phonetxt,emailtxt,basicsalarytxt,allowancetxt,taxtxt,leavetxt);
			detailsdiv2.getStyleClass().add("detailsdiv2");
			detailsdiv2.setPrefWidth(150);
			detailsdiv1.setPrefWidth(150);
			
			
			
			Button backbtn =  new Button("Back to Profile");
			backbtn.getStyleClass().add("salarybtn");
			backbtn.setMinWidth(250);
			backbtn.setMaxWidth(550);
			
			HBox detailsdiv = new HBox(detailsdiv1,detailsdiv2);
			
			VBox detailsbox = new VBox(30,edatailsheading,headingbar,detailsdiv,backbtn);
			detailsbox.getStyleClass().add("detailsbox");
		
		leftpane.getChildren().addAll(logout,dpbox,btnbox);
		viewdetails.setOnAction(e->{
			
			bg.getChildren().removeAll(bgcolors,leftpane);
			bg.getChildren().addAll(detailsbox);
			idtxt.setText(arr.get(0));
			nametxt.setText(arr.get(1));
			designationtxt.setText(arr.get(2));
			dobtxt.setText(arr.get(3));
			joindatetxt.setText(arr.get(4));
			phonetxt.setText(arr.get(5));
			emailtxt.setText(arr.get(6));
			basicsalarytxt.setText(arr.get(7));
			allowancetxt.setText(arr.get(8));
			taxtxt.setText(arr.get(9));
			leavetxt.setText(arr.get(10));
			
		});
		
		backbtn.setOnAction(e->{
			
			bg.getChildren().removeAll(detailsbox);
			bg.getChildren().addAll(bgcolors,leftpane);
			
		});
		
		logout.setOnAction(e->{
			LoginScreen ls = new LoginScreen();
			
			try {
				ls.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		markAttbtn.setOnAction(e->{
			if (markAttbtn.getText().equals("Mark Attendance")) {
				be.AttendenceCheckOut(Id);
				markAttbtn.setText("Attendance Marked");
			}
			
		});
		
		bg.getChildren().addAll(bgcolors,leftpane);
		
		
		HBox root = new HBox(bg);
		root.setAlignment(Pos.CENTER);
		
		stage.setTitle("Testing");
		Scene scene = new Scene(root,490,830);
		scene.getStylesheets().add("style.css");
		stage.setScene(scene);
//		stage.setResizable(false);
		stage.show();
	}

//	gets images
	ImageView image(String address) throws FileNotFoundException {
		
		FileInputStream input = new FileInputStream(address);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
		return imageView;	
	}
}