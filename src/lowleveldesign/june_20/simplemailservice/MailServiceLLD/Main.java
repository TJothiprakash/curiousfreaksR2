package lowleveldesign.june_20.simplemailservice.MailServiceLLD;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MailService mailService = new MailService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[1] Register User\n[2] Send Email\n[3] View Inbox\n[4] View Sent\n[5] View Trash\n[6] Delete Email\n[7] Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    mailService.registerUser(sc.nextLine());
                    break;
                case "2":
                    System.out.print("Sender ID: ");
                    int sId = Integer.parseInt(sc.nextLine());
                    System.out.print("Receiver ID: ");
                    int rId = Integer.parseInt(sc.nextLine());
                    System.out.print("Subject: ");
                    String subject = sc.nextLine();
                    System.out.print("Body: ");
                    String body = sc.nextLine();
                    mailService.sendEmail(sId, rId, subject, body);
                    break;
                case "3":
                    System.out.print("User ID: ");
                    mailService.viewEmails(Integer.parseInt(sc.nextLine()), Tag.INBOX);
                    break;
                case "4":
                    System.out.print("User ID: ");
                    mailService.viewEmails(Integer.parseInt(sc.nextLine()), Tag.SENT);
                    break;
                case "5":
                    System.out.print("User ID: ");
                    mailService.viewEmails(Integer.parseInt(sc.nextLine()), Tag.TRASH);
                    break;
                case "6":
                    System.out.print("User ID: ");
                    int uid = Integer.parseInt(sc.nextLine());
                    System.out.print("Email ID: ");
                    int eid = Integer.parseInt(sc.nextLine());
                    mailService.deleteEmail(uid, eid);
                    break;
                case "7":
                    System.out.println("👋 Exiting...");
                    return;
                default:
                    System.out.println("❗ Invalid input");
            }
        }
    }
}