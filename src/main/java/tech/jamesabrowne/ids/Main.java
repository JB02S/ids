package tech.jamesabrowne.ids;

import org.apache.commons.cli.*;
import org.pcap4j.core.PcapNetworkInterface;

import java.util.List;

class Main {

    public static void main(String[] args) {

        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("jna.library.path", "C:\\Windows\\System32\\Npcap");
            System.setProperty("org.pcap4j.core.pcapLibName", "C:\\Windows\\System32\\Npcap\\wpcap.dll");
            System.setProperty("org.pcap4j.core.packetLibName", "C:\\Windows\\System32\\Npcap\\Packet.dll");
        }

        Options options = new Options();

        options.addOption("i", "listen", true, "Network interface to listen on");
        options.addOption("s", "signature-based analysis", false, "List all available network interfaces");
        options.addOption("l", "list", false, "List all available network interfaces");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        PacketSniffer packetSniffer = new PacketSniffer();


        try {

            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")){
                String networkInterfaceName = cmd.getOptionValue("i");
                if (cmd.hasOption("-s")) {
                    packetSniffer.monitor(networkInterfaceName, true);
                } else {
                    packetSniffer.monitor(networkInterfaceName, false);
                }

            } else if (cmd.hasOption("l")) {
                List<PcapNetworkInterface> allDevs = packetSniffer.listNetworkInterfaces();
                for (PcapNetworkInterface dev : allDevs) {
                    System.out.println(dev);
                }

            } else {
                formatter.printHelp("PcapReader", options);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("PcapReader", options);
        }
    }
}
