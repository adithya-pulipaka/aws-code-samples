package org.pulipaka.sqs.standard;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

/**
 * A Simple SQS Client to receive messages from a STANDARD queue using AWS SDK.
 * The message request uses short polling by default and if the message count is very less in the queue,
 * not all of the messages may be received. Refer {@link MessageReceiverStdLongPolling} to use long polling.
 * @author Adithya
 *
 */
public class MessageReceiverStd {

	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final ReceiveMessageRequest request = new ReceiveMessageRequest()
						.withQueueUrl(System.getProperty("SQS_QUEUE_URL"));
		
		ReceiveMessageResult result = amazonSQS.receiveMessage(request);
		List<Message> messages = result.getMessages();
		System.out.println("Message count: " + messages.size());
		messages.forEach(message -> printMessage(message));
		
	}

	private static void printMessage(Message message) {
		System.out.println("*********START******************");
		System.out.println("Message ID: " + message.getMessageId());
		System.out.println("Message Body: " + message.getBody());
		System.out.println("MD5 of Message Body: " + message.getMD5OfBody());
		System.out.println("MD5 of Message Attributes: " + message.getMD5OfMessageAttributes());
		System.out.println("Receipt Handle: " + message.getReceiptHandle());
		message.getAttributes()
			.forEach((key,value) 
					-> System.out.println("Key: " + key + ", Value: " + value));
		System.out.println("**********END*******************");
		
	}

}
