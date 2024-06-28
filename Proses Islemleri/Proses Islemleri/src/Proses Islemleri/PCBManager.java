package _19010310021_odev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _19010310021_PCBManager {
	 private static int nextPid = 1000;
	    private Map<Integer, _19010310021_PCB> pcbs;
	    @SuppressWarnings("unused")
		private Map<Integer, _19010310021_MemoryBlock> memoryMap;

	    public _19010310021_PCBManager() {
	    	pcbs = new HashMap<>();
	        memoryMap = new HashMap<>();
	    }
	    public String getPCBNames() {
	        StringBuilder sb = new StringBuilder();
	        for (_19010310021_PCB pcb : pcbs.values()) {
	            sb.append(pcb.getName()).append(" ");
	        }
	        return sb.toString().trim();
	    }

    public int createPCB( int totalSizeKB, String name, int endTime, int startAddress, int endAddress) {
        int pid = nextPid++;
        _19010310021_PCB pcb = new _19010310021_PCB(pid, totalSizeKB, name, endTime, endAddress, startAddress);
        pcbs.put(pid, pcb);
        return pid;
    }

    public void deletePCB(int pid) {
        pcbs.remove(pid);
    }

    public void printRAMStatus(int second) {
        System.out.println(second + ". Saniyede RAM’in dolu olan kısımları:");
        System.out.println("0. Ve 1023999. Adresler arasında işletim sistemi bulunmaktadır.");

        int currentAddress = 1024000;
        for (_19010310021_PCB pcb : pcbs.values()) {
            int endAddress = currentAddress + pcb.getTotalSizeKB() * 1024 - 1;
            System.out.println(currentAddress + ". Ve " + endAddress + ". Adresler arasında " + pcb.getName() + " programı bulunmaktadır.");
            currentAddress = endAddress + 1;
        }
        System.out.print("PCB’si bulunan Prosesler: ");
        boolean first = true;
        for (_19010310021_PCB pcb : pcbs.values()) {
            if (!pcb.getName().equals("işletim sistemi")) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(pcb.getName());
                first = false;
            }
        }
        System.out.println();
        
    }




    public void printPCBInfo(int second, String processName) {
        _19010310021_PCB pcb = null;
        for (_19010310021_PCB p : pcbs.values()) {
            if (processName.equals(p.getName())) {
                pcb = p;
                break;
            }
        }

        if (pcb != null) {
            System.out.println(processName + " isimli prosesin " + second + ". Saniyedeki PCB bilgileri şu şekildedir:");
            System.out.println("Proses numarası: " + pcb.getPid());
            System.out.println("Prosesin yaratılma zamanı: " + pcb.getCreationTime() + ". saniye");
            System.out.println("Prosesin toplam büyüklüğü: " + pcb.getTotalSizeKB() + " KB");
        } else {
            System.out.println("Belirtilen isimde bir proses " + second + ". saniyede yaratılmamıştır.");
        }
    }

  

    	public static void main(String[] args) {
    	    _19010310021_PCBManager pcbManager = new _19010310021_PCBManager();
    	    Map<Integer, String> processes = new HashMap<>();

    	    try {
    	        BufferedReader reader = new BufferedReader(new FileReader("girdi.txt"));
    	        String line;
    	        while ((line = reader.readLine()) != null) {
    	            String[] parts = line.split(" ");
    	            String processName = parts[0];
    	            int startTime = Integer.parseInt(parts[1]);
    	            int dataSize = Integer.parseInt(parts[3]);
    	            int codeSize = Integer.parseInt(parts[4]);
    	            int stackSize = Integer.parseInt(parts[5]);
    	            int heapSize = Integer.parseInt(parts[6]);
    	            int endTime = startTime + dataSize + codeSize + stackSize + heapSize;

    	            processes.put(startTime, processName);
    	            pcbManager.createPCB(dataSize + codeSize + stackSize + heapSize, processName, endTime, startTime, nextPid);
    	        }
    	        reader.close();
    	        System.out.println("girdi.txt dosyası okundu.");
    	    } catch (IOException e) {
    	        System.out.println("Dosya okuma hatası: " + e.getMessage());
    	    }

    	    Scanner inputScanner = new Scanner(System.in);
    	    System.out.println("Lütfen RAM’in durumunu görmek istediğiniz saniyeyi giriniz:");
    	    int second = inputScanner.nextInt();

    	    pcbManager.printRAMStatus(second);

    	    inputScanner.nextLine(); 
    	    System.out.println(second + ". saniyedeki PCB’sini görüntülemek istediğiniz proses ismini giriniz:");
    	    String processName = inputScanner.nextLine();

    	    pcbManager.printPCBInfo(second, processName);

    	    inputScanner.close(); 
    	}}
