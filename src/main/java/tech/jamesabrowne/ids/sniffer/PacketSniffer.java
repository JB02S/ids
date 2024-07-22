package tech.jamesabrowne.ids.sniffer;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import tech.jamesabrowne.ids.analysis.PacketStatistics;
import tech.jamesabrowne.ids.signature.Signature;
import tech.jamesabrowne.ids.signature.SignatureLoader;
import tech.jamesabrowne.ids.signature.SignatureMatcher;

import java.util.List;

public class PacketSniffer {
    public void readPcap(String pcapPath) {
        try {
            PcapHandle handle = Pcaps.openOffline(pcapPath);
            Packet packet;

            PacketStatistics packetStatistics = new PacketStatistics();

            while ((packet = handle.getNextPacket()) != null) {
                packetStatistics.analyzePacket(packet);
            }

            System.out.println(packetStatistics.layerCounts);

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
            SignatureLoader signatureLoader = new SignatureLoader();

            List<Signature> signatures = signatureLoader.loadSignatures();
            SignatureMatcher signatureMatcher = new SignatureMatcher(signatures);

            System.out.println("using signature based analysis to monitor for malicious traffic");

            handle.loop(-1, (PacketListener) packet -> {
                if (monitorSignature) {
                    signatureMatcher.match(packet);
                } else {
                    System.out.println(handle.getTimestamp());
                    System.out.println(packet);
                }

            });

            handle.close();
        } catch (Exception e) {
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
