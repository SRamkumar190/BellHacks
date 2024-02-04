import java.io.FileWriter;
import java.io.IOException;

public class CourseHTMLGenerator {

    public static void main(String[] args) {
        generateHTML();
    }

    public static void generateHTML() {
        try (FileWriter writer = new FileWriter("courses.html")) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html lang=\"en\">\n");
            writer.write("<head>\n");
            writer.write("    <meta charset=\"UTF-8\">\n");
            writer.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            writer.write("    <link rel=\"stylesheet\" href=\"styles.css\">\n");
            writer.write("    <title>Courses</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");

            // Side Panel with Reminders, Assignments, and Announcements
            writeSidePanel(writer);

            // Main Content - Courses and Sections
            writeCourse(writer, "Java Programming", "Introduction", "This section covers the basics of Java programming.");
            writeCourse(writer, "Java Programming", "Advanced Topics", "Explore advanced Java concepts such as multithreading and design patterns.");
            writeCourse(writer, "Web Development with Spring Boot", "Building RESTful APIs", "Learn to create RESTful APIs using Spring Boot.");
            writeCourse(writer, "Web Development with Spring Boot", "Frontend Development", "Explore frontend development with Thymeleaf and HTML.");

            writer.write("</body>\n");
            writer.write("</html>");

            System.out.println("HTML file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeSidePanel(FileWriter writer) throws IOException {
        writer.write("    <div class=\"side-panel\">\n");
        writer.write("        <h2>Reminders</h2>\n");
        writer.write("        <ul>\n");
        writer.write("            <li>Assignment due on 2022-12-15</li>\n");
        writer.write("            <li>Quiz next week</li>\n");
        writer.write("        </ul>\n");

        writer.write("        <h2>Pending Assignments</h2>\n");
        writer.write("        <ul>\n");
        writer.write("            <li>Project proposal</li>\n");
        writer.write("            <li>Homework set 3</li>\n");
        writer.write("        </ul>\n");

        writer.write("        <h2>Announcements</h2>\n");
        writer.write("        <p>Welcome to the course! Please check the syllabus for important information.</p>\n");

        writer.write("    </div>\n");
    }

    private static void writeCourse(FileWriter writer, String courseTitle, String sectionTitle, String sectionContent) throws IOException {
        writer.write("    <div class=\"course\">\n");
        writer.write("        <h2 class=\"course-title\">" + courseTitle + "</h2>\n");
        writer.write("        <div class=\"section\">\n");
        writer.write("            <h3 class=\"section-title\">" + sectionTitle + "</h3>\n");
        writer.write("            <p class=\"section-content\">" + sectionContent + "</p>\n");
        writer.write("        </div>\n");
        writer.write("    </div>\n");
    }
}

