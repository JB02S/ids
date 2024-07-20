package tech.jamesabrowne.ids.client;

import tech.jamesabrowne.ids.sniffer.PacketSniffer;

public class ClientService {

    private final PacketSniffer packetSniffer = new PacketSniffer();
    public void readPcapOffline(String filepath) {
        packetSniffer.readPcap(filepath);
    }

    public void outputLive(String networkInterfaceName) {
        packetSniffer.listenOnInterface(networkInterfaceName, false);
    }

    public void listNetworkInterfaces() {
        packetSniffer.listNetworkInterfaces();
    }

    public void monitorSignatureBased(String networkInterfaceName) {
        packetSniffer.listenOnInterface(networkInterfaceName, true);
    }
}
