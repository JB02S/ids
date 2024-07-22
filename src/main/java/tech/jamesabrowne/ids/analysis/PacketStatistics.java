package tech.jamesabrowne.ids.analysis;

import org.pcap4j.packet.Packet;

import java.util.HashMap;
import java.util.Map;

public class PacketStatistics {

    public Map<String, Integer> layerCounts;
    private int totalPackets;

    public PacketStatistics() {
        this.layerCounts = new HashMap<>();
        this.totalPackets = 0;
    }

    public void analyzePacket(Packet packet) {

        totalPackets++;
        analyzeLayer(packet);
    }

    private void analyzeLayer(Packet packet) {
        Class<? extends Packet> layerClass = packet.getClass();
        layerCounts.put(layerClass.getSimpleName(), layerCounts.getOrDefault(layerClass, 0) + 1);

        Packet payload = packet.getPayload();
        if (payload != null) {
            analyzeLayer(payload);
        }
    }


}
