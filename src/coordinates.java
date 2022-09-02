import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class coordinates {
	
	public static double[] coord(double num) {// transforma de latitud, longitud a dms
		
		int gra = (int)num;
		double min = (num - gra) * 60;
		int mins = (int)min;
		double sec = (min - mins) * 60;
		
		double[] coor = {gra, mins, sec};
		return coor;
	}
	
	public static void main(String[] args) {
		File doc = new File("coordinates.txt");
		        
		try {
			
			Scanner obj = new Scanner(doc);
			ArrayList<String> places = new ArrayList<String>(); // almacena los posibles candidatos
			
			while (obj.hasNextLine()) {
				
				String[] coor = obj.nextLine().split(";");// coor[0] tiene la latidud
				String[] coor2 = coor[1].split(" "); // coor2[0] tiene la longitud y coor2[i] el precio
				coor2[1] = coor2[1].substring(0, coor2[1].length()-1);
				
				if(Integer.parseInt(coor2[1]) <= 8000) {
					double[] lat = coord(Double.parseDouble(coor[0].replace(",", ".")));
					double[] lon = coord(Double.parseDouble(coor2[0].replace(",", ".")));

					DecimalFormat df = new DecimalFormat("#.00");// los segundos tienen 2 decimales
					DecimalFormat df2 = new DecimalFormat("#");// grados y minutos sin decimales
					
					places.add("N" + df2.format(lat[0]) + "º" + df2.format(lat[1]) + "'" + df.format(lat[2]) + "'';E" 
							+ df2.format(lon[0]) + "º" + df2.format(lon[1]) + "'" + df.format(lon[2]) + "'' " 
							+ coor2[1] +"€");
				}
			}
			
			Random rand = new Random();
			int int_random = rand.nextInt(places.size()); 
			
			File file = new File("winners.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(places.get(int_random));
            bw.close();
            obj.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}
}
