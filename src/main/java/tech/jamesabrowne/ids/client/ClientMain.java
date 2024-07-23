package tech.jamesabrowne.ids.client;

import org.apache.commons.cli.*;
import org.pcap4j.core.PcapNetworkInterface;
import tech.jamesabrowne.ids.client.ClientService;

import java.util.List;

class ClientMain {
    public static void main(String[] args) {

        System.setProperty("jna.library.path", "C:\\Windows\\System32\\Npcap");
        System.setProperty("org.pcap4j.core.pcapLibName", "C:\\Windows\\System32\\Npcap\\wpcap.dll");
        System.setProperty("org.pcap4j.core.packetLibName", "C:\\Windows\\System32\\Npcap\\Packet.dll");

        Options options = new Options();


        /*
        * Option "-i" specify interface for traffic monitoring
        * */
        Option listenOption = new Option("i", "listen", true, "Network interface to listen on");
        listenOption.setRequired(false);
        options.addOption(listenOption);

        /*
        * Option "-s" for monitoring packets on an interface using signature-based analysis
        * */
        Option monitorSignature = new Option("s", "signature-based analysis", false, "List all available network interfaces");
        monitorSignature.setRequired(false);
        options.addOption(monitorSignature);

        /*
        * Option "-l" to list network interfaces
        * */
        Option listInterfacesOption = new Option("l", "list", false, "List all available network interfaces");
        listInterfacesOption.setRequired(false);
        options.addOption(listInterfacesOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            ClientService clientService = new ClientService();
            if (cmd.hasOption("i")){
                String networkInterfaceName = cmd.getOptionValue("i");
                if (cmd.hasOption("-s")) {
                    clientService.monitorSignatureBased(networkInterfaceName);
                } else {
                    clientService.outputLive(networkInterfaceName);
                }

            } else if (cmd.hasOption("l")) {
                List<PcapNetworkInterface> allDevs = clientService.getNetworkInterfaces();
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