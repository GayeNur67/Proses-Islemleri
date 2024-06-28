package _19010310021_odev;
public class _19010310021_MemoryBlock {
    private int start;
    private int size;
    private boolean occupied;
    private String programName;
    private String name;

    public _19010310021_MemoryBlock(int start, int size) {
        this.start = start;
        this.size = size;
        this.occupied = false;
        this.programName = null;
        this.name=null;
    }
    

    public int getStart() {
        return start;
    }

    public int getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
