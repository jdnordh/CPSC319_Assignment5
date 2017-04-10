package hash;

public class StringHashTable {
	private String [] data;
	private String [] entries;
	private int entryIndex;
	private int length;
	private int collisions;
	private double efficiency;
	private int totalRecords;
	private int maxReads;
	private int prime = 2;
	private int seed = 2;
	/**
	 * Make a hash table of certain size, size always should be a prime
	 * @param size Length of table
	 */
	public StringHashTable(int size){
		data = new String[size];
		entries = new String[size];
		length = size;
		entryIndex = totalRecords = maxReads = 0;
		setLength(size);
	}

	/**
	 * Add a string to the hash table by finding an address to hash to
	 * @param s String to be entered
	 */
	public void add(String s){
		entries[entryIndex++] = s;
		int i = hash(s);
		if (data[i] == null)
			data[i] = s;
		else {
			while (data[i] != null) {		// Linear probing
				i++;
				i = i %  length;
			}
			data[i] = s;
			collisions++;
		}
	}
	
	/** 
	 * Get the a string from the table
	 * @param s String
	 * @return String
	 */
	public String get(String s){
		return this.at(hash(s));
	}
	
	/**
	 * Get a hashed value from a string
	 * Adapted from the FNV-a1 hashing method
	 * @param s String 
	 * @return Array index
	 */
	public int hash(String s){
		/*if (s == null || s.equals("")) return 0;
		int hash = seed;
		byte [] arr = s.getBytes();
		for (int i = 0; i < arr.length; i++){
			hash = hash ^ arr[i];
			hash = hash * prime;
			// current best: 35.73146392527241 for seed: 3592 with prime: 5483147
		}*/
		
		int [] primes = {73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 
				137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 
				193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
				257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
				1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069,
				2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129};
		int j = s.charAt(0) % primes.length;
		int hash = seed;
		for (int i = 0; i < s.length(); i++){
			j += s.charAt(Math.abs(hash % s.length()));
			j %= primes.length;
			hash = ((hash * primes[j]) + (s.charAt(i) * primes[primes.length - 1 - j]));
			hash = hash * primes[j];
			int sw = s.charAt((j << 5) % s.length()) % 5;
			switch (sw){
			case 0:{
				hash = hash ^ s.charAt(i);
				hash = hash * prime;
				break;
			}
			case 1:{
				hash = hash * prime;
				hash = hash ^ s.charAt(i);
				break;
			}
			case 2:{
				hash = (hash >> 7) ^ s.charAt(i);
				hash = (hash >> 5) * prime;
				break;
			}
			case 3:{
				hash = (hash >> 5) * prime;
				hash = (hash >> 7) ^ s.charAt(i);
				break;
			}
			case 4:{
				hash += (hash << 3) - (hash >> 1);
				hash ^= (hash << sw) + (primes[j] << 3) - hash;
				break;
			}
			}
			//hash %= length;
		}
		hash %= length;
		return Math.abs(hash);
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
	
	public void setPrime(int p){
		prime = p;
	}
	
	public double loadFactor(){
		double lf = 0;
		double records = 0;
		for (int i = 0; i < data.length; i++){
			if (data[i] != null) records++;
		}
		double length = data.length;
		lf = (records / length);
		return lf;
	}
	
	public int getMaxReads(){
		return maxReads;
	}
	
	public double getEfficiency() {
		int totalReads = 0, singleRead = 0;;
		for (int i = 0; i < data.length; i++){
			if (data[i] != null) totalRecords++;
		}
		for (int i = 0; i < totalRecords; i++){
			singleRead = 1;
			int j = hash(entries[i]);
			totalReads++;
			while (!data[j++].equals(entries[i])){
				totalReads++;
				singleRead++;
				j = j % length;
			}
			if (singleRead > maxReads) maxReads = singleRead;
		}
		double avgReads = ((double)totalReads) / ((double)totalRecords);
		efficiency = loadFactor() / avgReads;
		efficiency = efficiency * 100;
		//System.out.println("Total reads is: " + totalReads);
		//System.out.println("Average reads is: " + avgReads);
		return efficiency;
	}
	
	public void setSeed(int s){
		seed = s;
	}
}
