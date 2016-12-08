package matrix;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CharTest {
	
	public static boolean isUpperCase(int a){
		if((a > 64) && (a < 91)){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isInt(int a){
		if((a > 47) && (a < 58)){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isLowerCase(int a){
		if((a > 96) && (a < 123)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void periodicElements(String input1){
		String input = input1 + "  ";
		for(int x = 0; x < input.length(); x++){
			int c = input.charAt(x);
			StringBuilder builder = new StringBuilder();
			String ret = new String();
			if(isUpperCase(c)){
				builder.append((char)c);
				if(isLowerCase((int)(input.charAt(x+1)))){
					builder.append(input.charAt(x+1));
					if(isInt(input.charAt(x+2))){
						int number = input.charAt(x+2) - 48;
						builder.append(number);
					}
					else{
						builder.append(1);
					}
				}
				else if(isInt(input.charAt(x+1))){
					int number = input.charAt(x+1) - 48;
					builder.append(number);
				}
				else{
					builder.append(1);
				}
				ret = builder.toString();
				System.out.println(ret);
			}
		}
	}
	
		
	public static Set numElements(String s2){
		String s1 = s2+"  ";
		Set set = new HashSet();
		for(int x = 0; x < s1.length(); x++){
			String add = new String();
			StringBuilder builder = new StringBuilder();
			if(isUpperCase(s1.charAt(x))){
				builder.append(s1.charAt(x));
				if(isLowerCase(s1.charAt(x+1))){
					builder.append(s1.charAt(x+1));
				}
				add = builder.toString();
				set.add(add);
			}
		}
		return set;
	}
	
	public static List listCompounds(String s1, String s2){
		String equation = s1 + "  " + s2 + "  ";
		List list = new ArrayList();
		for(int x = 0; x < equation.length(); x++){
			String compound = new String();
			StringBuilder builder = new StringBuilder();
			if(isInt(equation.charAt(x)) || isUpperCase(equation.charAt(x)) || isLowerCase(equation.charAt(x))){
				builder.append(equation.charAt(x));
				while(isInt(equation.charAt(x+1)) || isUpperCase(equation.charAt(x+1))|| isLowerCase(equation.charAt(x+1))){
					x++;
					builder.append(equation.charAt(x));
				}	
				compound = builder.toString();
				list.add(compound);
			}
		}
		
		return list;
	}
	
	public static List listCompoundsWithArrow(String s1, String s2){
		s1 += "  ";
		s2 += "  ";
		String equation = s1 + "  " + s2 + "  ";
		List list = new ArrayList();
		for(int x = 0; x < s1.length(); x++){
			String compound = new String();
			StringBuilder builder = new StringBuilder();
			if(isInt(s1.charAt(x)) || isUpperCase(s1.charAt(x)) || isLowerCase(s1.charAt(x))){
				builder.append(s1.charAt(x));
				while(isInt(s1.charAt(x+1)) || isUpperCase(s1.charAt(x+1))|| isLowerCase(s1.charAt(x+1))){
					x++;
					builder.append(s1.charAt(x));
				}	
				compound = builder.toString();
				list.add(compound);
			}
		}
		list.add("arrow");
		for(int x = 0; x < s2.length(); x++){
			String compound = new String();
			StringBuilder builder = new StringBuilder();
			if(isInt(s2.charAt(x)) || isUpperCase(s2.charAt(x)) || isLowerCase(s2.charAt(x))){
				builder.append(s2.charAt(x));
				while(isInt(s2.charAt(x+1)) || isUpperCase(s2.charAt(x+1))|| isLowerCase(s2.charAt(x+1))){
					x++;
					builder.append(s2.charAt(x));
				}	
				compound = builder.toString();
				list.add(compound);
			}
		}
		return list;
	}
	
	public static void listToString(List s){
		for(int x = 0; x < s.size(); x++){
			System.out.print(s.get(x) + "  ");
		}
		System.out.println();
	}
	
	public static double[][] makeArray(String s1, String s2){
		List compoundList = listCompounds(s1, s2);
		Set set = numElements(s1);
		int numColumns = compoundList.size();
		int numRows = numElements(s1).size();
		double[][] array = new double[numRows][numColumns];
//		for(int x = 0; x < numRows; x++){
//			array[x][numColumns] = 0.0;
//		}
		s1 = s1 + " "; 
		s2 = s2 + "  ";
		System.out.println("Input: "+s1+"--> "+s2);
		for(int z = 0; z < numColumns; z++){
			String compound = compoundList.get(z) + "      ";
			int multiplier = 1;
			if(isInt(compound.charAt(0))){
				multiplier = (int)compound.charAt(0) - 48;
			}
			//System.out.println("compound: " + compound);
			//System.out.println(getIndex(set,compoundList.getItem(z)));
			for(int x = 0; x < compound.length()-1; x++){
				String element = new String();
				StringBuilder builder = new StringBuilder();
				if(isUpperCase(compound.charAt(x))){
					builder.append(compound.charAt(x));
		
					if(isLowerCase(compound.charAt(x+1))){
						x++;
						builder.append(compound.charAt(x));
						
						if(isInt(compound.charAt(x+1))){		//edit here by using while loop to take care of 2 digit numbers
							x++;
							element = builder.toString();
							int c = (int)compound.charAt(x)-48;
							int r = getIndex(set,element);
							array[r][z] = c*multiplier;
							//System.out.println(element+": "+ c*multiplier);
						}
						else{
							element = builder.toString();
							int r = getIndex(set,element);
							array[r][z] = multiplier;
							//System.out.println(element+": "+ multiplier);
						}
					}
					else if(isInt(compound.charAt(x+1))){		//edit here by using while loop to take care of 2 digit numbers
						x++;
						element = builder.toString();
						int c = (int)compound.charAt(x)-48;
						int r = getIndex(set,element);
						array[r][z] = c*multiplier;
						//System.out.println(element+": "+ c*multiplier);
					}
					else{
						element = builder.toString();
						//System.out.println(element);
						int r = getIndex(set,element);
						array[r][z] = multiplier;
						//System.out.println(element+": "+ multiplier+"");
					}
				}
			}

			
		}
		return array;
	}
	
	public static int getIndex(Set set, String s) {
		ArrayList<String> nameList = new ArrayList<String>(set);
		for(int x = 0; x < nameList.size(); x++){
			if(s.equals(nameList.get(x))){
				return x;
			}
			//System.out.println(nameList.get(x));
		}
		return -1;
	}
	
	public static void printArray(int[][] a){
		for(int x = 0; x < a.length; x++){
			for(int z = 0; z < a[0].length; z++){
				System.out.print(a[x][z]);
			}
			System.out.println(x);
		}
	}


	public static void main(String[] args) {
		int count = 0;
		String s1 = " 2C2H6 + 7O2";
		String s2 = " 4CO2 + 6H2O";
		//periodicElements(s1);
		//listToString(listCompounds(s1, s2));
		System.out.println(numElements(s1));
		//makeArray(s1, s2);
		//System.out.println(Arrays.deepToString(makeArray(s1, s2)));
		Matrix m = new Matrix(makeArray(s1, s2));
		m.print();
	}	
}
