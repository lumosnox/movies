package iristk.app.movies;

import java.util.ArrayList;
import java.util.HashMap;

import iristk.situated.Body;
import iristk.situated.BodyPart;
import iristk.situated.Location;
import iristk.system.Event;
import iristk.util.Record;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Utils {
	public static ArrayList<Integer> calculateGroups(String currentBody, Record rec, HashMap<String,String> userBodies){
		ArrayList<Integer> r = new ArrayList<>();
		Map<String,Object> recMap=rec.toMap();
		String user="";
		System.out.println("The record is: "+rec);
		System.out.println("Current body is: "+currentBody);
		double d;
		//r.add(2);
		//r.add(3);
		//Body current = (Body) rec.get(s);
		Location l;
		try{
			Body b = (Body)recMap.get(currentBody);
			BodyPart m2 = (BodyPart) b.get("head");
			Location lCurrent= (Location) m2.get("location");
			
			for(String key: recMap.keySet()){
				b = (Body) recMap.get(key);
				m2 = (BodyPart) b.get("head");
				l = (Location) m2.get("location");
				d = calculateDistance(lCurrent.x,lCurrent.y,l.x,l.y);
				if(!key.equals(currentBody) && d<1.5){ //<1.5 meters
					//get the user for that body
					for(String u: userBodies.keySet()){
						if(userBodies.get(u).equals(key))
							user=userBodies.get(u);
						}
					//add user if user exists
					if (!user.equals(""))
						r.add(getIndex(user));
					}			
			}
		}
		catch (Exception e){
			//do nothing, the catch is just a precaution in case iristk body id does not match the user id
		}
		//getHeadLocation();
		return r; //return array of indexes for users, without the current user
	}
	
	public static boolean canUserAnswer(Event e, ArrayList<Integer> currentUsers){
		
		String s = e.getString("user"); //assuming this user is the "side" user
		//System.out.println("Inside canUserAnswer, event user is: "+s+" and currentUsers are: "+currentUsers);
		int u =Integer.parseInt(s.substring(5));
		if(currentUsers.contains(u))
			return true;
		return false;
	}
	
	public static double calculateDistance(double x1, double y1, double x2, double y2){
		double dist,x,y;
		x = x1-x2;
		y = y1-y2;
		dist = (int) Math.sqrt(x*x+y*y);
		return dist;
	}
	//calculate distance between user body and agent
	public static double calculateDistanceBody(String body,Record rec){
		Map<String,Object> recMap=rec.toMap();
		Body b = (Body)recMap.get(body);
		BodyPart m2 = (BodyPart) b.get("head");
		Location lCurrent= (Location) m2.get("location");
		return calculateDistance(lCurrent.x,lCurrent.y,0,0); 
	}
	
	public static int getIndex(String body){ //of form "body-index" or "user-index", aka index position starts from 5
		int l = body.length();
		return Integer.parseInt(body.substring(5,l)); 
	}
	
}
