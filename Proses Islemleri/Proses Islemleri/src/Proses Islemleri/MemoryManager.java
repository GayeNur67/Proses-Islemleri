package _19010310021_odev;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class _19010310021_MemoryManager {
    private Map<Integer, _19010310021_MemoryBlock> memoryMap;

    public _19010310021_MemoryManager(int totalMemorySize) {
        memoryMap = new HashMap<>();
        memoryMap.put(0, new _19010310021_MemoryBlock(0, totalMemorySize)); 
    }
    

    public boolean allocate(int start, int size, String programName, String name) {
        for (Map.Entry<Integer, _19010310021_MemoryBlock> entry : memoryMap.entrySet()) {
            _19010310021_MemoryBlock block = entry.getValue();
            int blockStart = block.getStart();
            int blockSize = block.getSize();
            if (!block.isOccupied() && blockSize >= size && start >= blockStart && start + size <= blockStart + blockSize) {
                
                block.setOccupied(true);
                block.setProgramName(programName); 
                block.setName(name); 

                int remainingSize = blockSize - size;
                memoryMap.remove(blockStart);
                if (remainingSize > 0) {
                    
                    memoryMap.put(blockStart + size, new _19010310021_MemoryBlock(blockStart + size, remainingSize));
                }
                return true;
            }
        }
        return false;
    }

    public void deallocate(int start, int size) {
        for (Map.Entry<Integer, _19010310021_MemoryBlock> entry : memoryMap.entrySet()) {
            _19010310021_MemoryBlock block = entry.getValue();
            int blockStart = block.getStart();
            int blockSize = block.getSize();
            if (blockStart == start + size || blockStart + blockSize == start) {
                
                block.setOccupied(false);
                block.setProgramName(null); 
                block.setName(null); 
                memoryMap.remove(blockStart);
                memoryMap.put(Math.min(blockStart, start), new _19010310021_MemoryBlock(Math.min(blockStart, start), blockSize + size));
                return;
            }
        }
        memoryMap.put(start, new _19010310021_MemoryBlock(start, size)); 
    }


    public void printMemoryMap() {
        System.out.println("Memory Map:");
        
        TreeMap<Integer, _19010310021_MemoryBlock> sortedMap = new TreeMap<>(memoryMap);

        System.out.println("0. Ve 1023999. Adresler arasında işletim sistemi bulunmaktadır.");

        for (_19010310021_MemoryBlock block : sortedMap.values()) {
            int blockStart = block.getStart();
            int blockSize = block.getSize();
            int blockEnd = blockStart + blockSize - 1;
            if (block.isOccupied()) {
                System.out.println(blockStart + ". Ve " + blockEnd + ". Adresler arasında " + block.getName() + " programı bulunmaktadır.");
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        
        _19010310021_MemoryManager memoryManager = new _19010310021_MemoryManager(2000000); 

        
        memoryManager.allocate(0, 1000, "A.exe", "Program A"); 

       
        memoryManager.printMemoryMap();

        memoryManager.deallocate(0, 1000); 

        
        memoryManager.printMemoryMap();
    }

}
