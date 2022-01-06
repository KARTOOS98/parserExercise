import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.shivdev.ParserExercise;


@RunWith(PowerMockRunner.class)
@PrepareForTest(BufferedReader.class)  
class ParserExerciseTest {
	
	ParserExercise parse;
	
	FileReader fr ;
	@BeforeEach
	void setUp() throws Exception {
		parse = new ParserExercise();
		fr = new FileReader("json.txt");
		PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(new BufferedReader(fr));
	}

	@Test
	void happyFlowTest() {
		
		assertEquals("1808130",parse.getId("https://en.wikipedia.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content"));
		
	}
	
	@Test
	void wrongLinkTest() {
		Exception ex = assertThrows(Exception.class, () -> parse.getId("https://en.wikipeda.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content"));
		
		assertEquals("en.wikipeda.org",ex.getMessage());
	}
	
	@Test
	void wrongProtocolTest() {
		Exception ex = assertThrows(Exception.class, () -> parse.getId("htts://en.wikipedia.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content"));
		
		assertEquals("unknown protocol: htts",ex.getMessage());
	}
	
	

}
