package Control;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import DAO.LivroDao;
import Model.LivroM;

public class payMqttCallBack implements MqttCallback {
	public void connectionLost(Throwable throwable) {
	    System.out.println("Connection lost!");
	}
	
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		System.out.println(new String(mqttMessage.getPayload()));
		LivroDao livrodao = new LivroDao();
		LivroM livro = new LivroM();
		livro.setId(Integer.parseInt(new String(mqttMessage.getPayload())));
		livrodao.busca(livro);
	}
	
  	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {}

}
