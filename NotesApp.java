import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_PATH = "notes.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Simple Notes Manager ===");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addNoteAppend(); break;
                case "2": overwriteNotes(); break;
                case "3": listNotes(); break;
                case "4": deleteNoteByLine(); break;
                case "5": clearNotes(); break;
                case "6": searchNotes(); break;
                case "0":
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice — try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add note (append)");
        System.out.println("2. Overwrite notes (replace file)");
        System.out.println("3. List all notes");
        System.out.println("4. Delete a note (by number)");
        System.out.println("5. Clear all notes");
        System.out.println("6. Search notes (keyword)");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    // 1. Append a note to the file
    private static void addNoteAppend() {
        System.out.print("Enter your note (single line): ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(note);
            bw.newLine();
            System.out.println("Note appended.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("I/O error while appending note: " + e.getMessage());
        }
    }

    // 2. Overwrite all notes
    private static void overwriteNotes() {
        System.out.println("Enter notes (multiple lines). Type a single line with END to finish:");
        List<String> lines = new ArrayList<>();

        while (true) {
            String line = scanner.nextLine();
            if ("END".equals(line)) break;
            lines.add(line);
        }

        try (FileWriter fw = new FileWriter(FILE_PATH, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("File overwritten with " + lines.size() + " line(s).");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("I/O error while overwriting notes: " + e.getMessage());
        }
    }

    // 3. List all notes
    private static void listNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int number = 1;
            boolean any = false;

            System.out.println("\n--- Notes ---");
            while ((line = br.readLine()) != null) {
                any = true;
                System.out.printf("%d. %s%n", number++, line);
            }
            if (!any) System.out.println("(no notes)");
            System.out.println("-------------");
        } catch (FileNotFoundException e) {
            System.out.println("(notes file not found — no notes yet)");
        } catch (IOException e) {
            System.err.println("I/O error while reading notes: " + e.getMessage());
        }
    }

    // 4. Delete a note by number
    private static void deleteNoteByLine() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String s;
            while ((s = br.readLine()) != null) lines.add(s);
        } catch (FileNotFoundException e) {
            System.out.println("(no notes to delete — file not found)");
            return;
        } catch (IOException e) {
            System.err.println("I/O error while loading notes: " + e.getMessage());
            return;
        }

        if (lines.isEmpty()) {
            System.out.println("(no notes to delete)");
            return;
        }

        System.out.println("Current notes:");
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, lines.get(i));
        }

        System.out.print("Enter note number to delete: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim());
            if (idx < 1 || idx > lines.size()) {
                System.out.println("Number out of range.");
                return;
            }
            lines.remove(idx - 1);

            try (FileWriter fw = new FileWriter(FILE_PATH, false);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
                System.out.println("Deleted note #" + idx + ".");
            } catch (FileNotFoundException e) {
                System.err.println("File not found while saving: " + FILE_PATH);
            } catch (IOException e) {
                System.err.println("I/O error while saving notes: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

    // 5. Clear all notes
    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_PATH, false)) {
            System.out.println("All notes cleared.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("I/O error while clearing notes: " + e.getMessage());
        }
    }

    // 6. Search notes by keyword
    private static void searchNotes() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int number = 1;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(keyword)) {
                    System.out.printf("%d. %s%n", number, line);
                    found = true;
                }
                number++;
            }
            if (!found) System.out.println("No matches found.");
        } catch (FileNotFoundException e) {
            System.out.println("(no notes found — file not found)");
        } catch (IOException e) {
            System.err.println("I/O error while searching notes: " + e.getMessage());
        }
    }
}
// NotesApp.java - A simple command-line notes manager in Java