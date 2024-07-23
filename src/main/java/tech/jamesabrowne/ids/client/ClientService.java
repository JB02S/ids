package tech.jamesabrowne.ids.client;

import org.pcap4j.core.PcapNetworkInterface;
import tech.jamesabrowne.ids.sniffer.PacketSniffer;

import java.util.List;

public class ClientService {

    private final PacketSniffer packetSniffer = new PacketSniffer();

    public void outputLive(String networkInterfaceName) {
        packetSniffer.listenOnInterface(networkInterfaceName, false);
    }

    public List<PcapNetworkInterface> getNetworkInterfaces() {
        return packetSniffer.listNetworkInterfaces();
    }

    public void monitorSignatureBased(String networkInterfaceName) {
        packetSniffer.listenOnInterface(networkInterfaceName, true);
    }
}
