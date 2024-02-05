import java.util.Scanner;
public class SimpleChatbot
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Simple Chatbot!");
        System.out.println("You can start chatting. Type 'quit' to exit.");

        while (true)
        {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("quit")) 
            {
                System.out.println("Bot: Goodbye! Have a great day!");
                break;
            }

            String response = getResponse(userInput);
            System.out.println("Bot: " + response);
        }
        scanner.close();
    }

    public static String getResponse(String userInput) 
    {
        if (userInput.contains("hello") || userInput.contains("hi")) 
        {
            return "Hello! How can I assist you today?";
        } else if (userInput.contains("how are you")) 
        {
            return "I'm just a bot, but thanks for asking!";
        } else if (userInput.contains("goodbye") || userInput.contains("bye")) 
        {
            return "Goodbye! Have a great day!";
        } else if (userInput.contains("thank you") || userInput.contains("thanks")) 
        {
            return "You're welcome!";
        } else 
        {
            return "I'm sorry, I don't understand. Can you please rephrase?";
        }
    }
}
