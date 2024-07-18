package tech.jamesabrowne.ids.client;

import tech.jamesabrowne.ids.sniffer.PacketSniffer;

public class ClientService {
    public void readPcapOffline(String filepath) {
        PacketSniffer packetSniffer = new PacketSniffer();
        packetSniffer.readPcap(filepath);
    }
}
