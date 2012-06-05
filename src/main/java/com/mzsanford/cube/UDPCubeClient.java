/**
 * 
 */
package com.mzsanford.cube;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Matt Sanford <matt@mzsanford.com>
 * 
 */
public class UDPCubeClient implements CubeClient {
    private String host;
    private int port;
    private DatagramSocket socket;

    public UDPCubeClient() throws UnknownHostException, SocketException {
        this("localhost", 1180);
    }

    public UDPCubeClient(String host, int port) throws UnknownHostException, SocketException {
        this.host = host;
        this.port = port;
        this.socket = new DatagramSocket(this.port,
                                         InetAddress.getByName(host));
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @see com.mzsanford.cube.CubeClient#send(com.mzsanford.cube.Event)
     */
    public void send(Event event) throws JsonGenerationException,
            JsonMappingException, IOException {
        byte[] packetData = event.toJSON().getBytes("UTF-8");
        DatagramPacket sendPacket = new DatagramPacket(packetData, packetData.length);
        socket.send(sendPacket);
    }

}
