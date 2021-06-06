import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import htwb.ai.model.Song;

class SongControllerTest {

	private MockMvc mockMvc;
//	private ISongDAO songDAOMock;

	@BeforeEach
	public void setup() {
		songDAOMock = Mockito.mock(ISongDAO.class);
		mockMvc = MockMvcBuilders.standaloneSetup(
				new SongController(new TestSongDAO())).build();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
