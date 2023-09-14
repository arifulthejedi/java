import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
Name:Ariful 
Project Title: A Simple Hotel Room Booking System
*/



class Room{

  //handle all bus information

  private String roomNo;
  private boolean booked;
  private String type;
  //true = booked
  //false = not booked yet
  

  public Room(String id,String type){
    this.roomNo = id;
    this.booked= false;
    this.type = type;
  }



  public String getRoomNo(){
    return this.roomNo;
  }

  public String getType(){
    return this.type;
  }
  
  public boolean isBooked(){
      return this.booked;
  }
   
  public void setBooked(){
    if(!this.booked){
      this.booked = true;
    }
  }
  
  public void resetBooked(){
      this.booked = false;
  }
  
}
  
 



class Ticket{
  //id, name,issued,date,busno,issuer,userphone,date,busno,issuerid,
  //sitno,

  private String Id,name,checkIn,checkOut,roomNo,roomType,issuerId;

  
  public Ticket(String id,String name,String checkIn,String checkOut,String issuerID){
    this.Id = id;
    this.name = name;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.issuerId = issuerID;
  }

  public String getId(){
      return this.Id;
  }

  public String getCheckin(){
     return this.checkIn;
  }

  
  public void setRoom(String r){
    this.roomNo = r;
  }

  public void setRoomType(String t){
    this.roomType = t;
  }
  
  public String getTicket(){  
    String ticket = "ID:"+this.Id+" Name:"+this.name+"  CheckIn:"+this.checkIn+" CheckOut:"+this.checkOut+"  RoomNo:"+this.roomNo+" roomType:"+this.roomType+" IssuerId:"+this.issuerId;

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

  List<Room> listOfRoomes = new ArrayList<>();
  List<Ticket> listOfTickets = new ArrayList<>();
  List<Admin> listOfIssuers = new ArrayList<>();


  public void setRoom(Room r){
    listOfRoomes.add(r);
  }

  public void setTicket(Ticket ticket){
    listOfTickets.add(ticket);
  }

  public void setIssuer(Admin issu){
    listOfIssuers.add(issu);
  }

  public List<Room> getBusList(){
    return this.listOfRoomes;
  }

  public List<Ticket> getTicketList(){
    return this.listOfTickets;
  }

  public List<Admin> getIssuerList(){
    return this.listOfIssuers;
  }

  public void HotelAvailableRoom(){
      int counter = 1;
      System.out.println("Available room:");
      for(Room r:this.listOfRoomes){
          if(!r.isBooked()){
            counter++;
            System.out.println("Room No:"+r.getRoomNo()+" Type:"+r.getType());
          }
      }     
      if(counter == 1){
          System.out.println("Sorry! :( Room All are Booked");
      }
  }
  
  
  
  public String HotelBookARoom(String rno,Ticket tic){
    for(Room r : this.listOfRoomes){
        if(r.getRoomNo() == rno){
           if(!r.isBooked()){
            r.setBooked(); //book the room
            //Ticket t= tic;
            tic.setRoom(r.getRoomNo());
            tic.setRoomType(r.getType());
            this.setTicket(tic); //adding to the list
            return this.getTicket(tic.getId());
           }  
        }
    }
    
System.out.println("Something went wrong please enter valid nfo");
return "";
}

  

public String getTicket(String id){
    for(Ticket tic : this.listOfTickets){
        if(tic.getId() == id){
            return tic.getTicket();
        }
    }
  
   System.out.println("No Recod found please enter vlid info");
  return "";
}

public String searchTicket(String checkIn){
    for(Ticket t: this.listOfTickets){
      if(t.getCheckin() == checkIn){
          return t.getTicket();
      }
    }
   System.out.println("No record found !");
return "";
}  

  public boolean isRoomBooked(String roomno){
        for(Room r : this.listOfRoomes){
      if(r.getRoomNo() == roomno) {
          if(r.isBooked()){
            return true;
          }
          else
            return false;
      }
    }
   System.out.println("No record found !");
return false;
}


//end class
}





//the driver class
class Hotel_Room_Booking_System{
  public static void main(String[] args) {
   Booking book = new Booking();

   //setting room 
   Room room1 = new Room("A-23","Single Bed");
   Room room2 = new Room("A-24","Single Bed");
   Room room3 = new Room("A-25","Single Bed");
   Room room4 = new Room("A-26","Double Bed");
   Room room5 = new Room("A-27","Double Bed");

   //add a issuer
    String issuerId = "1212"; //functionality for generate uiser id
   Admin issuer1 = new Admin(issuerId,"Kashem");
   issuer1.setWorking();//active the issuer

    //adding room to list
     book.setRoom(room1);
     book.setRoom(room2);
     book.setRoom(room3);
     book.setRoom(room4);
     book.setRoom(room4);


    //Searching for available room
    book.HotelAvailableRoom();

//String id,String name,String checkIn,String checkOut,String issuerID
    
    //booking process
    String tic1id = "2345";//functionality for generate id
    Ticket tic1 = new Ticket(tic1id,"Ariful","23-04-24","24-04-23",issuer1.getId());

        Ticket tic2 = new Ticket("1246","Ariful","23-04-24","24-04-23",issuer1.getId());

  String t1 = book.HotelBookARoom("A-23",tic1);
  String t2 = book.HotelBookARoom("A-24",tic2);

//searching remaining un booked room
//book.HotelAvailableRoom();

    System.out.println(t1);
    
//file hnadling
  printTicket(t1); //printing the ticket

//print list of ticket 
String tickets = "";
List<Ticket> list = book.getTicketList();
for(Ticket t: list){
  tickets = tickets+t.getTicket()+"\n \n";
}
  
printList(tickets);



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