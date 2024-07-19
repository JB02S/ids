package tech.jamesabrowne.ids.client;

import tech.jamesabrowne.ids.sniffer.PacketSniffer;

public class ClientService {

    private final PacketSniffer packetSniffer = new PacketSniffer();
    public void readPcapOffline(String filepath) {
        packetSniffer.readPcap(filepath);
    }

    public void listen(String networkInterfaceName) {
        packetSniffer.listenOnInterface(networkInterfaceName);
    }
}
