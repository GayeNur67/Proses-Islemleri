package _19010310021_odev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _19010310021_Process {
    private String name;
    private int startTime;
    private int endTime;
    private int dataSegmentSize;
    private int codeSegmentSize;
    private int stackSize;
    private int heapSize;
    private int baseAddress; 

    public _19010310021_Process(String name, int startTime, int endTime, int dataSegmentSize, int codeSegmentSize, int stackSize, int heapSize) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataSegmentSize = dataSegmentSize;
        this.codeSegmentSize = codeSegmentSize;
        this.stackSize = stackSize;
        this.heapSize = heapSize;
        this.baseAddress = -1; 
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getDataSegmentSize() {
        return dataSegmentSize;
    }

    public int getCodeSegmentSize() {
        return codeSegmentSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(int baseAddress) {
        this.baseAddress = baseAddress;
    }



    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("girdi.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int startTime = Integer.parseInt(parts[1]);
                int endTime = Integer.parseInt(parts[2]);
                int dataSegmentSize = Integer.parseInt(parts[3]);
                int codeSegmentSize = Integer.parseInt(parts[4]);
                int stackSize = Integer.parseInt(parts[5]);
                int heapSize = Integer.parseInt(parts[6]);

                _19010310021_Process process = new _19010310021_Process(name, startTime, endTime, dataSegmentSize, codeSegmentSize, stackSize, heapSize);
                System.out.println("Process name: " + process.getName());
                System.out.println("Start Time: " + process.getStartTime());
                System.out.println("End Time: " + process.getEndTime());
                System.out.println("Data Segment Size: " + process.getDataSegmentSize());
                System.out.println("Code Segment Size: " + process.getCodeSegmentSize());
                System.out.println("Stack Size: " + process.getStackSize());
                System.out.println("Heap Size: " + process.getHeapSize());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
