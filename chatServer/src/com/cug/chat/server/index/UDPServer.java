package com.cug.chat.server.index;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	private static final int PORT = 5000;
	private DatagramSocket dataSocket;
	private DatagramPacket dataPacket;
	private byte receiveByte[];
	private String receiveStr;
	
	public UDPServer(){
		Init();
	}
	public void Init(){
		try {
			dataSocket = new DatagramSocket(PORT);
			receiveByte = new byte[1024];
			dataPacket = new DatagramPacket(receiveByte, receiveByte.length);
			receiveStr = "";
			int i = 0;
			while(i==0){	//�����ݣ���ѭ��
				dataSocket.receive(dataPacket);
				i = dataPacket.getLength();//��������
					receiveStr = new String(receiveByte,0,dataPacket.getLength());
					System.out.println(receiveStr);
					receiveStr = "����˽��ճɹ�������";
					receiveByte = receiveStr.getBytes();
					dataPacket = new DatagramPacket(receiveByte, receiveByte.length,
							InetAddress.getByName("localhost"),PORT+1);
					dataSocket.send(dataPacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("�ر�UDP����");
			dataSocket.close();
		}
	}
	public static void main(String[] args) {
		new UDPServer();
	}
}
