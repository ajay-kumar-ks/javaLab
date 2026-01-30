import java.util.Scanner;

public class Cpu {
    int price;
    Scanner sc = new Scanner(System.in);
    public Cpu(){
        System.out.println("enter Cpu price ");
        this.price = sc.nextInt();
    }
    public class Processor {
      int numOfCore;
      String manufacturer;
      public Processor(){
        System.out.println("enter number of processor cores ");
        this.numOfCore = sc.nextInt();
        sc.nextLine();
        System.out.println("enter processor manufacturer ");
        this.manufacturer = sc.nextLine();
      }
      
      public void display(){
        System.out.println("Cpu price: "+price);
        System.out.println("\n number of processor cores: "+numOfCore);
        System.out.println("\n processor manufacturer: "+manufacturer);
      }
    }
    static class Ram{
        int memory;
        String manufacturer;
        public Ram(){
            Scanner sc = new Scanner(System.in);
            System.out.println("enter ram memory");
            this.memory = sc.nextInt();
            sc.nextLine();
            System.out.println("enter ram manufacturer ");
            this.manufacturer = sc.nextLine();
        }
        public void display(){
            System.out.println("\n ram memory: "+memory+" GB");
            System.out.println("\n ram manufacturer: "+manufacturer);
        }
    }

    public static void main(String[] args) {
        Cpu cpu = new Cpu();
        Cpu.Processor process = cpu.new Processor();
        Cpu.Ram ram = new Cpu.Ram();
        process.display();
        ram.display();
    }


}
