import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class Bus{

  //handle all bus information

  private String busId;
  private boolean[] sits ={true,true,true,true,true,true,true,true}; 
  //false = booked
  //true = not booked yet
  
  private String route;

  //methods

  public Bus(String id,String route){
    this.busId = id;
    this.route = route;
  }



  public String getId(){
    return this.busId;
  }
  
  public String getRoute(){
      return this.route;
  }


  public void availableSit(){
    int i = 1; int count = 1;
    for(boolean sit : this.sits){
      if(sit){
         System.out.println("Sit No: "+i+"\n");
         count++;
      }
      i++;
    }
    
    if(count == 1){
        System.out.println("Sits are not available");
    }
  }
  
 
  public int bookAsit(int sitno){
    if(this.sits[sitno-1]){
        this.sits[sitno-1] = false;
             return sitno;
    }
    else
      return 0;
      
}



  public void resetBus(){
    int i = 0;
     for(boolean sit : this.sits){
       this.sits[i] = true;
       i++;
     }
    }


//end og class
}


class Ticket{
  //id, name,issued,date,busno,issuer,userphone,date,busno,issuerid,
  //sitno,

  private String Id,name,date,route,busNo,busType,issuerId;
  private int sitNo;

  public Ticket(String id,String name,String date,String busNo,String route,int sitno,String issuerID){
    this.Id = id;
    this.name = name;
    this.date = date;
    this.busNo = busNo;
    this.route = route;
    this.sitNo = sitno;
    this.issuerId = issuerID;
    
  }

  public String getId(){
      return this.Id;
  }
  public String getTicket(){  
    String ticket = "ID:"+this.Id+" Name:"+this.name+"  Date:"+this.date+" Route:"+this.route+"  BusNo:"+this.busNo+" SitNo:"+this.sitNo+" IssuerId:"+this.issuerId;

    return ticket; 
  }


//end of class
}





class Admin{

  //store and handle all admin related data
  //data field id,name,working
  //methods setWorking,setNotWorking,Admin

  private String Id;
  private String name;
  private boolean isWorking;


  public Admin(String id,String name){
    this.Id = id;
    this.name = name;
    this.isWorking = false;
  }

  public String getName(){
    return this.name;
  }

  public String getId(){
    return this.Id;
  }

  public void setWorking(){
    this.isWorking = true;
  }

   public void setNotWorking(){
    this.isWorking = false;
  }

  public boolean isWorking(){
    return this.isWorking;
  }


//end of class
}



class Booking{

  List<Bus> listOfBuses = new ArrayList<>();
  List<Ticket> listOfTickets = new ArrayList<>();
  List<Admin> listOfIssuers = new ArrayList<>();


  public void setBus(Bus bus){
    listOfBuses.add(bus);
  }

  public void setTicket(Ticket ticket){
    listOfTickets.add(ticket);
  }

  public void setIssuer(Admin issu){
    listOfIssuers.add(issu);
  }

  public List<Bus> getBusList(){
    return this.listOfBuses;
  }

  public List<Ticket> getTicketList(){
    return this.listOfTickets;
  }

  public List<Admin> getIssuerList(){
    return this.listOfIssuers;
  }

  public void BusAvailableSit(String id){
      int counter = 1;
      for(Bus bus:this.listOfBuses){
          if(bus.getId() == id){
              counter++;
              bus.availableSit();
          }
      }
      
      if(counter == 1){
          System.out.println("No Bus Found");
      }

  }
  
  
  
  public String BookATicket(int sitno,String busid,Ticket tic){
    int count = 0;
    for(Bus bus : this.listOfBuses){
        if(bus.getId() == busid){
            count++;
            bus.bookAsit(sitno);
            this.setTicket(tic); //adding to the list
            return this.getBusTicket(tic.getId());
        }
    }
    
  if(count > 0){
        return "Something went wrong please enter valid info";
    }

return "";
}

public String getBusTicket(String id){
    for(Ticket tic : this.listOfTickets){
        if(tic.getId() == id){
            return tic.getTicket();
        }
    }
    
  return "No Recod found in Tickets";
    

}


public void SearchTicket(String id){
  int count = 0;
  for(Ticket t: this.listOfTickets){
    if(t.getId() == id){
      count++;
      System.out.println(t.getTicket());
    }
  }
  if(count == 0 ){
    System.out.println("No Record Found");
  }
}




//end class
  
}



public class Bus_Ticket_Booking_System{

public static void  main(String[] args){

//initializing the booking class
Booking book = new Booking(); 


//including bus to the system
Bus bus1 = new Bus("a2020","dhaka-sylet");
Bus bus2 = new Bus("b2021","dhaka-sylet");
book.setBus(bus1);
book.setBus(bus2);
  
//including admin to the system
Admin issuer1 = new Admin("1111","Jalal");
issuer1.setWorking();//active the a issuer
book.setIssuer(issuer1);


//serching available bus sit  
//book.BusAvailableSit("a2020");


//String id,String name,String date,String busNo,String route,int sitno,String issuerID
  
//booking process 
  String id = "a202023";
  String name = "Julkar";
  String date = "04-06-23";
  String busNo = "a2020";
  String route = "dhaka-sylet";
  int sitno = 4;
  String issuer = "1111";
  Ticket tic1 = new Ticket(id,name,date,busNo,route,sitno,issuer); //initialize the ticket

String getTic1 = book.BookATicket(4, busNo, tic1);//getting ticket

Ticket tic2 = new Ticket("b202223","Hasan","04-06-23",busNo,route,5,issuer);

String getTic2 = book.BookATicket(5, busNo, tic2);//getting ticket

System.out.println("Sit availabe in BusNo a2020");
book.BusAvailableSit("a2020");

System.out.println("Showing the List of Tickets");
  
List<Ticket> tickets = book.getTicketList();

for(Ticket t : tickets){
  System.out.println(t.getTicket());
}



//File handling 
//printTicket(getTic1); //printing ticket in Ticket.txt

//print list
String list = "";
for(Ticket t : tickets){
  list = list+"\n \n"+t.getTicket();
}

printList(list);



  //end of main
}

//print  a Ticket  
public static void  printTicket(String ticket){
  try {
            String fileName = "Ticket.txt";
            FileWriter writer = new FileWriter(fileName);
            
            // Write data to the file
            writer.write(ticket);
            
            // Close the writer when done
            writer.close();
            
            System.out.println("Ticket Printed: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
}


//print list
public static void  printList(String list){
  try {
            String fileName = "List.txt";
            FileWriter writer = new FileWriter(fileName);
            
            // Write data to the file
            writer.write(list);
            
            // Close the writer when done
            writer.close();
            
            System.out.println("Ticket Printed: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
}






  
//end of class

}


