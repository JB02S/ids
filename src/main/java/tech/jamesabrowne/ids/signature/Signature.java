package tech.jamesabrowne.ids.signature;

public class Signature {

    private int id;
    private String pattern;
    private String protocol;
    private String srcIp;
    private String dstIp;

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

    public String getProtocol() {
        return this.protocol;
    }

}
