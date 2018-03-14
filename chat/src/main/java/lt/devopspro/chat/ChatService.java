package lt.devopspro.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatService {
	
	private Map<Integer, List<String>> messages = new HashMap<>();
	
	@PostMapping(path = "/post-mesage",  consumes = "application/json")
	public void post(@RequestBody RoomMessage message) {
		System.out.println("post: roomId = " + message.getRoomId() + ", message = " + message.getMessage());
		List<String> m = messages.get(message.getRoomId());
		if (m != null) {
			m.add(message.getMessage());
		} else {
			messages.put(message.getRoomId(), Stream.of(new String[] {message.getMessage()}).collect(Collectors.toList()));
		}
	}
	
	@GetMapping(path = "/get-mesages")
	public List<String> read(Integer roomId) {
		System.out.println("message list: " + messages.toString());
		return messages.get(roomId);
	}
}
