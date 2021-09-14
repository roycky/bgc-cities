

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class CitiesConnection {

	private String file;
	private List<Set<String>> listSet = new LinkedList<>();

	public CitiesConnection(String file) throws IOException {
		super();
		this.file = file;
		init();
		
	}

	private void init() throws IOException {
		FileInputStream fis = new FileInputStream(new File(file));
		constructMap(fis);
	}
	
	public boolean isConnected(String c1, String c2) {
		return listSet.stream().anyMatch(it->{
			return it.contains(c1) && it.contains(c2);
		});
	}

	private void constructMap(InputStream inputStream)
			throws IOException {
		
		try (
				FileInputStream fis = new FileInputStream(new File(file));
				BufferedReader br
				= new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			
			while ((line = br.readLine()) != null) {
				
				String[] rawCities = line.split("\\s*,\\s*");
				String c1 = rawCities[0];
				String c2 = rawCities[1];
				Set<String> foundSet = null;
				
				Iterator<Set<String>> iter = listSet.iterator();
				while(iter.hasNext()) {
					Set<String> set = iter.next();
					if(set.contains(c1)) {
						
						// Merge two sets, then remove one of the set
						if(foundSet != null) {
							foundSet.addAll(set);
							iter.remove();
							break;
						}else {
							foundSet = set;
							set.add(c2);
						}
					}else if(set.contains(c2)) {
						if(foundSet != null) {
							foundSet.addAll(set);
							iter.remove();
							break;
						}else {
							foundSet = set;
							set.add(c1);
						}
					}
				}
				
				// Create a new set
				if(foundSet == null) {
					Set<String> set = new HashSet<String>();
					set.add(c1);
					set.add(c2);
					listSet.add(set);
				}
				
			}
		}
	}

}
