package ryan.edu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPlay {
	String[] songs = {"Pinball Wizard","My Generation","Happy Jack","Who are you","Baba O'Reilly"};
	Player pl = null;
	@Before
	public void initialize1(){
		pl = new Player();
	}
	@Test
	public void testAddSong() {
		int count = 0;
		for(int i=0;i<pl.getNumberOfSongs();i++){
			pl.addSong("Song"+1);
			count++;
		}
		assertEquals(count,pl.getNumberOfSongs());
		
	}
	@After
	public void tearDown1(){
		pl = null;
		assertNull(pl);
	}
	
	@Before
	public void initialize2(){
		pl = new Player();
	}
	@Test
	public void testPlay() {
		pl.addSong("Pinball Wizard");
		pl.addSong("My Generation");
		pl.addSong("Happy Jack");
		pl.addSong("Who are you");
		pl.addSong("Baba O'Reilly");
		pl.shuffle();
		int i;
		int j=0;
		String output = pl.play();
		for(i=0;i<songs.length;i++){
			if(output.equals(songs[i])){
				j=i;
				break;
			}
		}
		assertEquals(output,songs[j]);
	}
	@After
	public void tearDown2(){
		pl = null;
		assertNull(pl);
	}
	
	
}
