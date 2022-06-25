import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
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
		} else if (selectedAction.equals(4)) {
			this.listAllStudentRecordsPage();
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
			if (isWrittenToFile) {
				System.out.println("Student information successfully saved");
			} else {
				System.out.println("Saving student information failed. Try again later");
			}
		}
		this.askToExitOrPerformAnotherAction();
	}

	public void askToExitOrPerformAnotherAction(){
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------");
		System.out.println("Do you wish to perform another action? Press 'y' or 'n'");
		Scanner in = new Scanner(System.in);
		String performAnotherAction = in.nextLine();
		if (performAnotherAction.strip().toLowerCase().equals("y")) {
			this.showHomePage();
		} else {
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
		this.askToExitOrPerformAnotherAction();

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

    public void listAllStudentRecordsPage() {
		System.out.println("");
		System.out.println("");
        List<String> students = this.readFrommFileAllStudents();
		System.out.println("There are " + students.size() + " students in our database");
		for(String student: students) {
            String[] studentArr = student.split(",");
            String indexNo = studentArr[0];
			String name = studentArr[1];
			String email = studentArr[2];
			String program = studentArr[3];
			String phoneNumber = studentArr[4];
			String nickname = studentArr[5];
			String registrationDate = studentArr[6];

			System.out.println("Index No.: " + indexNo);
			System.out.println("Name: " + name);
			System.out.println("Email: " + email);
			System.out.println("Program: " + program);
			System.out.println("Phone Number: " + phoneNumber);
			System.out.println("Nickname: " + nickname);
			System.out.println("Registration Date: " + registrationDate);
			System.out.println("");
			System.out.println("--------------------------------");
			System.out.println("");
        }
		this.askToExitOrPerformAnotherAction();
    }

    public List<String> readFrommFileAllStudents() {;
        try {
            List<String> dbStudents = Files.readAllLines(Path.of(this.studentFilePath));
            return dbStudents;
        } catch (IOException e) {
            return null;
        }
    }

	public void exitApp() {
		Runtime.getRuntime().exit(0);
	}

}
