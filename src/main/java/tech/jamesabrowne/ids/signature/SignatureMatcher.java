package tech.jamesabrowne.ids.signature;

import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

import java.util.List;

public class SignatureMatcher {
    private List<Signature> signatures;

    public SignatureMatcher(List<Signature> signatures) {
        this.signatures = signatures;
    }

    public boolean match(Packet packet) {
        for (Signature sig : signatures) {
            if (matches(packet, sig)) {
                System.out.println("Match found for signature ID: " + sig.getId());
                return true;
            }
        }
        return false;
    }

    private boolean matches(Packet packet, Signature sig) {

        IpV4Packet ipPacket = packet.get(IpV4Packet.class);
        System.out.println("**************************\n************************\n**************************\n************************\n");
        if (ipPacket == null) {
            return false;
        }
        System.out.println("**************************\n************************\n**************************\n************************\n");
        String packetSrcIp = ipPacket.getHeader().getSrcAddr().getHostAddress();
        String packetDstIp = ipPacket.getHeader().getDstAddr().getHostAddress();
        String packetProtocol = ipPacket.getHeader().getProtocol().name();
        String packetData = new String(ipPacket.getPayload().getRawData());
//
//        if (packetProtocol.equalsIgnoreCase(sig.getProtocol()) &&
//                packetDstIp.equals(sig.getDstIp()) &&
//                packetSrcIp.equals(sig.getSrcIp())) {
//            return true;
//        }

        System.out.println("**************************\n************************\n**************************\n************************\n");
        System.out.println(packetData);
        System.out.println(packetProtocol);
        System.out.println(packetDstIp);
        System.out.println(packetSrcIp);
        System.out.println("**************************\n************************\n**************************\n************************\n");
        return false;
    }
}
