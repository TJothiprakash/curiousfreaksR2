package lld.messagebrokersystem;
import java.util.List;

public interface Consumer {
    void subscribe(String topic, String groupId);
    List<Message> poll(String topic, String groupId);
}

