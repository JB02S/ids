package tech.jamesabrowne.ids.client;

import org.apache.commons.cli.*;
import tech.jamesabrowne.ids.client.ClientService;

class ClientMain {
    public static void main(String[] args) {

        System.setProperty("jna.library.path", "C:\\Windows\\System32\\Npcap");
        System.setProperty("org.pcap4j.core.pcapLibName", "C:\\Windows\\System32\\Npcap\\wpcap.dll");
        System.setProperty("org.pcap4j.core.packetLibName", "C:\\Windows\\System32\\Npcap\\Packet.dll");

        Options options = new Options();

        /*
        * Option "-r" to read a pcap file offline
        * */
        Option readOption = new Option("r", "read", true, "Path to the pcap file");
        readOption.setRequired(false);
        options.addOption(readOption);

        /*
        * Option "-i" for live pcap capture on a specified interface
        * */
        Option listenOption = new Option("i", "listen", true, "Network interface to listen on");
        listenOption.setRequired(false);
        options.addOption(listenOption);

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

            if (cmd.hasOption("r")) {
                String filePath = cmd.getOptionValue("r");
                clientService.readPcapOffline(filePath);
            } else if (cmd.hasOption("i")){
                String networkInterfaceName = cmd.getOptionValue("i");
                clientService.listen(networkInterfaceName);
            } else if (cmd.hasOption("l")) {
                clientService.listNetworkInterfaces();
            } else {
                formatter.printHelp("PcapReader", options);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("PcapReader", options);
        }
    }
}