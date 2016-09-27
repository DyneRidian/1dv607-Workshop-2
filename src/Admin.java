import java.util.ArrayList;

public class Admin {

	ArrayList<Member> memberStorage;
	
	
	/* this method right now is only changing the memebr list, but memberlist is empty
	 * everytime you start the program so its kinda shitty, unless we populate the memberlist everytime we start the program by reading
	 * the text file. but at that point maybe its just better to have no list and modify textfile directly.
	 */
	public void deleteMember(String memberID){
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID() == memberID){
				
				memberStorage.remove(i);
			}
			
		}
		
	}
	
}
