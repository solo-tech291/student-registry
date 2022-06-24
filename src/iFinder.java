import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
public class iFinder {

	public static void main(String[] args)throws IOException {
		iFinder ifinder = new iFinder();
		ifinder.showHomePage();
	}
	
	 public boolean showHomePage() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("---------------------------------");
	        System.out.println("*** iFinder ***");
	        System.out.println("---------------------------------");
	        System.out.println("");
	        System.out.println("1. Add a student");
	        System.out.println("2. Search for a student");
	        System.out.println("3. Delete a record ");
	        System.out.println("4. List all records ");
	        System.out.println("5. Exit ");
	        System.out.println("");
	        System.out.print("Enter choice [1 - 5]: ");
	        Integer selectedAction = scanner.nextInt();

	        if (selectedAction.equals(1)) {
	            this.showAddAStudentPage();
	          } 
	        else if(selectedAction.equals(5)) {
	            this.exitApp();
	        }
	        return true;
	    }
	
	 public boolean showAddAStudentPage() { 
		 	Scanner scanner = new Scanner(System.in);
	        System.out.print("Full Name: ");
	        String fullName = scanner.nextLine();
	        System.out.print("Index no.: ");
	        String indexNo = scanner.nextLine();
	        System.out.print("Email Address: ");
	        String Email = scanner.nextLine();
	        System.out.print("Program Of Study: ");
	        String programOfStudy = scanner.nextLine();
	        System.out.print("Phone Number: ");
	        String phoneNumber = scanner.nextLine();
	        System.out.print("Nickname: ");
	        String nickname = scanner.nextLine();
	        System.out.print("Date Of System Registeration: ");
			String systemRegisteration = scanner.nextLine();
			System.out.println("Press [y] to save");
	        String confimrationAction = scanner.nextLine();
	        if (confimrationAction.toLowerCase().equals("y")) {
	            System.out.println(showAddAStudentPage());
	        }
	        return true;
	        
	        }
	 
	 public boolean writeStudentToFile() {
		return false;
	 }
	 public void exitApp() {
		 	System.exit(0);
	    }

}
