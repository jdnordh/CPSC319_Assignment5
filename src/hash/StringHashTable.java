package hash;

public class StringHashTable {
	private String [] data;
	private int length;
	private int collisions;
	private int efficiency;
	
	/**
	 * Make a hash table of certain size, size always should be a prime
	 * @param size Length of table
	 */
	public StringHashTable(int size){
		data = new String[size];
		setLength(size);
	}

	/**
	 * Add a string to the hash table by finding an address to hash to
	 * @param s String to be entered
	 */
	public void add(String s){
		int i = hash(s);
		data[i] = s;
	}
	
	public int hash(String s){
		int x = 0;
		
		// Part one get a small constant from the characters being even or odd ascii characters
		int part1 = 0;
		for (int i = 0; i < s.length(); i++){
			int temp = s.charAt(i);
			temp = temp % 2;
			part1 += Math.pow(-1.0, temp);
		}
		//System.out.println(part1);
		x += part1;
		int part2 = 0;
		for (int i = 0; i < s.length(); i++){
			int temp = s.charAt(i);
			part2 += temp * (temp - 1) / (temp -2);
		}
		//System.out.println(part2);
		x += part2;
		
		// Part three 
		int part3 = 0;
		String temp2 = "";
		String temp3 = "";
		int half = s.length()/2;
		for (int i = 0; i < half; i++){
			int temp = s.charAt(i);
			String parse = Integer.toString(temp);
			temp2 += parse;
		}
		for (int i = 0; i < temp2.length(); i++){
			part3 += temp2.charAt(i);
		}
		for (int i = half; i < s.length(); i++){
			int temp = s.charAt(i);
			String parse = Integer.toString(temp);
			temp3 += parse;
		}
		for (int i = 0; i < temp3.length(); i++){
			part3 += temp3.charAt(i) + 1;
		}
		//System.out.println(part3);
		
		x += part3;
		x = x + (part2 * part3);
		
		
		
		
		x = x % length;
		if (x < 0) x = x * (-1);
		return x;
	}
	
	// Setters and Getters
	
	public String at(int i){
		return data[i];
	}
	
	public void setAt(String s, int i){
		data[i] = new String(s);
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	
	public static void main(String[] args) {
		String [] s = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "bois"};
		StringHashTable t = new StringHashTable(11);
		
		for (int i = 0; i < s.length; i++){
			System.out.println(s[i] + " hashed to: " + t.hash(s[i]));
		}
		
	}
}
