package tech.jamesabrowne.ids.sniffer;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

public class PacketSniffer {
    public void readPcap(String pcapPath) {
        try {
            // Open capture handle on pcap file
            PcapHandle handle = Pcaps.openOffline(pcapPath);
            Packet packet;

            // Read all packets in pcap
            while ((packet = handle.getNextPacket()) != null) {
                printPacketDetails(packet);
            }

            handle.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printPacketDetails(Packet packet) {
        System.out.println("Packet: " + packet);
    }
}
