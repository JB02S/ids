package tech.jamesabrowne.ids.client;

import org.apache.commons.cli.*;
import tech.jamesabrowne.ids.client.ClientService;

class ClientMain {
    public static void main(String[] args) {

        Options options = new Options();

        Option readOption = new Option("r", "read", true, "Path to the pcap file");
        readOption.setRequired(false);
        options.addOption(readOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            ClientService clientService = new ClientService();

            if (cmd.hasOption("r")) {
                String filePath = cmd.getOptionValue("r");
                clientService.readPcapOffline(filePath);
            } else {
                formatter.printHelp("PcapReader", options);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("PcapReader", options);
        }
    }
}