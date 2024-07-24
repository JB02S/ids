package tech.jamesabrowne.ids.sniffer;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import tech.jamesabrowne.ids.signature.Signature;
import tech.jamesabrowne.ids.signature.SignatureLoader;
import tech.jamesabrowne.ids.signature.SignatureMatcher;

import java.util.List;

public class PacketSniffer {

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
                }
            });

            handle.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<PcapNetworkInterface> listNetworkInterfaces() {
        
        List<PcapNetworkInterface> allDevs = null;
        try {
            allDevs = Pcaps.findAllDevs();
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }

        return allDevs;
    }

    private static void printPacketDetails(Packet packet) {
        System.out.println("Packet: " + packet);
    }
}
