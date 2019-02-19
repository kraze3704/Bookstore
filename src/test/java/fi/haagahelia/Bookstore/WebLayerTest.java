package fi.haagahelia.Bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

// imports for weblayer methods
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// get()
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// status(), content()
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@Test
	public void testDefaultMessage() throws Exception {
		this.mockMVC.perform(get("/")).andDo(print())
										.andExpect(status().isOk())
										.andExpect(content().string(containsString("Index Page")));
	}
}

/*
 * Application fails when this test is executed.
 * Console reads:
 * 
 * Description:
 * Field userDetailsService in fi.haagahelia.Bookstore.WebSecurityConfig required a bean of type fi.haagahelia.Bookstore.web.UserDetailServiceImpl that could not be found.
 * 
 * The injection point has the following annotations:
 * 	- @org.springframework.beans.factory.annotation.Autowired(required=true)
 * 
 * Action:
 * Consider defining a bean of type fi.haagahelia.Bookstore.web.UserDetailServiceImpl in your configuration
 * 
*/