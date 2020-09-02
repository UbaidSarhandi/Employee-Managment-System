import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProjectX extends Application{
	boolean returnAlert= false;
	BackEnd be = new BackEnd();
	ArrayList<ArrayList<String>>  arr = be.selectall();
	int no_of_employees = arr.size();
	HBox hey[]  = new HBox[no_of_employees];
	Button bey[]  = new Button[no_of_employees];
	String name = "Unkown";
	String id;
	public ProjectX() {
		// TODO Auto-generated constructor stub
	}
	public ProjectX(String id) {
	this.id = id;
	ArrayList<String> arr = be.SelectOne(id);
	this.name = arr.get(1);
	}
	
	Label idtxt;
	Label nametxt;
	Label designationtxt;
	Label dobtxt;
	Label joindatetxt;
	Label phonetxt;
	Label emailtxt;
	Label basicsalarytxt;
	Label allowancetxt;
	Label taxtxt;
	Label leavetxt;
	
	HBox dashboard;
	HBox notifbox;
	Label notiftxt;
	
		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage stage) throws Exception {

			
			
			
//			********************  Left Pane			
			Group bg = new Group();
			Label bgcolors = new Label("",image("Group 12.png"));
		
			VBox leftpane = new VBox(40);
			leftpane.getStyleClass().add("leftpane");
			HBox.setHgrow(leftpane, Priority.ALWAYS);
			leftpane.setMaxWidth(340);
			leftpane.setMinWidth(340);
			
//				Logout Button
				Button logout = new Button("  Logout",image("log-out.png"));
				logout.getStyleClass().add("textbutton");
				
//				Profile photo
				VBox dpbox = new VBox(7);
				dpbox.setAlignment(Pos.CENTER);
				Label dp = new Label("",image("Group 21.png"));
				Label username = new Label(name);
				username.getStyleClass().add("username");
				Label post = new Label("HR Manager");
				dpbox.getChildren().addAll(dp,username,post);
			
			leftpane.getChildren().addAll(logout,dpbox);
			
			bg.getChildren().addAll(bgcolors,leftpane);
			
//			********************  Center Pane
			VBox centerpane = new VBox(25);
			centerpane.getStyleClass().add("centerpane");
			HBox.setHgrow(centerpane, Priority.ALWAYS);
			centerpane.setMinWidth(470);
			
//				Count of employees
				Label totalEmplblb = new Label("Total Employees");
				Label countlbl = new Label(String.valueOf(arr.size()));
				countlbl.getStyleClass().add("countlbl");
				VBox countlabel = new VBox(totalEmplblb,countlbl);
				Label counticon = new Label("",image("Group 1.png"));
				VBox icon  = new VBox(counticon);
				HBox countbox = new HBox(10,icon,countlabel);
				countbox.setMaxHeight(60);
				countbox.getStyleClass().add("countbox");
				
//				Notifcation
				Label Notiflbl = new Label("Notification");
				Notiflbl.getStyleClass().add("countlbl");
				notiftxt = new Label("");
				VBox notif = new VBox(Notiflbl,notiftxt);
				Label notificon = new Label("",image("Group 14.png"));
				VBox icon1  = new VBox(notificon);
				notifbox = new HBox(10,icon1,notif);
				notifbox.setMaxHeight(60);
				HBox.setHgrow(notifbox, Priority.ALWAYS);
				notifbox.getStyleClass().add("countbox");
				notifbox.setPrefHeight(200);
				dashboard = new HBox(20,countbox);

//				Search Box
				HBox searchbox = new HBox();
				Label searchlogo = new Label("",image("search.png"));
				searchlogo.getStyleClass().add("searchlogo");
				TextField searchbar = new TextField();
				searchbar.setPromptText("Search employee ID");
				searchbar.setPrefWidth(360);
				searchbar.getStyleClass().add("searchbar");
				Button clear = new Button("",image("Group 20.png"));
				clear.getStyleClass().add("clear");
				Button searchButton = new Button("Search");
				searchButton.setPrefWidth(100);
				searchButton.getStyleClass().add("searchButton");
				
				searchButton.setMinWidth(120);
				Label space = new Label("");
				space.getStyleClass().add("space");
				searchbox.getChildren().addAll(searchlogo,searchbar,clear,space,searchButton);
				
//				list of employees
				
//				heading
				Label employeeslbl  = new Label("Employees");
				employeeslbl.getStyleClass().add("username");
				HBox heading = new HBox(employeeslbl);
						
				Button addEmployee = new Button("Add Employee");
				addEmployee.getStyleClass().add("salarybtn");
				Button createSheet = new Button("Create Attendance Sheet");
				createSheet.getStyleClass().add("updatebtn");
				HBox headercontrols = new HBox(20,addEmployee,createSheet);
				headercontrols.setAlignment(Pos.CENTER_RIGHT);
				HBox.setHgrow(headercontrols, Priority.ALWAYS);
				HBox header = new HBox(heading,headercontrols);

				VBox vbox = new VBox(23);
				ScrollPane scroll = new ScrollPane();
				scroll.setContent(vbox);
				scroll.setFitToWidth(true);
				scroll.getStyleClass().add("scroll");
				scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
				
//				creating employe hbox
			
				for (int i = 0; i < hey.length; i++) {
					
					String a = Integer.toString(i%5);
					String code = "color"+a;
					hey[i]=createEmplyee(arr.get(i).get(1) , arr.get(i).get(0),i,code);
				}
				
//				adding to the vbox
				for (int i = 0; i < hey.length; i++) {
					vbox.getChildren().add(hey[i]);
				}
				
				searchButton.setOnAction(e->{
					for (int i = 0; i < hey.length; i++) {
//						remove all.
						vbox.getChildren().remove(hey[i]);
					}
//					add the searched id.
					for (int i = 0; i < bey.length; i++) {
					if (searchbar.getText().equals(arr.get(i).get(0))) {
						vbox.getChildren().add(hey[i]);
					}
					}
					
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
				
				createSheet.setOnAction(e->{
					be.createAttendenceSheet();
					be.AttendenceCheckOut(id);
					notiftxt.setText("Attendence Sheet Created");
					notification();
				});
				
				clear.setOnAction(e->{
					for (int i = 0; i < hey.length; i++) {
//						remove all.
						vbox.getChildren().remove(hey[i]);
					}
//					add the searched id.
					for (int i = 0; i < bey.length; i++) {
						vbox.getChildren().add(hey[i]);
					
					}
					searchbar.clear();
				});

			centerpane.getChildren().addAll(dashboard,header,searchbox,scroll);
			
//			********************  Right Pane
			VBox rightpane = new VBox(30);
			rightpane.getStyleClass().add("rightpane");
			HBox.setHgrow(rightpane, Priority.ALWAYS);
			rightpane.setPrefWidth(490);
			
	//			heading
				Label edatailsheading  = new Label("Employee Datails");
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
				
				idtxt = new Label();
				nametxt = new Label();
				designationtxt = new Label();
				dobtxt = new Label();
				joindatetxt = new Label();
				phonetxt = new Label();
				emailtxt = new Label();
				basicsalarytxt = new Label();
				allowancetxt = new Label();
				taxtxt = new Label();
				leavetxt = new Label();
				
				VBox detailsdiv2 = new VBox(32,idtxt,nametxt,designationtxt,dobtxt,joindatetxt,phonetxt,emailtxt,basicsalarytxt,allowancetxt,taxtxt,leavetxt);
				detailsdiv2.getStyleClass().add("detailsdiv2");
				
				HBox detailsdiv = new HBox(detailsdiv1,detailsdiv2);
				
				Separator s  = new Separator();
				
				Button salarybtn =  new Button("Send Salary");
				salarybtn.getStyleClass().add("salarybtn");
				Button updatebtn =  new Button("Update");
				updatebtn.getStyleClass().add("updatebtn");
				Button deletebtn =  new Button("Delete");
				deletebtn.getStyleClass().add("deletebtn");
				HBox econtrols = new HBox(20,salarybtn,updatebtn,deletebtn);
				econtrols.setAlignment(Pos.BOTTOM_LEFT);
				
				VBox detailsbox = new VBox(30,edatailsheading,headingbar,detailsdiv,s,econtrols);
				
				salarybtn.setOnAction(e->{
					
					Send salary = new Send("hmanager37", "HelloWorld1", "hmanager37@gmail.com", emailtxt.getText());
					
					salary.sendSalary(Integer.parseInt(basicsalarytxt.getText()), Integer.parseInt(allowancetxt.getText()), Integer.parseInt(taxtxt.getText()), Integer.parseInt((leavetxt.getText())));
					if(true) {
								
						notiftxt.setText("Salary sent Successfully");
					}else {
						notiftxt.setText("Sending Salary Failed");
					}
					notification();
				});
				
			
				Label updateheading  = new Label("Update Employee Datails");
				updateheading.getStyleClass().add("updateheading");
				
				Label fidlbl = new Label("Employee ID");
				Label fnamelbl = new Label("Name");
				Label fdesignationlbl = new Label("Designation");
				Label fdoblbl = new Label("Date Of Birth");
				Label fjoindatelbl = new Label("Join Date");
				Label fphonelbl = new Label("Phone");
				Label femaillbl = new Label("E-mail");
				Label fbasicsalarylbl = new Label("Basic Salary");
				Label fallowancelbl = new Label("Allowance");
				Label ftaxlbl = new Label("Tax");
				Label fleavelbl = new Label("Leave");
				VBox fdetailsdiv1 = new VBox(34,fidlbl,fnamelbl,fdesignationlbl,fdoblbl,fjoindatelbl,fphonelbl,femaillbl,fbasicsalarylbl,fallowancelbl,ftaxlbl,fleavelbl);
				fdetailsdiv1.getStyleClass().add("detailsdiv1");
				fdetailsdiv1.setPrefWidth(150);
				
				TextField fidtxt = new TextField();
				TextField fnametxt = new TextField();
				TextField fdesignationtxt = new TextField();
				TextField fdobtxt = new TextField();
				TextField fjoindatetxt = new TextField();
				TextField fphonetxt = new TextField();
				TextField femailtxt = new TextField();
				TextField fbasicsalarytxt = new TextField();
				TextField fallowancetxt = new TextField();
				TextField ftaxtxt = new TextField();
				TextField fleavetxt = new TextField();
				
				VBox fdetailsdiv2 = new VBox(22,fidtxt,fnametxt,fdesignationtxt,fdobtxt,fjoindatetxt,fphonetxt,femailtxt,fbasicsalarytxt,fallowancetxt,ftaxtxt,fleavetxt);
				fdetailsdiv2.getStyleClass().add("detailsdiv2");
				
				HBox fdetailsdiv = new HBox(fdetailsdiv1,fdetailsdiv2);
				
				Button fdeletebtn =  new Button("   Cancel   ");
				fdeletebtn.getStyleClass().add("deletebtn");
				Button fupdatebtn =  new Button("      Update      ");
				fupdatebtn.getStyleClass().add("updatebtn");
				
				HBox fecontrols = new HBox(20,fdeletebtn,fupdatebtn);
				fecontrols.setAlignment(Pos.BOTTOM_LEFT);
				
				VBox formbox = new VBox(25,updateheading,fdetailsdiv,fecontrols);
				formbox.getStyleClass().add("formbox");
				
				Button addemployee =  new Button("Add Employee");
				addemployee.getStyleClass().add("updatebtn");
				
				updatebtn.setOnAction(e->{
					
					try {
						fecontrols.getChildren().remove(addemployee);
						fecontrols.getChildren().add(fupdatebtn);
					} catch (Exception e1) {
					}
					
					rightpane.getChildren().remove(detailsbox);
					rightpane.getChildren().add(formbox);
					transition(800,0,formbox);
					fidtxt.setText(idtxt.getText());
					fnametxt.setText(nametxt.getText());
					fdesignationtxt.setText(designationtxt.getText());
					fdobtxt.setText(dobtxt.getText());
					fjoindatetxt.setText(joindatetxt.getText());			
					fphonetxt.setText(phonetxt.getText());
					femailtxt.setText(emailtxt.getText());
					fbasicsalarytxt.setText(basicsalarytxt.getText());
					fallowancetxt.setText(allowancetxt.getText());
					ftaxtxt.setText(taxtxt.getText());
					fleavetxt.setText(leavetxt.getText());
					fidtxt.setDisable(true);
					fleavetxt.setDisable(true);
				});
				
				addemployee.setOnAction(e->{
					transition(0,800,formbox);
					PauseTransition pt = new PauseTransition(Duration.seconds(0.5));
					pt.setOnFinished(event -> rightpane.getChildren().remove(formbox));
					pt.play();
					
					try {
						be.insert(fnametxt.getText(), fdesignationtxt.getText(), fdobtxt.getText(), fjoindatetxt.getText(), fphonetxt.getText(), femailtxt.getText(), fbasicsalarytxt.getText(), fallowancetxt.getText(), ftaxtxt.getText(), fleavetxt.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//asdasf
					
					if(true) {
						 this.arr.clear();
						 this.arr = be.selectall();
						 
							for (int i = 0; i < hey.length; i++) {
								vbox.getChildren().remove(hey[i]);
							}
							HBox htemp[] = new HBox[this.arr.size()];
							Button btemp[] = new Button[this.arr.size()];
							hey = htemp;
							bey = btemp;
							for (int i = 0; i < hey.length; i++) {
								
								String a = Integer.toString(i%5);
								String code = "color"+a;
								try {
									hey[i]=createEmplyee(arr.get(i).get(1) , arr.get(i).get(0),i,code);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							for (int i = 0; i < bey.length; i++) {
								vbox.getChildren().add(hey[i]);
							}
						countlbl.setText(String.valueOf(this.arr.size()));
					}
					//asdsad
					PauseTransition pt1 = new PauseTransition(Duration.seconds(0.5));
					pt1.setOnFinished(event -> rightpane.getChildren().add(detailsbox));
					pt1.play();
				});
				
				addEmployee.setOnAction(e->{
					fidtxt.setDisable(true);

					fidtxt.setPromptText("Auto Generated");
					
					rightpane.getChildren().remove(detailsbox);
					rightpane.getChildren().add(formbox);
					transition(800,0,formbox);
					
					try {
						fecontrols.getChildren().add(addemployee);
						fecontrols.getChildren().remove(fupdatebtn);
					} catch (Exception e1) {
					}
				});
				
				fdeletebtn.setOnAction(e->{
					
					transition(0,800,formbox);
					PauseTransition pt = new PauseTransition(Duration.seconds(0.5));
					pt.setOnFinished(event -> rightpane.getChildren().remove(formbox));
					pt.play();
					
					PauseTransition pt1 = new PauseTransition(Duration.seconds(0.5));
					pt1.setOnFinished(event -> rightpane.getChildren().add(detailsbox));
					pt1.play();
					
					try {
						fecontrols.getChildren().remove(addemployee);
						fecontrols.getChildren().add(fupdatebtn);
					} catch (Exception e1) {
					}
				});
				
				fupdatebtn.setOnAction(e->{
					
					transition(0,800,formbox);
					PauseTransition pt = new PauseTransition(Duration.seconds(0.5));
					pt.setOnFinished(event -> rightpane.getChildren().remove(formbox));
					pt.play();
					
					PauseTransition pt1 = new PauseTransition(Duration.seconds(0.5));
					pt1.setOnFinished(event -> rightpane.getChildren().add(detailsbox));
					pt1.play();
					
					boolean check = false;
					if (!fidtxt.getText().isEmpty()) {
				
						System.out.println(fidtxt.getText());
						be.Update(fidtxt.getText(), fnametxt.getText(), fdesignationtxt.getText(), fdobtxt.getText(), fjoindatetxt.getText(), fphonetxt.getText(), femailtxt.getText(), fbasicsalarytxt.getText(), fallowancetxt.getText(), ftaxtxt.getText(), fleavetxt.getText());
						check = true;
						nametxt.setText(fnametxt.getText());
						designationtxt.setText(fdesignationtxt.getText());
						dobtxt.setText( fdobtxt.getText());
						joindatetxt.setText(fjoindatetxt.getText());
						phonetxt.setText(fphonetxt.getText());
						emailtxt.setText(femailtxt.getText());
						basicsalarytxt.setText(fbasicsalarytxt.getText());
						allowancetxt.setText(fallowancetxt.getText());
						taxtxt.setText(ftaxtxt.getText());
						leavetxt.setText(fleavetxt.getText());
					}
					
					if(check){
					 this.arr.clear();
					 this.arr = be.selectall();
						for (int i = 0; i < hey.length; i++) {
							vbox.getChildren().remove(hey[i]);
						}

						for (int i = 0; i < hey.length; i++) {
							
							String a = Integer.toString(i%5);
							String code = "color"+a;
							try {
								hey[i]=createEmplyee(arr.get(i).get(1) , arr.get(i).get(0),i,code);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						for (int i = 0; i < bey.length; i++) {
							vbox.getChildren().add(hey[i]);
						}
						
						notiftxt.setText("Details Updated Successfully.");
					}else {
						notiftxt.setText("Error Updating details");
					}
					
					try {
						fecontrols.getChildren().remove(addemployee);
						fecontrols.getChildren().add(fupdatebtn);
					} catch (Exception e1) {
					}
					
					notification();
				});
				
				deletebtn.setOnAction(e->{
					
					
					if(Alertb("Are you sure you want to delete this employee?")) {
						be.Delete(idtxt.getText());
						 this.arr.clear();
						 this.arr = be.selectall();
						 
							for (int i = 0; i < hey.length; i++) {
								vbox.getChildren().remove(hey[i]);
							}
							HBox htemp[] = new HBox[this.arr.size()];
							Button btemp[] = new Button[this.arr.size()];
							hey = htemp;
							bey = btemp;
							for (int i = 0; i < hey.length; i++) {
								
								String a = Integer.toString(i%5);
								String code = "color"+a;
								try {
									hey[i]=createEmplyee(arr.get(i).get(1) , arr.get(i).get(0),i,code);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							for (int i = 0; i < bey.length; i++) {
								vbox.getChildren().add(hey[i]);
							}
						countlbl.setText(String.valueOf(this.arr.size()));
						notiftxt.setText("Employee Details Deleted");
						idtxt.setText("");
						nametxt.setText("");
						designationtxt.setText("");
						dobtxt.setText("");
						joindatetxt.setText("");
						phonetxt.setText("");
						emailtxt.setText("");
						basicsalarytxt.setText("");
						allowancetxt.setText("");
						taxtxt.setText("");
						leavetxt.setText("");
						
					}else {
						notiftxt.setText("Error Deleting Employee details");
					}
					notification();		
				});
			
			rightpane.getChildren().addAll(detailsbox);
			
//			********************  Root Stuff
			HBox root = new HBox();
			root.getChildren().addAll(bg,centerpane,rightpane);
			stage.setTitle("Testing");
			Scene scene = new Scene(root,1400,830);
			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
			
		}
		
		private String str(String string) {
			return null;
		}

//		gets images
		ImageView image(String address) throws FileNotFoundException {
			
			FileInputStream input = new FileInputStream(address);
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
			return imageView;	
		}
		
//		for creating employee HBox
		HBox createEmplyee(String employeeName, String Id, int n,String color) throws Exception {
			
			HBox ebox = new HBox();
			
			Label id = new Label(Id);
			id.getStyleClass().add(color);
			Label ename = new Label(employeeName);
			Button edetailsbtn = new Button("View Details");
			edetailsbtn.getStyleClass().add("edetailsbtn");
			bey[n] = edetailsbtn;
			HBox ebtnbox = new HBox(edetailsbtn);
			ebtnbox.setAlignment(Pos.CENTER_RIGHT);
			HBox.setHgrow(ebtnbox, Priority.ALWAYS);
			HBox elblbox = new HBox(20,id,ename);
			elblbox.setAlignment(Pos.CENTER_LEFT);
			ebox.getChildren().addAll(elblbox,ebtnbox);
			ebox.getStyleClass().add("ebox");
			
			
			bey[n].setOnAction(e->{
				idtxt.setText(arr.get(n).get(0));
				nametxt.setText(arr.get(n).get(1));
				designationtxt.setText(arr.get(n).get(2));
				dobtxt.setText(arr.get(n).get(3));
				joindatetxt.setText(arr.get(n).get(4));
				phonetxt.setText(arr.get(n).get(5));
				emailtxt.setText(arr.get(n).get(6));
				basicsalarytxt.setText(arr.get(n).get(7));
				allowancetxt.setText(arr.get(n).get(8));
				taxtxt.setText(arr.get(n).get(9));
				leavetxt.setText(String.valueOf(be.calulateLeaves(arr.get(n).get(0))));
			});
			
			return ebox;
			
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
		
	
		
		void transition(int from,int to, VBox box) {
			TranslateTransition tt = new TranslateTransition();
			tt.setDuration(Duration.seconds(0.5));
			
			tt.setFromY(from);
			tt.setToY(to);
			tt.setAutoReverse(true);
			tt.setCycleCount(1);
			tt.setNode(box);
			tt.play();
		}
		
		void transition(int from,int to, HBox box) {
			TranslateTransition tt = new TranslateTransition();
			tt.setDuration(Duration.seconds(0.5));
			
			tt.setFromY(from);
			tt.setToY(to);
			tt.setAutoReverse(true);
			tt.setCycleCount(1);
			tt.setNode(box);
			tt.play();
		}
		
		void notification(){
			try {
				dashboard.getChildren().addAll(notifbox);
				transition(-100, 0, notifbox);
				
				PauseTransition pt = new PauseTransition(Duration.seconds(4));
				pt.setOnFinished(event -> dashboard.getChildren().removeAll(notifbox));
				pt.play();
			} catch (Exception e) {
				
			}
		}
	}