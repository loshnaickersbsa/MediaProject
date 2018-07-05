package vzap.losh.borrow;

import java.io.Serializable;

import vzap.losh.media.Media;
import vzap.losh.user.User;

public class BorrowedMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user; 
	private Media media;
	
	
	public BorrowedMedia(User user, Media media) {
	
		this.user= user;
		this.media = media;
	}

	
	@Override
	public String toString() {
		return "BorrowedMedia [user=" + user + ", media=" + media + ", getUser()=" + getUser() + ", getMedia()="
				+ getMedia() + "]";
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Media getMedia() {
		return media;
	}


	public void setMedia(Media media) {
		this.media = media;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
