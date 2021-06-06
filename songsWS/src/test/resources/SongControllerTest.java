import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongControllerTest {

	private MockMvc mockMvc;
	private ISongDAO songDAOMock;

	@BeforeEach
	public void setup() {
		songDAOMock = Mockito.mock(ISongDAO.class);
		mockMvc = MockMvcBuilders.standaloneSetup(
				new SongController(songDAOMock)).build();
		
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
