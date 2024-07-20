package tech.jamesabrowne.ids.signature;

public class Signature {

    public Signature() {

    }
    private int id;
    private String pattern;
    private String protocol;
    private String srcIp;
    private String dstIp;
    private int srcPort;
    private int dstPort;

    public int getId() {
        return id;
    }

    public String getPattern() {
        return pattern;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public String getDstIp() {
        return dstIp;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public int getDstPort() {
        return dstPort;
    }

    public String getProtocol() {
        return this.protocol;
    }

}
