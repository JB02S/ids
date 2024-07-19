package tech.jamesabrowne.ids.sniffer;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.util.List;

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

    public void listenOnInterface(String networkInterfaceName) {
        try {
            PcapNetworkInterface device = Pcaps.getDevByName(networkInterfaceName);
            if (device == null) {
                System.out.println("No device found.");
            }
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }

    }

    public void listNetworkInterfaces() {

        try {
            List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
            System.out.println("name : device description");
            for (PcapNetworkInterface dev : allDevs) {
                System.out.println(dev.getName() + " : " + dev.getDescription());
            }
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }

    }

    private static void printPacketDetails(Packet packet) {
        System.out.println("Packet: " + packet);
    }
}
