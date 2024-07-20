package tech.jamesabrowne.ids.sniffer;

import org.pcap4j.core.*;
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

    public void listenOnInterface(String networkInterfaceName, Boolean monitorSignature) {
        try {
            PcapNetworkInterface device = Pcaps.getDevByName(networkInterfaceName);
            if (device == null) {
                System.err.println("No device found with name: " + networkInterfaceName);
                return;
            }

            int snapLen = 65536;
            int timeout = 10;
            PcapHandle handle = device.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout);

            handle.loop(-1, (PacketListener) packet -> {
                if (monitorSignature) {
                    System.out.println("using signature based analysis to monitor for malicious traffic");
                }
                System.out.println(handle.getTimestamp());
                System.out.println(packet);
            });

            handle.close();
        } catch (PcapNativeException | InterruptedException | NotOpenException e) {
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
