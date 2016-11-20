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
			while(i==0){	//无数据，则循环
				dataSocket.receive(dataPacket);
				i = dataPacket.getLength();//接收数据
					receiveStr = new String(receiveByte,0,dataPacket.getLength());
					System.out.println(receiveStr);
					receiveStr = "服务端接收成功，返回";
					receiveByte = receiveStr.getBytes();
					dataPacket = new DatagramPacket(receiveByte, receiveByte.length,
							InetAddress.getByName("localhost"),PORT+1);
					dataSocket.send(dataPacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("关闭UDP连接");
			dataSocket.close();
		}
	}
	public static void main(String[] args) {
		new UDPServer();
	}
}
