package _19010310021_odev;
public class _19010310021_PCB {
    private int pid;
    private int creationTime;
    private int totalSizeKB;
    private String name;
    private int endTime;
    private int startAddress;
    private int endAddress;

    public _19010310021_PCB(int pid, int totalSizeKB, String name, int endTime, int startAddress, int endAddress) {
        this.pid = pid;
        this.creationTime = endTime - totalSizeKB; 
        this.totalSizeKB = totalSizeKB;
        this.name = name;
        this.endTime = endTime;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public String getName() {
        return name;
    }

    public int getPid() {
        return pid;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getTotalSizeKB() {
        return totalSizeKB;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }
}
