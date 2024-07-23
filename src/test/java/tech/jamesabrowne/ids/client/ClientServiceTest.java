package tech.jamesabrowne.ids.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import tech.jamesabrowne.ids.sniffer.PacketSniffer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientService clientService;
    private PacketSniffer mockPacketSniffer;

    @BeforeEach
    void setUp() {
        clientService = new ClientService();
        mockPacketSniffer = mock(PacketSniffer.class);
    }
    
}