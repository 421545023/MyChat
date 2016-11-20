package com.cug.chat.client.index;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
	private static final int PORT = 5000;
    private DatagramSocket dataSocket;
    private DatagramPacket dataPacket;
    private byte sendDataByte[];
    private String sendStr;

    public UDPClient() {
        Init();
    }
    public void Init() {
		try {
			dataSocket = new DatagramSocket(PORT+1);
			sendDataByte = new byte[1024];
			sendStr = "UDP方式发送数据";
			sendDataByte = sendStr.getBytes();
			dataPacket = new DatagramPacket(sendDataByte, sendDataByte.length,
					InetAddress.getByName("localhost"),PORT);
			dataSocket.send(dataPacket);
			sendDataByte = new byte[1024];
			dataPacket = new DatagramPacket(sendDataByte, sendDataByte.length);
			dataSocket.receive(dataPacket);
			sendStr = new String(sendDataByte,0,dataPacket.getLength());
			System.out.println(sendStr);
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}finally{
			System.out.println("关闭客户端连接");
			dataSocket.close();
		}
	}
    public static void main(String[] args) {
		new UDPClient();
	}
}
