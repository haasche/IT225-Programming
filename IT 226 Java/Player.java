package ryan.edu;
import java.util.*;


public class Player 
{
	private int numSongs;
	private String[] songs;
	private int[] order;
	private int numSongsInShuffle;
	
	public Player()
	{
		songs = new String[5];
		order = null;
		numSongs = 0;
		numSongsInShuffle = 0;
	}
	
	public void addSong(String name)
	{
		if ((songs==null) || (numSongs==songs.length))
		{
			//expand
			String [] temp;
			temp = new String[2*numSongs];
			for (int i=0;i<songs.length;i++)
				temp[i] = songs[i];
			songs = temp;
		}
		songs[numSongs] = name;
		numSongs++;
	}
	
	public String toString(){
		return Arrays.toString(songs);
	}
	public int getSong(){
		return numSongsInShuffle;
	}
	public int getNumberOfSongs()
	{
		return numSongs;
	}
	
	public void shuffle()
	{
		int i;
		
		order = new int[songs.length];
		for (i=0;i<songs.length;i++)
		{
			order[i] = i;
		}
		numSongsInShuffle = order.length;
	}
	
	public String play()
	{
		int index;
		
		if (numSongsInShuffle==0)
			return "end";
		
		index = (int)(Math.random()*numSongsInShuffle);
		
		String song = songs[order[index]];
		
		//exchange the currently selected song with that at the end of list so that it is not selected again
		int temp = order[index];
		order[index] = order[numSongsInShuffle-1];
		order[numSongsInShuffle-1] = temp;
		numSongsInShuffle--;
		
		return song;		
	}
}
