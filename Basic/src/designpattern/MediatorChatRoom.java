package designpattern;

import java.util.ArrayList;
import java.util.List;

class PersonMD {
    private final String name;
    private ChatRoomMD chatRoom;
    private final List<String> chatLog = new ArrayList<>();

    public PersonMD(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ChatRoomMD getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoomMD chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void receive(String sender, String message) {
        String s = sender + " : '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void say(String message) {
        if (chatRoom != null) chatRoom.broadcast(name, message);
    }

    public void privateMessage(String receiver, String message) {
        if (chatRoom != null) chatRoom.message(name, receiver, message);
    }
}
class ChatRoomMD {
    private final List<PersonMD> people;

    public ChatRoomMD() {
        people = new ArrayList<>();
    }

    public void join(PersonMD person) {
        people.add(person);
        person.setChatRoom(this);
    }

    public void leave(PersonMD person) {
        people.remove(person);
        person.setChatRoom(null);
    }

    public void broadcast(String from, String message) {
        for (PersonMD p : people) {
            if (!p.getName().equals(from)) {
                p.receive(from, message);
            }
        }
    }

    public void message(String from, String to, String message) {
        people.stream()
                .filter(p -> p.getName().equals(to))
                .findFirst()
                .ifPresent(p -> p.receive(from, message));
    }
}
public class MediatorChatRoom {
    public static void main(String[] args) {
        ChatRoomMD room = new ChatRoomMD();

        PersonMD john = new PersonMD("John");
        PersonMD jane = new PersonMD("Jane");

        room.join(john); // no message here
        room.join(jane);

        john.say("hi room");
        jane.say("oh, hey john");

        PersonMD simon = new PersonMD("Simon");
        room.join(simon);
        simon.say("hi everyone!");

        jane.privateMessage("Simon", "glad you could join us!");
    }
}
