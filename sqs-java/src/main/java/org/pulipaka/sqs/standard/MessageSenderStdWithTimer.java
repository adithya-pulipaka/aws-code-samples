package org.pulipaka.sqs.standard;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * A Simple SQS Client to send messages to a STANDARD queue using AWS SDK along with a delayed timer.
 * The message with positive delay will be accepted by SQS but only shows up for consuming after the specified time.
 * @author Adithya
 *
 */
public class MessageSenderStdWithTimer {
	
	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final SendMessageRequest request = new SendMessageRequest()
					.withQueueUrl(System.getProperty("SQS_QUEUE_URL"))
					.withMessageBody("Hello Amazon SQS from StandardSQSMessageSenderWithTimer!")
					.withDelaySeconds(60);
		
		SendMessageResult result = amazonSQS.sendMessage(request);
		System.out.println("Message ID: " + result.getMessageId());
		System.out.println("MD5 of Message Body: " + result.getMD5OfMessageBody());
	}

}
