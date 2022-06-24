import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
public class iFinder {

	private static String studentFilePath = "C:\\Users\\sketty\\eclipse-workspace\\GH Care\\src\\resources\\students.txt";


	public static void main(String[] args) throws IOException {
		iFinder ifinder = new iFinder();
		ifinder.showHomePage();
	}

	public void showHomePage() {
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
		} else if (selectedAction.equals(2)) {
			this.searchForStudentPage();
		}
		else if (selectedAction.equals(5)) {
			this.exitApp();
		}

	}

	public void showAddAStudentPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Full Name: ");
		String fullName = scanner.nextLine();
		System.out.print("Index no.: ");
		String indexNo = scanner.nextLine();
		System.out.print("Email Address: ");
		String email = scanner.nextLine();
		System.out.print("Program Of Study: ");
		String programOfStudy = scanner.nextLine();
		System.out.print("Phone Number: ");
		String phoneNumber = scanner.nextLine();
		System.out.print("Nickname: ");
		String nickname = scanner.nextLine();

		System.out.println("Press [y] to save");
		String confimrationAction = scanner.nextLine();
		if (confimrationAction.toLowerCase().equals("y")) {
			Boolean isWrittenToFile = this.writeStudentToFile(fullName, indexNo, email, programOfStudy, phoneNumber, nickname);
			this.askToExitOrPerformAnotherAction(isWrittenToFile);
		}
	}

	public void askToExitOrPerformAnotherAction(Boolean isWrittenToFile) {
		System.out.println("-------------------------");
		if (isWrittenToFile) {
			System.out.println("Student information successfully saved");
			Scanner in = new Scanner(System.in);
			System.out.println("Do you wish to perform another action? Press 'y' or 'n'");
			String performAnotherAction = in.nextLine();
			if (performAnotherAction.strip().toLowerCase().equals("y")) {
				this.showHomePage();
			} else{
				this.exitApp();
			}
		} else{
			System.out.println("Saving student information failed. Try again later");
			this.exitApp();
		}

	}

	public boolean writeStudentToFile(String fullName, String indexNo, String email, String programOfStudy, String phoneNumber, String nickname) {
		try {
			fullName = fullName.strip();
			indexNo = indexNo.strip();
			email = email.strip();
			programOfStudy = programOfStudy.strip();
			phoneNumber = phoneNumber.strip();
			nickname = nickname.strip();
			Date mDate = new Date();
			String dateToday = mDate.toString();
			String registrationDate = dateToday;


			String studentLine = indexNo + "," + fullName + "," + email + "," + programOfStudy + "," + phoneNumber + "," + nickname + "," + registrationDate + "\n";
			BufferedWriter out = new BufferedWriter(new FileWriter(this.studentFilePath, true));

			System.out.println("student_line == " + studentLine);
			out.append(studentLine);
			out.close();

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void searchForStudentPage() {
		/* Do scanner things */
		Scanner in = new Scanner(System.in);
		System.out.println("*** Search For Student ***");
		System.out.println("Enter student index number to find student information: ");
		String indexNo = in.nextLine().strip();
		String foundStudent = this.searchForStudentWithIndexNumber(indexNo);
		if (foundStudent.length() > 0) {
			System.out.println("Your search matched one students: ");
			System.out.println(foundStudent);
		} else {
			System.out.println("No student found our system with provided index number");

		}
		Boolean studentFound =  foundStudent.length() > 0;
		this.askToExitOrPerformAnotherAction(studentFound);

	}

	public String searchForStudentWithIndexNumber(String searchTerm) {
		List<String> dbStudents;
		ArrayList<String> foundItems = new ArrayList<String>();
		String foundStudent = null;
		Boolean isMatched = false;
		try {
			dbStudents = Files.readAllLines(Path.of(this.studentFilePath));
			for (String student: dbStudents) {
				String[] dbStudent = student.split(",");
				isMatched = dbStudent[0].toLowerCase().matches("(\\w|\\s)*(" + searchTerm + ")(\\w|\\s)*");
				if (isMatched) {
					foundStudent = student;
				}
				isMatched = false;
			}
			System.out.println(foundItems);
			return foundStudent;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void exitApp() {
		Runtime.getRuntime().exit(0);
	}

}
