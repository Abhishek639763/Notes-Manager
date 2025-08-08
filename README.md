📒 NotesApp – Simple Notes Manager (Java)

📌 Overview

NotesApp is a Java console-based application that allows you to manage notes stored in a text file.

It demonstrates Java File I/O concepts such as:

FileReader, FileWriter

BufferedReader, BufferedWriter

Append vs Overwrite mode

Try-with-resources

Exception handling with separate catch blocks for each type of exception

This project was built as part of Task 4 to practice reading and writing text files in Java with proper resource management.


//*****************************************************************************


⚙ Features

Add Note (Append) → Adds a new note to the file without deleting old notes.

Overwrite Notes → Replaces the entire file content with new notes.

List All Notes → Displays all saved notes with numbering.

Delete a Note → Removes a note by its number.

Clear All Notes → Deletes all notes from the file.

Search Notes → Finds notes containing a keyword.



//****************************************************************************


🛠 Technologies Used

Java 11+

File I/O API (java.io)

Collections API (java.util)



//****************************************************************************


📂 Project Structure

NotesApp.java   # Main Java source file

notes.txt       # Created automatically to store notes

README.md       # Project documentation



//****************************************************************************


🚀 How to Run


Save the file as NotesApp.java.

Open terminal/command prompt in the folder containing the file.

Compile the program: javac NotesApp.java

Run the program: java NotesApp


//***************************************************************************


📖 Usage Example

=== Simple Notes Manager ===

Menu:

1. Add note (append)

2. Overwrite notes (replace file)

3. List all notes

4. Delete a note (by number)

5. Clear all notes

6. Search notes (keyword)

0. Exit

Choose: 1

Enter your note (single line): Buy groceries

Note appended.


//***************************************************************************


📚 Learning Outcomes

Understanding FileReader and BufferedReader for reading text files.

Using FileWriter and BufferedWriter for writing to text files.

Difference between append mode and overwrite mode.

Handling exceptions like:

FileNotFoundException

IOException

NumberFormatException

Using try-with-resources to auto-close streams.


//***************************************************************************


🖊 Author

Abhishek Sharma

Bachelor of Information Technology (2021–2025)

Software Developer 

If you want, I can also add screenshots of sample runs and a UML diagram to make this README even more impressive for your submission. That will make it look like a polished portfolio project.
