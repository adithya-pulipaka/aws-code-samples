package org.pulipaka.sqs.standard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * A Simple SQS Client to send messages to a STANDARD queue using AWS SDK along with Attributes.
 * The default Data types <b>String, Number and Binary</b> have been used for demonstration.
 * @author Adithya
 *
 */
public class MessageSenderStdWithAttributes {
	
	public static void main(String[] args) {
		
		final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();
		
		final Map<String,MessageAttributeValue> messageAttributes = new HashMap<String, MessageAttributeValue>();
		messageAttributes.put("StringAttr",
				new MessageAttributeValue()
				.withDataType("String")
				.withStringValue("Name Attribute from Sender"));
		messageAttributes.put("NumberAttr", 
				new MessageAttributeValue()
				.withDataType("Number")
				.withStringValue("1542.321"));
		messageAttributes.put("BinaryAttr", 
				new MessageAttributeValue()
				.withDataType("Binary")
				.withBinaryValue(ByteBuffer.wrap("Hello from sender".getBytes())));
		
		final SendMessageRequest request = new SendMessageRequest()
					.withQueueUrl(System.getProperty("SQS_QUEUE_URL"))
					.withMessageBody("Hello Amazon SQS from MessageSenderStdWithAttributes!")
					.withMessageAttributes(messageAttributes);
		
		SendMessageResult result = amazonSQS.sendMessage(request);
		System.out.println("Message ID: " + result.getMessageId());
		System.out.println("MD5 of Message Body: " + result.getMD5OfMessageBody());
		System.out.println("MD5 of Message Attributes: " + result.getMD5OfMessageAttributes());
	}

}
