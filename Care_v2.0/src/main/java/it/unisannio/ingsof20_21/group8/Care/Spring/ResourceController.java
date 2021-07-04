package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*metodi su cui vine applicata la politica di sccesso*/

@RestController
public class ResourceController {
	
	@RequestMapping("/hellouser")
	public String getUser()
	{
		return "Hello User";
	}
	
	@RequestMapping("/helloadmin")
	public String getAdmin()
	{
		return "Hello Admin";
	}
	
	@RequestMapping("/helloluigi")
	public String getLuigi()
	{
		return "Hello luiggiiii";
	}

}