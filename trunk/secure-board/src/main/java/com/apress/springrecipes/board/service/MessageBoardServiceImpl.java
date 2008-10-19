package com.apress.springrecipes.board.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.apress.springrecipes.board.domain.Message;

public class MessageBoardServiceImpl implements MessageBoardService {

	protected Logger logger = Logger.getLogger(getClass());
	private Map<Long, Message> messages = new LinkedHashMap<Long, Message>();

	public List<Message> listMessages() {
		logger.info("returning messages " + messages);
		return new ArrayList<Message>(messages.values());
	}

	public synchronized void postMessage(Message message) {
		message.setId(System.currentTimeMillis());
		logger.info("posting message " + message);
		messages.put(message.getId(), message);

	}

	public synchronized void deleteMessage(Message message) {
		messages.remove(message.getId());

	}

	public Message findMessageById(Long messageId) {
		return messages.get(messageId);
	}
}
