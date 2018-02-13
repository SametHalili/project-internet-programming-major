package forumBlabla.view;

import forumBlabla.db.ForumPostDb;
import forumBlabla.db.ForumPostDbInMemory;
import forumBlabla.domain.*;

import static javax.swing.JOptionPane.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ServiceTest
{
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String [] args)
    {
        ForumPostDb db = new ForumPostDbInMemory();
        boolean running = true;
        while(running)
        {
            System.out.println("-----------------------------");
            System.out.println("Topic: Test");
            Map<Integer, ForumPost> topic = db.getAll();
            for (Map.Entry<Integer, ForumPost> entry: topic.entrySet())
            {
                ForumPost msg = entry.getValue();
                System.out.println("Username: " + msg.getUsername() + "  " +
                                   "Time: " + msg.getMsgTime().format(FORMAT) + "  " +
                                   "PostID: " + msg.getPostId() +
                                   "\n");
                System.out.println("-----------------------------\n");
                System.out.println(msg.getMsg() + "\n\n");
            }

            String input = showInputDialog("New Message: 1" + "\n" +
                                                       "Leave Topic: 2" + "\n" +
                                                       "Aantal posts : " + db.getAll().size() + "\n" +
                                                       "Original Poster : " + topic.entrySet().iterator().next().getValue().getUsername() +
                                                       "\n\n");
            if(input == null)
            {
                running = false;
                System.out.println("Terminate!");
            }
            else
            {
                switch (input) {
                    case "1":
                        String user = showInputDialog("What's your username?");
                        String newMsg = showInputDialog("Your message");
                        db.add(new ForumPost(newMsg, user));
                        break;
                    case "2":
                        System.out.println("You left the thread");
                        running = false;
                        break;
                    default:
                        System.out.println("Error with your message!");
                        break;
                }
            }
        }
    }
}
